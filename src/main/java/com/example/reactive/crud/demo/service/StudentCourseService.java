package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.Student;
import com.example.reactive.crud.demo.model.StudentCourseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StudentCourseService {

    Flux<StudentCourseDto> findAllEmployeeDepartments();

    Flux<List<Student>> findAllStudentsByAge(int parseInt);



}
