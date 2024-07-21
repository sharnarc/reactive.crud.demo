package com.example.reactive.crud.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <code>Settings</code> is model class for the Settings Response.
 *
 * @author Sunny Kumar Verma
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    private String settingsId;

    private String settingsValue;
}
