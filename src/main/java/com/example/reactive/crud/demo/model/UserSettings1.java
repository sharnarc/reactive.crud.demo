package com.example.reactive.crud.demo.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <code>UserAccountSettings</code> is model class for the User Settings Response.
 *
 * @author Sunny Kumar Verma
 */
@Data
@Builder
public class UserSettings1 {
    List<Settings> userAccountSettings;
}
