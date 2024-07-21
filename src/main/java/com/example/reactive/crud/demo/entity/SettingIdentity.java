package com.example.reactive.crud.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <code>SettingIdentities</code> is model class for the Setting Value.
 *
 * @author Sunny Kumar Verma
 */
@Data
@Builder
public class SettingIdentity implements Serializable {
    private String accountId;
    private String settingsId;
}
