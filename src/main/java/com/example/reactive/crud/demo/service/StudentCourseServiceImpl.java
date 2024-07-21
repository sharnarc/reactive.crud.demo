package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.Course;
import com.example.reactive.crud.demo.entity.Student;
import com.example.reactive.crud.demo.model.StudentCourseDto;
import com.example.reactive.crud.demo.repository.CourseRepository;
import com.example.reactive.crud.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public Flux<StudentCourseDto> findAllEmployeeDepartments() {
        return studentRepository.findAll()
                .flatMap(student -> courseRepository.findById(student.getCourseId())
                .map(course -> new StudentCourseDto(student, course)));

    }

    @Override
    public Flux<List<Student>> findAllStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }
 
}
