package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Departments;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DepartmentsRepository extends ReactiveCrudRepository<Departments, String> {

}
