package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Department;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DepartmentRepository extends R2dbcRepository<Department,Long> {

    Mono<Department> findByDeptId(Integer deptTd);
}
