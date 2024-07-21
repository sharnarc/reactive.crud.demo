package com.example.reactive.crud.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <code>UserResponse</code> is model class for the User Response.
 *
 * @author Sunny Kumar Verma
 */
@Data
@Builder
public class Response {
    @JsonProperty("userAccounts")
    List<UserAccount> userAccounts;
    int pageSize;
    int totalSize;
}
