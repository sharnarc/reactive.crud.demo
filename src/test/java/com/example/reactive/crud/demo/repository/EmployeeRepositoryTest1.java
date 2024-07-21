package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.testconfig.ContainerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContainerTest
public class EmployeeRepositoryTest1 {


    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void cleanUp() {
        employeeRepository.deleteAll().block();
    }

    @Test
    void testFindByPaymentAdviceNumberReturnsCorrectData() {
        var employee =
                Employee.builder()
                        .firstName("archie")
                        .lastName("sharma")
                        .address("bhopal")
                        .rollNumber("333")
                        .build();

        var saveAndFind = employeeRepository
                .save(employee)
                .then(employeeRepository.findByRollNumber("333"));
        StepVerifier.create(saveAndFind).consumeNextWith(employee1 -> {
            assertNotNull(employee1.getId());
            assertEquals(employee1.getFirstName(), "archie");
            assertEquals(employee1.getLastName(), "sharma");
            assertEquals(employee1.getRollNumber(), "333");
            assertEquals(employee1.getAddress(), "bhopal");
        }).verifyComplete();
    }
}
