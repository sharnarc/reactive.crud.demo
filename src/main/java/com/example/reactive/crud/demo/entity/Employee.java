package com.example.reactive.crud.demo.entity;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Table("employee")
public class Employee {
    @Id
    private Long id;
    @NonNull
    private String rollNumber;
    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
    @NonNull
    private Integer deptId;

    @Column("dept_id")
    private List<Integer> departmentIds;


}
