package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Department;
import com.example.reactive.crud.demo.entity.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;


import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeAll
    public static void setup(){
        TestContainersInit.startContainer();
    }

    @AfterAll
    public static void stop(){
        TestContainersInit.stopContainer();
    }
    @AfterEach
    void cleanUp() {
        employeeRepository.deleteAll().block();
    }

    @Test
    void testFindByPaymentAdviceNumberReturnsCorrectData() {

       // var department= Department.builder().name("IT").build();

        var employee =
                Employee.builder()
                        .firstName("archie")
                        .lastName("sharma")
                        .address("bhopal")
                        .rollNumber("333")
                       // .department(department)
                        .build();

        StepVerifier.create(employeeRepository.save(employee))
                .assertNext(savedEmployee -> {
                    assertThat(savedEmployee.getId()).isNotNull();
                    assertThat(savedEmployee.getFirstName()).isEqualTo("archie");
                    assertThat(savedEmployee.getLastName()).isEqualTo("sharma");
                    assertThat(savedEmployee.getRollNumber()).isEqualTo("333");
                    assertThat(savedEmployee.getAddress()).isEqualTo("bhopal");
                })
                .expectComplete() // Ensure the publisher completes successfully
                .verify();

        StepVerifier.create(employeeRepository.findAll())
                .assertNext(foundEmployee -> {
                    assertThat(foundEmployee.getId()).isNotNull();
                    assertThat(foundEmployee.getFirstName()).isEqualTo("archie");
                    assertThat(foundEmployee.getLastName()).isEqualTo("sharma");
                    assertThat(foundEmployee.getRollNumber()).isEqualTo("333");
                    assertThat(foundEmployee.getAddress()).isEqualTo("bhopal");
                })
                .expectComplete()
                .verify();

        StepVerifier.create(employeeRepository.findByRollNumber("333"))
                .assertNext(foundEmployee -> {
                    assertThat(foundEmployee.getId()).isNotNull();
                    assertThat(foundEmployee.getFirstName()).isEqualTo("archie");
                    assertThat(foundEmployee.getLastName()).isEqualTo("sharma");
                    assertThat(foundEmployee.getRollNumber()).isEqualTo("333");
                    assertThat(foundEmployee.getAddress()).isEqualTo("bhopal");
                })
                .expectComplete()
                .verify();



    }

}
