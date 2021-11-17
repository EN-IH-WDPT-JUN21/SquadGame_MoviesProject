package com.ironhack.apigateway.configuration;

import com.ironhack.apigateway.filters.AuthorizationHeadersFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApiGatewayConfiguration {

    private final String PLAYLIST_SERVICE_NAME = "PLAYLIST-SERVICE";
    private final String USER_SERVICE_NAME = "USER-SERVICE";
    private final Environment environment;

    public ApiGatewayConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                //GET, DELETE, PATCH Playlist by ID
                .route(p -> p.path("/playlists/**")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET Playlists by user
                .route(p -> p.path("/user/**")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET, POST Playlist (get all)
                .route(p -> p.path("/playlists")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET Playlist found by ID
                .route(p -> p.path("/playlist_by_id/**")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //POST Playlist
                .route(p -> p.path("/playlists")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET, POST playlist item (get all)
                .route(p -> p.path("/playlist-item")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET, DELETE playlist item by ID
                .route(p -> p.path("/playlist-item/**")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //GET playlist items by playlist name
                .route(p -> p.path("/playlist-title/**")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+PLAYLIST_SERVICE_NAME))
                //USER SERVICE
                .route(p -> p.path("/users")
                        .uri("lb://"+USER_SERVICE_NAME))
                .route(p -> p.path("/users/login")
                        .uri("lb://"+USER_SERVICE_NAME))
                .route(p -> p.path("/user_details/**")
                        .filters(f -> f.filter(new AuthorizationHeadersFilter(environment).apply(new AuthorizationHeadersFilter.Config())))
                        .uri("lb://"+USER_SERVICE_NAME))
                .build();
    }
}
