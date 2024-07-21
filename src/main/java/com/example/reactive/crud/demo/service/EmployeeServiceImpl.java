package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.Department;
import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.model.BranchDTO;
import com.example.reactive.crud.demo.model.DepartmentDTO;
import com.example.reactive.crud.demo.model.EmployeeDTO;
import com.example.reactive.crud.demo.model.UpdateResponseDTO;
import com.example.reactive.crud.demo.repository.BranchRepository;
import com.example.reactive.crud.demo.repository.DepartmentRepository;
import com.example.reactive.crud.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private BranchRepository branchRepository;
    @Override
    public Mono<Employee> findByRollNumber(String rollNumber) {
        return employeeRepository.findByRollNumber(rollNumber);
    }

    @Override
    public Mono<Void> saveEmployee(Employee employee) {
        return employeeRepository.save(employee).then();
    }

    @Override
    public Mono<Employee> findEmployeeByRollNumber(String rollNumber) {

        return employeeRepository.findByRollNumber(rollNumber);

    }


    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }


    @Override
    public Mono<UpdateResponseDTO> updateAndReturnProjectResponse(EmployeeDTO employeeDTO,String parseLong) {
     //  UpdateResponseDTO response = new UpdateResponseDTO();
      /*  response.setSuccessfulIds(new ArrayList<>());
        response.setFailedIds(new ArrayList<>());

        employeeRepository.findByRollNumber(parseLong)
                .flatMap(existingProject -> {
                    response.getSuccessfulIds().add(existingProject.getRollNumber());

                    existingProject.setAddress(employeeDTO.getAddress()); // Example: Update fields as needed

                    return employeeRepository.save(existingProject);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    // Handle the case where the project with the specified ID was not found
                    response.getFailedIds().add(employeeDTO.getRollNumber());
                    return Mono.empty();
                }));


        return Mono.just(response);*/

        return employeeRepository.findByRollNumber(parseLong)
            .flatMap(existingEmployee -> {
                // Log existingEmployee to check if it's found
                System.out.println("Found employee: " + existingEmployee);
                existingEmployee.setFirstName(employeeDTO.getFirstName());
                employeeRepository.save(existingEmployee);

                // Update logic here (you might want to use a separate method)
                // ...

                // Assuming update is successful, return the updated project ID
                return Mono.just(new UpdateResponseDTO("Successfully Updated Employee ID: " ,existingEmployee.getRollNumber()));
            })
            .switchIfEmpty(Mono.just(new UpdateResponseDTO("Employee ID not found: " ,employeeDTO.getAddress())))
            .doOnNext(updateResponseDTO -> {
                // Log the final response
                System.out.println("Final response: " + updateResponseDTO);
            });
    }


    @Override
    public Flux<UpdateResponseDTO> updateEmployees(Flux<Integer> employeeIds) {
        return employeeIds.flatMap(this::updateEmployee);
    }


    @Override
    public Mono<Void> saveAllDepartment(Department department) {
        return departmentRepository.save(department).then();
    }


    @Override
    public Mono<EmployeeDTO> findEmployeeWithDept(String rollNumber) {
        return employeeRepository.findByRollNumber(rollNumber)
            .flatMap(employee -> departmentRepository.findByDeptId(employee.getDeptId())
                .map(department -> EmployeeDTO.builder()
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .rollNumber(employee.getRollNumber())
                    .deptName(department.getFirstName()).build()));

    }


    @Override
    public Flux<EmployeeDTO> findEmployeeWithDeptId(String deptId) {
        return employeeRepository.findAllByDeptId(Integer.parseInt(deptId))
            /*.map(employee -> EmployeeDTO.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .rollNumber(employee.getRollNumber())
                .departmentIds(List.of(employee.getDeptId())).build());*/

         .flatMap(employee -> departmentRepository.findByDeptId(employee.getDeptId())
            .map(department -> EmployeeDTO.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .rollNumber(employee.getRollNumber())
               // .deptName(department.getFirstName())
                .department(DepartmentDTO.builder().deptId(department.getDeptId()).firstName(department.getFirstName()).build())
                /*.departmentIds(List.of(employee.getDeptId()))*/.build()));
    }


    @Override
    public Mono<BranchDTO> findBranchWithStore(String branchId) {
        return branchRepository.findById(Integer.parseInt(branchId))
            .map(branch ->
                BranchDTO.builder()
                    .branchName(branch.getBranchName())
                    .city(branch.getCity())
                    .storeId(branch.getStore_id())
                    .branchNumber(branch.getBranchNumber()).build());
    }
    @Override
    public Flux<Department> getDepartmentsByEmployeeId(Integer employeeId) {
        return employeeRepository.findById(Long.valueOf(employeeId))
            .flatMapMany(employee -> Flux.fromIterable(employee.getDepartmentIds())
                .flatMap(departmentRepository::findByDeptId));
    }


    private Mono<UpdateResponseDTO> updateEmployee(Integer employeeId) {
        // Your update logic here
         Set<Integer> integerSet=new HashSet<>();
        return employeeRepository.findByRollNumber(String.valueOf(employeeId))
            .flatMap(employee -> {
                // Perform the update on the employee
                employee.setFirstName("UpdatedName"); // Replace with your update logic
                employeeRepository.save(employee);
                integerSet.add(employeeId);
                return Mono.just(new UpdateResponseDTO("Successfully Updated Employee ID: ", String.valueOf(employeeId)/*,integerSet*/));
            })
            .switchIfEmpty(
                Mono.just(new UpdateResponseDTO("Employee ID not found: ", String.valueOf(employeeId)/*,integerSet*/)))
            .onErrorResume(throwable -> {
                integerSet.add(employeeId);
                return Mono.just(new UpdateResponseDTO("Project ID not found: ", String.valueOf(employeeId)/*,integerSet*/));
            });
    }


    @Override
    public Mono<Void> saveAllEmployee(Employee employee) {
        return employeeRepository.save(employee).then();
    }

    @Override
    public Flux<Employee> saveAll(List<Employee> employeeList) {
        return employeeRepository.saveAll(employeeList);
    }

    @Override
    public Mono<Employee> saveAndReturnEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Employee> findByNumberAndName(String rollNumber, String name) {
        return employeeRepository.findByRollNumberAndFirstName(rollNumber, name);
    }

    @Override
    public Flux<Employee> findByRollNumbers(List<String> numbers) {
        return Flux.fromIterable(numbers)
                .flatMap(rollNumber -> employeeRepository.findByRollNumber(rollNumber));
    }
}
