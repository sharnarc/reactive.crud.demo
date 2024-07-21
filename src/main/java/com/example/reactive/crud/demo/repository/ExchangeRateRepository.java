package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.ExchangeRate;
import com.example.reactive.crud.demo.entity.RateIdentity;
import com.example.reactive.crud.demo.model.ExchangeRateDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ExchangeRateRepository extends R2dbcRepository<ExchangeRate, String> {

    Mono<ExchangeRate> findAllByTargetCurrencyAndDate(String targetCurrency, LocalDate date);
}
