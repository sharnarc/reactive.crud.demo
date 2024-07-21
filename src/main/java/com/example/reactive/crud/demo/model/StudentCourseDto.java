package com.example.reactive.crud.demo.model;

import com.example.reactive.crud.demo.entity.Course;
import com.example.reactive.crud.demo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentCourseDto {

    private final Student student;
    private final Course course;
}
