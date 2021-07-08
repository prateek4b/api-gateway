package com.guide.cm.apigatway.route;


import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Consumer;

@Configuration
public class RouteConfiguration {

    /*@Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("route1",predicateSpec -> predicateSpec.path("/incorrectUrl")
                        *//*.filters(gatewayFilterSpec ->
                                gatewayFilterSpec.circuitBreaker(config -> config.setName("myCircuitBreaker")
                                .setFallbackUri("forward:/fallback"))).uri("lb://USER-PROFILE-SERVICE"))*//*
                        .filters(f -> f.circuitBreaker(c -> c.setName("myCircuitBreaker").setFallbackUri("forward:///fallback")))
                        .uri("lb://USER-PROFILE-SERVICE"))
                .route("route2",predicateSpec -> predicateSpec.path("/sayHello")
                        .uri("lb://USER-PROFILE-SERVICE"))
                .route("route3",predicateSpec -> predicateSpec.path("/afterTime").and()
                        .before(evalTodayRequest()).filters(x->x.prefixPath("/time").preserveHostHeader())
                        .uri("lb://USER-PROFILE-SERVICE"))
                .route("route4",predicateSpec -> predicateSpec.path("/showRequest")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.addRequestHeader("gateway","apiGateway")
                                *//*.addRequestParameter("gateway","apiGateway")*//*)
                        .uri("lb://USER-PROFILE-SERVICE"))
                .route("route6",predicateSpec -> predicateSpec.path("/fallback")
                .uri("lb://API-GATEWAY"))
                .route("route7",predicateSpec -> predicateSpec.path("/timer/**")
                        .filters(x->x.rewritePath("/timer(?<s egment>/?.*)","${segment}"))
                        .uri("lb://USER-PROFILE-SERVICE"))
                .route("route8",predicateSpec -> predicateSpec.path("/retryer")
                        .filters(x->x.retry(retryConfig -> getRetryConfig(retryConfig)))
                        .uri("lb://USER-PROFILE-SERVICE"))

                .build();
    }*/

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("client",r->r.path("/client/**").uri("lb://USER-PROFILE-SERVICE"))
                .route("fee",r->r.path("/fee/**").uri("lb://FEE-CALCULATION-SERVICE"))
                .route("report",r->r.path("/report/**").uri("lb://REPORT-GENERATOR"))
                .route("route2",predicateSpec -> predicateSpec.path("/sayHello")
                        .uri("lb://USER-PROFILE-SERVICE"))
                .build();
    }

    private RetryGatewayFilterFactory.RetryConfig getRetryConfig(RetryGatewayFilterFactory.RetryConfig retryConfig) {

        retryConfig.setRetries(3);
        retryConfig.setStatuses(HttpStatus.FOUND,HttpStatus.OK);

        RetryGatewayFilterFactory.BackoffConfig backoff = new RetryGatewayFilterFactory.BackoffConfig() ;
        backoff.setFirstBackoff(Duration.ofMillis(10));
        backoff.setMaxBackoff(Duration.ofMillis(100));
        backoff.setFactor(2);
        backoff.setBasedOnPreviousValue(false);
        retryConfig.setBackoff(backoff);

        return retryConfig;
    }

    private ZonedDateTime evalTodayRequest() {
        ZonedDateTime dateTime = ZonedDateTime.of(
        2021, 7, 6,
        1, 0, 0, 0, ZoneId.of("Asia/Kolkata"));
        return dateTime;
    }
}
