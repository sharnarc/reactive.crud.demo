package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Student;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
@Repository
public interface StudentRepository extends R2dbcRepository<Student,Long> {

    Flux<List<Student>> findByAge(Integer age);
}
