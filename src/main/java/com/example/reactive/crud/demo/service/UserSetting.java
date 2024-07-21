package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.UserSettings;
import com.example.reactive.crud.demo.model.UserSettings1;
import reactor.core.publisher.Mono;

public interface UserSetting {

     Mono<Void> saveUserSettings(UserSettings userSettings);
     Mono<UserSettings> findByAcctIdAndsettingId(String accountId,String settingId);
     Mono<UserSettings1> getUserSettingsNew(String accountId, String settingId);
}
