package com.example.reactive.crud.demo.handler;

import com.example.reactive.crud.demo.model.Response;
import com.example.reactive.crud.demo.model.UserEntitlementResponse;
import com.example.reactive.crud.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@AllArgsConstructor
@Slf4j
public class UserHandler {
    private static final String START_TIME = "startTimeUTC";
    private static final String MAXIMUM_NUMBER = "maximumNumber";
    private static final String DATA_NOT_FOUND_MESSAGE = "The data you seek is not here.";

    private final UserService userService;
    public Mono<ServerResponse> getUserDetails(ServerRequest request) {
        String startTime = request.queryParam(START_TIME)
            .filter(timeValue -> !timeValue.isEmpty())
            .orElseThrow(() -> new RuntimeException("Invalid request. Mandatory parameter(s) missing."));

        String maxNumber = request.queryParam(MAXIMUM_NUMBER).orElse("3");
        int maximumNumber = Integer.parseInt(maxNumber);

        return ServerResponse.ok().contentType(APPLICATION_JSON)
            .body(userService.getUserAccountDetails(startTime, maximumNumber),Response.class)
                .onErrorResume(Mono::error)
                .switchIfEmpty(Mono.error(new RuntimeException(DATA_NOT_FOUND_MESSAGE)));
    }


    public Mono<ServerResponse> getEntitlements(ServerRequest serverRequest) {


        return ok().contentType(APPLICATION_JSON)
            .body(userService.getEntitlements(serverRequest.pathVariable("id")), UserEntitlementResponse.class)
            .onErrorResume(Mono::error)
            .switchIfEmpty(Mono.error(new RuntimeException(DATA_NOT_FOUND_MESSAGE)));

    }

}
