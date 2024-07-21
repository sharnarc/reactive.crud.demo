package com.example.reactive.crud.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * <code>UserSettings</code> is user object as in postgres
 *
 * @author Sunny Kumar Verma
 */
@Table("user_settings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSettings {
    @Column("account_id")
    private String accountId;

    @Column("settings_id")
    private String settingsId;

    @Column("settings_value")
    private String settingsValue;
}