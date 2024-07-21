package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.SettingIdentity;
import com.example.reactive.crud.demo.entity.UserSettings;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserSettingsRepository extends R2dbcRepository<UserSettings, SettingIdentity> {

 //   Mono<UserSettings> findAllByAccountIdAndSettingsId(String accountId, String settingId);
    Flux<UserSettings> findAllByAccountIdAndSettingsId(String accountId, String settingId);
}
