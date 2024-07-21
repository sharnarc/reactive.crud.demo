package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {


    @Autowired
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void testCreateEmployee() {
        var employee =
                Employee.builder()
                        .firstName("archie")
                        .lastName("sharma")
                        .address("bhopal")
                        .rollNumber("333")
                        .build();

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(Mono.just(employee));

        StepVerifier.create(employeeService.saveEmployee(employee))
                .expectComplete()
                .verify();
    }

}
