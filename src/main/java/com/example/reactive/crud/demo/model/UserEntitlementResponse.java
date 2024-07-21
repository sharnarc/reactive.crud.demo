package com.example.reactive.crud.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserEntitlementResponse {

    @JsonProperty("userAccounts")
    UserAccount userAccounts;
    @JsonProperty("entitlements")
    Entitlement entitlements;

}
