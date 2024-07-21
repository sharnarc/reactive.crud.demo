package com.example.reactive.crud.demo.mapper;

import com.example.reactive.crud.demo.entity.UserSettings;
import com.example.reactive.crud.demo.model.UserAccountSetting;
import org.mapstruct.Mapper;

/**
 * <code>SettingMapper</code> is mapper between Settings and User Setting in database
 *
 * @author Sunny Kumar Verma
 */
@Mapper
public interface SettingMapper {
    /**
     * Map User Settings information from model to Settings entity
     *
     * @param userSettings UserSettings
     * @return UserAccountSetting
     */
    UserAccountSetting toUserAccountSettings(UserSettings userSettings);

    /**
     * Map Settings entity information to User Settings model
     *
     * @param userAccountSetting UserAccountSetting
     * @return UserSettings
     */
    UserSettings toUserSettings(UserAccountSetting userAccountSetting);
}
