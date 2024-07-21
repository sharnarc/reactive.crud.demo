package com.example.reactive.crud.demo.handler;

import com.example.reactive.crud.demo.entity.Department;
import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.model.EmployeeDTO;
import com.example.reactive.crud.demo.model.UpdateResponseDTO;
import com.example.reactive.crud.demo.router.EmployeeRouter;
import com.example.reactive.crud.demo.service.EmployeeService;
import com.example.reactive.crud.demo.testconfig.ContainerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
//@ContainerTest

public class EmployeeHandlerTest {
    private WebTestClient webTestClient;
    private EmployeeHandler employeeHandler;
    private StudentHandler studentHandler;
    private SettingsHandler settingsHandler;
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = Mockito.mock(EmployeeService.class);
        employeeHandler = new EmployeeHandler(employeeService);
        webTestClient = WebTestClient.bindToRouterFunction(new EmployeeRouter().route(employeeHandler)).build();
    }

    @Test
    public void testCreateEmployee() {
        var employee =
                Employee.builder()
                        .firstName("archie")
                        .lastName("sharma")
                        .address("bhopal")
                        .rollNumber("333")
                        .build();

        when(employeeService.saveAndReturnEmployee(any(Employee.class)))
                .thenReturn(Mono.just(employee));

        webTestClient.post().uri("/employee/save")
                .body(Mono.just(employee), Employee.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("archie");
    }

    @ParameterizedTest
    @MethodSource("employeeProvider")
    public void testSaveEmployee(Employee employee) {
        when(employeeService.saveAndReturnEmployee(any(Employee.class)))
                .thenReturn(Mono.just(employee));
        Mono<Employee> result = webTestClient.post()
                .uri("/employee/save")
                .body(Mono.just(employee), Employee.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Employee.class)
                .getResponseBody()
                .next();

        StepVerifier.create(result)
                .expectNextMatches(savedEmployee -> savedEmployee.getId() != null)
                .expectComplete()
                .verify();
    }

    private static Stream<Employee> employeeProvider() {
        // Return a stream of test employee objects
        return Stream.of(
                new Employee(1L,"111","archie", "sharma","pune"),
                new Employee(2L,"222","raghu", "sharma","bhopal")
                // Add more test employees here
/*
                new Employee(1L,"111","archie", "sharma","pune",new Department(3L,"IT")),
                new Employee(2L,"222","raghu", "sharma","bhopal",new Department(3L,"Admin"))*/
        );
    }

    @Test
    public void testUpdateEmployee() {

        var responseDTO = UpdateResponseDTO.builder().message("updated").build();

        Flux<Integer> integerFlux = Flux.just(1, 2, 3);

        when(employeeService.updateEmployees(integerFlux))
            .thenReturn(Flux.just(responseDTO));

        webTestClient.put().uri("/employee/update")
            .body(Mono.just(integerFlux), UpdateResponseDTO.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.firstName").isEqualTo("archie");
    }

    @Test
    public void testUpdateAllEmployee() {

/*        var responseDTO = UpdateResponseDTO.builder().message("updated").build();
        EmployeeDTO employeeDTO = EmployeeDTO.builder().firstName("xyz").build();

        when(employeeService.updateAndReturnProjectResponse(employeeDTO,"1"))
            .thenReturn(Mono.just(responseDTO));



        webTestClient.put().uri("/employee/update/1")

            .body(employeeDTO, Employee.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.firstName").isEqualTo("archie");*/

/*
        var responseDTO = UpdateResponseDTO.builder().message("updated").build();
        EmployeeDTO employeeDTO = EmployeeDTO.builder().firstName("xyz").build();

        when(employeeService.updateAndReturnProjectResponse(employeeDTO,"1"))
            .thenReturn(Mono.just(responseDTO));*/


 /*       webTestClient.put()
            .uri("/employee/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(employeeDTO)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("empId").isEqualTo(1);*/
/*
        webTestClient.put()
            .uri("/employee/update/{projectId}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(Mono.just(employeeDTO))
            .exchange()
            .expectStatus().isOk()
            .expectBody(UpdateResponseDTO.class)
            .value(response -> {
                // Assert the response
                // For example, assert that the response contains the expected message
                assertEquals("Successfully Updated Employee ID: 123", response.getMessage());
            });*/

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        // Set other properties as needed

        String empId = "114";  // Sample empId

        // Prepare a sample UpdateResponseDTO
        UpdateResponseDTO responseDTO = new UpdateResponseDTO("Successfully Updated Employee ID: " + empId,"114");

        // Mock the behavior of the employeeService.updateAndReturnProjectResponse method
        when(employeeService.updateAndReturnProjectResponse(eq(employeeDTO), eq(empId)))
            .thenReturn(Mono.just(responseDTO));

        // Perform the HTTP request
        webTestClient.put()
            .uri("/employee/update/{projectId}", empId)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(employeeDTO)
            .exchange()
            .expectStatus().isOk()
            .expectBody(UpdateResponseDTO.class)
            .isEqualTo(responseDTO);

        // Verify that the employeeService.updateAndReturnProjectResponse method was called with the correct arguments
        verify(employeeService).updateAndReturnProjectResponse(eq(employeeDTO), eq(empId));
    }
    }



