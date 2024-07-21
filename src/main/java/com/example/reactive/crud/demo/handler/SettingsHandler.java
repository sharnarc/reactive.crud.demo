package com.example.reactive.crud.demo.handler;

import com.example.reactive.crud.demo.entity.UserSettings;
import com.example.reactive.crud.demo.model.UserSettings1;
import com.example.reactive.crud.demo.service.UserSetting;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@AllArgsConstructor
@Slf4j
public class SettingsHandler {

   private final UserSetting setting;
    public Mono<ServerResponse> getUserSettings(ServerRequest request) {

        String accountId = request.pathVariable("accountId");
        String settingId="1";
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(setting.findByAcctIdAndsettingId(accountId,settingId),UserSettings.class);
    }

    public Mono<ServerResponse> saveUserSettings(ServerRequest serverRequest) {

       return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
               .body(serverRequest.bodyToMono(UserSettings.class)
               .flatMap(userSettings -> setting.saveUserSettings(userSettings)), Void.class);

    }


    public Mono<ServerResponse> getSettings(ServerRequest serverRequest) {
        String accountId = serverRequest.pathVariable("accountId");
        String settingId = serverRequest.pathVariable("settingId");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(setting.getUserSettingsNew(accountId,settingId), UserSettings1.class);
    }

}
