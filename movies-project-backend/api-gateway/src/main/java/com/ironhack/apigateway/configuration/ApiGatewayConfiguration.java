package com.ironhack.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    private final String PLAYLIST_SERVICE_NAME = "PLAYLIST-SERVICE";
    private final String AUTH_SERVICE_NAME = "AUTH-SERVICE";

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                //GET Playlist for user
                .route(p -> p.path("/playlists/**")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET ALL Playlist
                .route(p -> p.path("/playlists")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET Playlist found by ID
                .route(p -> p.path("/playlist_by_id/**")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //POST Playlist
                .route(p -> p.path("/playlists")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET User
                .route(p -> p.path("/users/**")
                        .uri("lb://"+AUTH_SERVICE_NAME))
                //POST User
                .route(p -> p.path("/users")
                        .uri("lb://"+AUTH_SERVICE_NAME))
                //POST Authenticate user
                .route(p -> p.path("/authenticate")
                        .uri("lb://"+AUTH_SERVICE_NAME))
                .build();
    }
}
