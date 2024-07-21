package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Employee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends R2dbcRepository<Employee,Long> {

    Mono<Employee> findByRollNumber(String rollNumber);
    Flux<Employee> findAllByDeptId(Integer rollNumber);
    Mono<Employee> findByRollNumberAndFirstName(String rollNumber,String name);

}
