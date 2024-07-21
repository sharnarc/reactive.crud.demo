package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.ExchangeRate;
import com.example.reactive.crud.demo.model.ExchangeRateDTO;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ExchangeRateService {

    Mono<ExchangeRateDTO> getRateOfExchangeByTargetCurrencyAndDate(String targetCurrency, LocalDate date);
    Mono<Void> saveRateOfExchange(ExchangeRateDTO exchangeRateDTO);
}
