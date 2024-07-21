package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.mapper.UserMapper;
import com.example.reactive.crud.demo.model.Entitlement;
import com.example.reactive.crud.demo.model.Response;
import com.example.reactive.crud.demo.model.UserEntitlementResponse;
import com.example.reactive.crud.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    @Override
    public Mono<Response> getUserAccountDetails(String startTime, int size) {

        Timestamp startTimeTs = Timestamp.valueOf(OffsetDateTime.parse(startTime).toLocalDateTime());
        return userRepository.findAllByStartTime(startTimeTs)
            .collectList()
            .flatMap(userList -> Mono.just(userList.stream().limit(size).collect(Collectors.toList()))
                .flatMapMany(Flux::fromIterable)
                .map(user -> Mappers.getMapper(UserMapper.class).toUserAccount(user))
                .collectList().map(userAccounts ->
                    Response.builder()
                        .userAccounts(userAccounts)
                        .pageSize(userAccounts.size())
                        .totalSize(userList.size())
                        .build()).onErrorReturn(Response.builder().totalSize(userList.size()).build())
            ).onErrorReturn(Response.builder().build());


    }


    @Override
    public Mono<UserEntitlementResponse> getEntitlements(String id) {
        return userRepository.findById(id).map(user -> Mappers.getMapper(UserMapper.class).toUserAccount(user))
            .map(userAccount ->
                UserEntitlementResponse.builder()
                    .userAccounts(userAccount)
                    .entitlements(getUserEntitlements(userAccount.getEntitlements()))
                    .build());
    }


    private Entitlement getUserEntitlements(String entitlements) {

        try {
            return objectMapper.readValue(entitlements, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
