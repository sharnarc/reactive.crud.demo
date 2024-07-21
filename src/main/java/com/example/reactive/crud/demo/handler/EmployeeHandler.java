package com.example.reactive.crud.demo.handler;

import com.example.reactive.crud.demo.entity.Department;
import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.model.DepartmentDTO;
import com.example.reactive.crud.demo.model.EmployeeDTO;
import com.example.reactive.crud.demo.model.StudentCourseDto;
import com.example.reactive.crud.demo.model.UpdateResponseDTO;
import com.example.reactive.crud.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EmployeeHandler {
    private final EmployeeService employeeService;

    public Mono<ServerResponse> save(ServerRequest serverRequest) {

        Mono<Employee> userMono = serverRequest.bodyToMono(Employee.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMono.flatMap(employeeService::saveEmployee), Void.class);

/*
        return serverRequest.bodyToMono(Employee.class)
                .flatMap(offer -> employeeService.saveEmployee(offer))
                .flatMap(p -> ServerResponse.ok().build());*/
    }

    public Mono<ServerResponse> saveAll(ServerRequest serverRequest) {

        Flux<Employee> employeeFlux = serverRequest.bodyToFlux(Employee.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeFlux.flatMap(employeeService::saveAllEmployee), Void.class);
    }

    public Mono<ServerResponse> saveAllFlux(ServerRequest serverRequest) {

        Mono<List<Employee>> listMono = serverRequest.bodyToMono(new ParameterizedTypeReference<List<Employee>>() {
        });
        Flux<Employee> employeeFlux = listMono.flatMapMany(employeeService::saveAll);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(employeeFlux, Employee.class);
    }

    public Mono<ServerResponse> saveAndReturn(ServerRequest serverRequest) {

        Mono<Employee> userMono = serverRequest.bodyToMono(Employee.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMono.flatMap(employeeService::saveAndReturnEmployee), Employee.class);
    }


    public Mono<ServerResponse> all(ServerRequest serverRequest) {
        return ServerResponse.ok().body(employeeService.findAll(), Employee.class);
    }

    public Mono<ServerResponse> findByRollNumber(ServerRequest serverRequest) {
        var rollNumber = serverRequest.pathVariable("number");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.findEmployeeByRollNumber(rollNumber), Employee.class);

    }

    public Mono<ServerResponse> findByRollNumbers(ServerRequest serverRequest) {
        List<String> numbers = Arrays.asList(serverRequest.pathVariable("numbers").split(","));
        return ServerResponse.ok()
                .body(employeeService.findByRollNumbers(numbers), Employee.class);
    }

    public Mono<ServerResponse> findByNameAndRollNumber(ServerRequest serverRequest) {
        Map<String, String> rollAndname = serverRequest.pathVariables();
        var rollNumber = rollAndname.get("rollNumber");
        var name = rollAndname.get("name");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.findByNumberAndName(rollNumber, name), Employee.class);
    }

    public Mono<ServerResponse> deleteByRollNumber(ServerRequest serverRequest) {

        return null;
    }

    public Mono<ServerResponse> deleteAll(ServerRequest serverRequest) {

        return null;
    }

    public Mono<ServerResponse> updateByRollNumber(ServerRequest serverRequest) {

        return null;
    }


    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> updateAll(ServerRequest serverRequest) {

        var empId = serverRequest.pathVariable("projectId");
/*        var empIds= projectIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toSet());*/

       // Long parseLong = Long.parseLong(empId);

        return serverRequest
            .bodyToMono(EmployeeDTO.class)
            .flatMap(employeeDTO -> ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(employeeService.updateAndReturnProjectResponse(employeeDTO,empId), UpdateResponseDTO.class));
    }


    public Mono<ServerResponse> updateAllWithoutIds(ServerRequest serverRequest) {


            Flux<String> employeeIds = serverRequest.bodyToFlux(String.class);
            Flux<Integer> employeeIds1 = serverRequest.bodyToFlux(Integer.class);

       // List<String> employeeIdsList = Arrays.asList("112", "113", "1144");
      //  Flux<String> employeeIdsFlux = Flux.fromIterable(employeeIdsList);

            return ServerResponse.ok()
                .body(employeeService.updateEmployees(employeeIds1), UpdateResponseDTO.class);
        }


    public Mono<ServerResponse> saveAllDept(ServerRequest serverRequest) {
        Flux<Department> departmentFlux = serverRequest.bodyToFlux(Department.class);
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(departmentFlux.flatMap(employeeService::saveAllDepartment), Void.class);

    }


    public Mono<ServerResponse> findEmployeeWithDepartment(ServerRequest serverRequest) {

        var rollNumber = serverRequest.pathVariable("Rollnumber");
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(employeeService.findEmployeeWithDept(rollNumber), EmployeeDTO.class);
    }


    public Mono<ServerResponse> findEmployeeByDeptId(ServerRequest serverRequest) {

        var deptId = serverRequest.pathVariable("deptid");
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(employeeService.findEmployeeWithDeptId(deptId), EmployeeDTO.class);
    }


    public Mono<ServerResponse> findBranchAndStore(ServerRequest serverRequest) {
        var branchId = serverRequest.pathVariable("branchid");
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(employeeService.findBranchWithStore(branchId), EmployeeDTO.class);

    }


    public Mono<ServerResponse> findDepartmentByEmployeeId(ServerRequest serverRequest) {
        var empid = serverRequest.pathVariable("empid");
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(employeeService.getDepartmentsByEmployeeId(Integer.valueOf(empid)), DepartmentDTO.class);
    }

}
