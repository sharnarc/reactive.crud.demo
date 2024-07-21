package com.example.reactive.crud.demo.mapper;


import com.example.reactive.crud.demo.entity.User;
import com.example.reactive.crud.demo.model.UserAccount;
import org.mapstruct.Mapper;

/**
 * <code>UserMapper</code> is mapper between User Account and User in database
 *
 * @author SUMANPattanaik
 */
@Mapper(uses = TimestampStringMapper.class)
public interface UserMapper {
    /**
     * Map UserAccount information from model to User entity
     *
     * @param user User
     * @return UserAccount
     */
    UserAccount toUserAccount(User user);

    /**
     * Map User entity information to UserAccount model
     *
     * @param userAccount UserAccount
     * @return User
     */
    User toUser(UserAccount userAccount);
}
