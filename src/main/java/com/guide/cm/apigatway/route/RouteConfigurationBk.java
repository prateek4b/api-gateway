package com.guide.cm.apigatway.route;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

//@Configuration
public class RouteConfigurationBk {

    //@Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                /*.route("first_route",
                        r -> r.path("/sayHello").uri("lb://USER-PROFILE-SERVICE"))*/
                               /* filters(gatewayFilterSpec -> gatewayFilterSpec.circuitBreaker(config->config.setFallbackUri("forward:/fallback"))).
                               */

                /*.route("first_route",
                        r -> r.path("/sayHello").
                                uri("lb://USER-PROFILE-SERVICE/"))*/
         /*       .route("first-route", r -> r.path("/sayHello").
                        filters(f -> f.rewritePath("sayHello", "sayRedirected")).
                        uri("lb://USER-PROFILE-SERVICE"))
*/
               /* .route("route1",predicateSpec -> predicateSpec.path("/sayHello").filters(gatewayFilterSpec -> gatewayFilterSpec.circuitBreaker(config -> config.setName().setFallbackUri())))*/
                .build();
    }
}
