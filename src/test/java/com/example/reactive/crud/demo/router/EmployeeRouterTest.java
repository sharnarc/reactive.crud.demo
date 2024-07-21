package com.example.reactive.crud.demo.router;

import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.service.EmployeeService;
import com.example.reactive.crud.demo.testconfig.ContainerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.shaded.com.google.common.net.HttpHeaders;
import reactor.core.publisher.Mono;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
//@ContainerTest
public class EmployeeRouterTest {

    private WebTestClient webTestClient;
    @Autowired
    private ApplicationContext context;
    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void restrictPostTest() {
        var employee =
                Employee.builder()
                        .id(33L)
                        .firstName("archie")
                        .lastName("sharma")
                        .address("bhopal")
                        .rollNumber("333")
                        .build();
        given(employeeService.saveAndReturnEmployee(any(Employee.class))).willReturn(Mono.just(employee));
        webTestClient
                .post()
                .uri("/employee/save")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue("""
                         {
                                 "rollNumber": "333",
                                 "firstName": "archie",
                                 "lastName": "sharma",
                                 "address": "bhopal"
                             }
                        """)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("{\"id\":33,\"rollNumber\":\"333\",\"firstName\":\"archie\",\"lastName\":\"sharma\",\"address\":\"bhopal\"}");

    }

    @Test
    void restrictPostTest1() {
        var employee =
                Employee.builder()
                        .id(33L)
                        .firstName("archie")
                        .lastName("sharma")
                        .address("bhopal")
                        .rollNumber("333")
                        .build();
        given(employeeService.saveAndReturnEmployee(any(Employee.class))).willReturn(Mono.just(employee));
        webTestClient
                .post()
                .uri("/employee/save")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue("""
                         {
                                 "rollNumber": "333",
                                 "firstName": "archie",
                                 "lastName": "sharma",
                                 "address": "bhopal"
                             }
                        """)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("""
                        {
                                "id": 33,
                                "rollNumber": "333",
                                "firstName": "archie",
                                "lastName": "sharma",
                                "address": "bhopal"
                            }
                        """);
    }

}
