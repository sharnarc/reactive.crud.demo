package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.repository.EmployeeRepository;
import com.example.reactive.crud.demo.testconfig.ContainerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class EmployeeServiceTest1 {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @AfterEach
    void cleanUp() {
        employeeRepository.deleteAll().block();
    }


    @Test
    void testEmployeeByRollNumber() {
        var employee =
                Employee.builder()
                        .firstName("archie")
                        .lastName("sharma")
                        .address("bhopal")
                        .rollNumber("333")
                        .build();

        var saveAndFind = employeeRepository
                .save(employee)
                .then(employeeService.findByRollNumber("333"));

        StepVerifier.create(saveAndFind).consumeNextWith(emp -> {
            assertNotNull(emp.getId());
            assertEquals(emp.getFirstName(), "archie");

        }).verifyComplete();
    }

    @Test
    public void testSaveAndFindByRollNumber() {
        var employee =
                Employee.builder()
                        .firstName("archie")
                        .lastName("sharma")
                        .address("bhopal")
                        .rollNumber("333")
                        .build();


        StepVerifier.create(employeeRepository.save(employee)
                        .then(employeeService.findByRollNumber("333")))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

}
