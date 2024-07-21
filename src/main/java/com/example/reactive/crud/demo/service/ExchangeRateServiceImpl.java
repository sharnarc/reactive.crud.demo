package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.ExchangeRate;
import com.example.reactive.crud.demo.model.ExchangeRateDTO;
import com.example.reactive.crud.demo.repository.ExchangeRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService{

    private final ExchangeRateRepository rateRepository;
    @Override
    public Mono<ExchangeRateDTO> getRateOfExchangeByTargetCurrencyAndDate(String targetCurrency, LocalDate date) {
        return rateRepository.findAllByTargetCurrencyAndDate(targetCurrency, date)
                .map(this::toExchangeRateDTO);
    }

    @Override
    public Mono<Void> saveRateOfExchange(ExchangeRateDTO exchangeRateDTO) {
        return rateRepository.save(toExchangeRate(exchangeRateDTO)).then();
    }

    private ExchangeRateDTO toExchangeRateDTO(ExchangeRate exchangeRate) {

        return ExchangeRateDTO.builder()
                .exchangeRate(exchangeRate.getExchangeRate())
                .date(exchangeRate.getDate())
                .targetCurrency(exchangeRate.getTargetCurrency())
                .build();
    }

    private ExchangeRate toExchangeRate(ExchangeRateDTO exchangeRateDTO) {

        return ExchangeRate.builder()
                .exchangeRate(exchangeRateDTO.getExchangeRate())
                .date(exchangeRateDTO.getDate())
                .targetCurrency(exchangeRateDTO.getTargetCurrency())
                .build();
    }
}
