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
                //GET, DELETE, PATCH Playlist by ID
                .route(p -> p.path("/playlists/**")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET Playlists by user
                .route(p -> p.path("/user/**")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET, POST Playlist (get all)
                .route(p -> p.path("/playlists")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET Playlist found by ID
                .route(p -> p.path("/playlist_by_id/**")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //POST Playlist
                .route(p -> p.path("/playlists")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET, POST playlist item (get all)
                .route(p -> p.path("/playlist-item")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET, DELETE playlist item by ID
                .route(p -> p.path("/playlist-item/**")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET playlist items by playlist name
                .route(p -> p.path("/playlist-title/**")
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                .build();
    }
}
