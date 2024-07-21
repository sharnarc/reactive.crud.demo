package com.example.reactive.crud.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <code>UserSetting</code>
 *
 * @author SUMANPattanaik
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountSetting {
    private String accountId;
    private String settingsId;
    private String settingsValue;
}
