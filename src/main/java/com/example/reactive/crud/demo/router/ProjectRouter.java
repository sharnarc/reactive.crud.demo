package com.example.reactive.crud.demo.router;

import com.example.reactive.crud.demo.handler.ProjectHandler;
import com.example.reactive.crud.demo.handler.SettingsHandler;
import com.example.reactive.crud.demo.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
@Configuration
public class ProjectRouter {
    private static final String BASE_ACCOUNTS_URI = "/project/{projectId}";
    @Bean
    public RouterFunction<ServerResponse> projectRoute(ProjectHandler projectHandler) {
        return RouterFunctions.route(GET(BASE_ACCOUNTS_URI), projectHandler::getProjectDetails);
    }
}
