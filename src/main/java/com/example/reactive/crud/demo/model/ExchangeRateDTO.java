package com.example.reactive.crud.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDTO {

    private BigDecimal exchangeRate;
    private String targetCurrency;
    private LocalDate date;

}
