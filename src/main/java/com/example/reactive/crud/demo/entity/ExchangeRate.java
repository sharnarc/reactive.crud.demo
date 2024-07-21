package com.example.reactive.crud.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table("rate_of_exchange")
public class ExchangeRate {
    @Column("exchange_rate")
    private BigDecimal exchangeRate;
    @Column("target_currency")
    private String targetCurrency;
    @Column("date")
    private LocalDate date;
}
