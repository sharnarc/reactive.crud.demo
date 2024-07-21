package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Branch;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BranchRepository extends R2dbcRepository<Branch,Long> {

    Mono<Branch> findById(Integer id);

}
