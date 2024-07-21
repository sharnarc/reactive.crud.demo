package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

/**
 * <code>UserRepository</code> is common reactive repository interface
 *
 * @author SUMANPattanaik
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String>{
    /**
     * Returns the total count of users based on startTime
     *
     * @param startTime String
     * @return Flux<User>
     */
    @Query("SELECT * FROM users WHERE modified_date >= $1 order by modified_date asc")
    Flux<User> findAllByStartTime(Timestamp startTime);


    @Override
    Mono<User> findById(String s);

}
