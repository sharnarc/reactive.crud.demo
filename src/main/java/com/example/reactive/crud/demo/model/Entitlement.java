package com.example.reactive.crud.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entitlement {
    @JsonProperty("schemaHint")
    public String schemaHint;
    @JsonProperty("defaultRoles")
    public List<String> defaultRoles;
    @JsonProperty("customers")
    public List<Customer> customers;

}
