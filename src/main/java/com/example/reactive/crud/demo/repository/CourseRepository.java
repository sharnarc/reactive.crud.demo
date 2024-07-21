package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Course;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends R2dbcRepository<Course,Long> {


}
