package com.example.reactive.crud.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <code>UserAccount</code> is user account model object
 *
 * @author SUMANPattanaik
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount implements Serializable {
    @JsonProperty("accountId")
    private String userId;

    @JsonProperty("fullUserName")
    private String userName;

    @JsonProperty("userAccountStatus")
    private String status;

    @JsonProperty("lockAccountStatus")
    private String lockedStatus;

    @JsonProperty("carriers")
    private String carriers;

    @JsonProperty("entitlements")
    private String entitlements;

    @JsonProperty("createTimeUTC")
    private String registeredOn;

    @JsonProperty("updateTimeUTC")
    private String modifiedOn;
}
