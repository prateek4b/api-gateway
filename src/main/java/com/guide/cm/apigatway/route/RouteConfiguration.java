package com.guide.cm.apigatway.route;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

//@Configuration
public class RouteConfiguration {

    //@Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("first_route",
                        r -> r.path("/sayHello").
                               /* filters(gatewayFilterSpec -> gatewayFilterSpec.circuitBreaker(config->config.setFallbackUri("forward:/fallback"))).
                               */ uri("lb://USER-PROFILE-SERVICE"))
                .build();
    }
}
