package com.example.reactive.crud.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class RateIdentity implements Serializable {
    private String targetCurrency;
    private LocalDate date;
}
