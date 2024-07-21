package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.model.Response;
import com.example.reactive.crud.demo.model.UserEntitlementResponse;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<Response> getUserAccountDetails(String startTime, int size);


    Mono<UserEntitlementResponse> getEntitlements(String id);

}
