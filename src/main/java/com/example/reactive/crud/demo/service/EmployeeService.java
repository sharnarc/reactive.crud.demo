package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.Department;
import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.model.BranchDTO;
import com.example.reactive.crud.demo.model.EmployeeDTO;
import com.example.reactive.crud.demo.model.UpdateResponseDTO;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Mono<Employee> findByRollNumber(String rollNumber);
    Mono<Void> saveEmployee(Employee employee);
    Mono<Employee> findEmployeeByRollNumber(String rollNumber);
    Mono<Void> saveAllEmployee(Employee employee);

    Flux<Employee> saveAll(List<Employee> employeeList);

    Mono<Employee> saveAndReturnEmployee(Employee employee);

    Mono<Employee> findByNumberAndName(String rollNumber, String name);

    Flux<Employee> findByRollNumbers(List<String> numbers);

    Flux<Employee> findAll();


    Mono<UpdateResponseDTO> updateAndReturnProjectResponse(EmployeeDTO employeeDTO,String parseLong);


    Flux<UpdateResponseDTO> updateEmployees(Flux<Integer> employeeIds);


    Mono<Void>  saveAllDepartment(Department department);


    Mono<EmployeeDTO> findEmployeeWithDept(String rollNumber);


    Flux<EmployeeDTO> findEmployeeWithDeptId(String deptId);


    Mono<BranchDTO> findBranchWithStore(String branchId);
    Flux<Department> getDepartmentsByEmployeeId(Integer employeeId);


}
