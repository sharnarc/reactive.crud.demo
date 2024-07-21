package com.example.reactive.crud.demo.mapper;

import com.example.reactive.crud.demo.entity.User;
import com.example.reactive.crud.demo.model.UserAccount;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-16T11:22:33+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    private final TimestampStringMapper timestampStringMapper = new TimestampStringMapper();

    @Override
    public UserAccount toUserAccount(User user) {
        if ( user == null ) {
            return null;
        }

        UserAccount.UserAccountBuilder userAccount = UserAccount.builder();

        userAccount.userId( user.getUserId() );
        userAccount.userName( user.getUserName() );
        userAccount.status( user.getStatus() );
        userAccount.lockedStatus( user.getLockedStatus() );
        userAccount.carriers( user.getCarriers() );
        userAccount.entitlements( user.getEntitlements() );
        userAccount.registeredOn( user.getRegisteredOn() );
        userAccount.modifiedOn( timestampStringMapper.toString( user.getModifiedOn() ) );

        return userAccount.build();
    }

    @Override
    public User toUser(UserAccount userAccount) {
        if ( userAccount == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userAccount.getUserId() );
        user.userName( userAccount.getUserName() );
        user.status( userAccount.getStatus() );
        user.lockedStatus( userAccount.getLockedStatus() );
        user.carriers( userAccount.getCarriers() );
        user.entitlements( userAccount.getEntitlements() );
        user.registeredOn( userAccount.getRegisteredOn() );
        user.modifiedOn( timestampStringMapper.toTimestamp( userAccount.getModifiedOn() ) );

        return user.build();
    }
}
