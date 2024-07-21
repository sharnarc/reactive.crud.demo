package com.example.reactive.crud.demo.handler;

import com.example.reactive.crud.demo.entity.ExchangeRate;
import com.example.reactive.crud.demo.entity.UserSettings;
import com.example.reactive.crud.demo.model.ExchangeRateDTO;
import com.example.reactive.crud.demo.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateHandler {

    private final ExchangeRateService rateService;

    public Mono<ServerResponse> findExchangeRateByDateAndTargetCurrency(ServerRequest request) {

        String date = request.pathVariable("date");
        String targetCurrency = request.pathVariable("targetCurrency");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(rateService.getRateOfExchangeByTargetCurrencyAndDate(targetCurrency, LocalDate.parse(date)), ExchangeRateDTO.class);
    }

    public Mono<ServerResponse> saveRates(ServerRequest serverRequest) {


        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(serverRequest.bodyToMono(ExchangeRateDTO.class)
                        .flatMap(rateDTO -> rateService.saveRateOfExchange(rateDTO)), Void.class);


    }
}
