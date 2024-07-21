package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Employees;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeesRepository extends ReactiveCrudRepository<Employees, String> {

}
