package com.example.reactive.crud.demo.mapper;

import com.example.reactive.crud.demo.entity.UserSettings;
import com.example.reactive.crud.demo.model.UserAccountSetting;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-17T10:50:22+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class SettingMapperImpl implements SettingMapper {

    @Override
    public UserAccountSetting toUserAccountSettings(UserSettings userSettings) {
        if ( userSettings == null ) {
            return null;
        }

        UserAccountSetting.UserAccountSettingBuilder userAccountSetting = UserAccountSetting.builder();

        userAccountSetting.accountId( userSettings.getAccountId() );
        userAccountSetting.settingsId( userSettings.getSettingsId() );
        userAccountSetting.settingsValue( userSettings.getSettingsValue() );

        return userAccountSetting.build();
    }

    @Override
    public UserSettings toUserSettings(UserAccountSetting userAccountSetting) {
        if ( userAccountSetting == null ) {
            return null;
        }

        UserSettings.UserSettingsBuilder userSettings = UserSettings.builder();

        userSettings.accountId( userAccountSetting.getAccountId() );
        userSettings.settingsId( userAccountSetting.getSettingsId() );
        userSettings.settingsValue( userAccountSetting.getSettingsValue() );

        return userSettings.build();
    }
}
