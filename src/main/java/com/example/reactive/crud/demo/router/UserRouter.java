package com.example.reactive.crud.demo.router;

import com.example.reactive.crud.demo.handler.SettingsHandler;
import com.example.reactive.crud.demo.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class UserRouter {
    private static final String BASE_ACCOUNTS_URI = "/account";
    private static final String BASE_ENTITLEMENT_URI = "/entitlement";
    private static final String BASE_SETTINGS_URI = "/settings";

    @Bean
    public RouterFunction<ServerResponse> userRoute(UserHandler userHandler, SettingsHandler settingsHandler) {
        return RouterFunctions.route(GET(BASE_ACCOUNTS_URI), userHandler::getUserDetails)
            .andRoute(GET(BASE_ENTITLEMENT_URI+"/{id}"),userHandler::getEntitlements)
            .andRoute(GET(BASE_SETTINGS_URI+"/{accountId}/{settingId}"),settingsHandler::getSettings);
    }

}
