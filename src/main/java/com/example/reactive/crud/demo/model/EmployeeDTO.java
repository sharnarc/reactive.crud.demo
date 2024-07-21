package com.example.reactive.crud.demo.model;

import com.example.reactive.crud.demo.entity.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeDTO {
    private Long id;
    private String rollNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String deptName;
    private List<Integer> departmentIds;
    private DepartmentDTO department;

}
