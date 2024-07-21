package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.UserSettings;
import com.example.reactive.crud.demo.mapper.SettingMapper;
import com.example.reactive.crud.demo.model.Settings;
import com.example.reactive.crud.demo.model.UserSettings1;
import com.example.reactive.crud.demo.repository.UserSettingsRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserSettingImpl implements UserSetting {

    private final UserSettingsRepository settingsRepository;
    private static final String DEFAULT_SETTINGS_ID = "units";
    private static final String DEFAULT_SETTINGS_VALUE = "metric";
    @Override
    public Mono<Void> saveUserSettings(UserSettings userSettings) {
        return settingsRepository.save(userSettings).then();
    }

    @Override
    public Mono<UserSettings> findByAcctIdAndsettingId(String accountId, String settingId) {
       // return settingsRepository.findAllByAccountIdAndSettingsId(accountId, settingId);
        return null;
    }
    @Override
    public Mono<UserSettings1> getUserSettingsNew(String accountId, String settingId) {
        // Call Postgres DB to get the user settings and build the response

        //Logic to get all the user settings for the account id. If settingId is present, return that particular setting
        return settingsRepository.findAllByAccountIdAndSettingsId(accountId, settingId)
             /*   .collectList()
                .flatMap(userSettingList -> Mono.just(userSettingList)
                        .flatMapMany(Flux::fromIterable)*/
            .map(userSettings -> Mappers.getMapper(SettingMapper.class).toUserAccountSettings(userSettings))
            .map(userAccountSetting -> Settings.builder()
                .settingsId(userAccountSetting.getSettingsId())
                .settingsValue(userAccountSetting.getSettingsValue()).build())
            .collect(Collectors.toList())
            .filter(settingsData -> !settingsData.isEmpty())
            .map(settingsData ->
                UserSettings1.builder()
                    .userAccountSettings(settingsData)
                    .build())
            .defaultIfEmpty(UserSettings1.builder().userAccountSettings(Collections.singletonList(
                Settings.builder().settingsId(DEFAULT_SETTINGS_ID).settingsValue(DEFAULT_SETTINGS_VALUE).build())).build())
            .onErrorReturn(UserSettings1.builder().build());
        // ).onErrorReturn(UserSettings1.builder().build());
    }


    // ao-ciam-user-details have good example of repository and test cases
}
