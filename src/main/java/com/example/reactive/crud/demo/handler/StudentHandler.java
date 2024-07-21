package com.example.reactive.crud.demo.handler;

import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.entity.Student;
import com.example.reactive.crud.demo.model.StudentCourseDto;
import com.example.reactive.crud.demo.model.UpdateResponseDTO;
import com.example.reactive.crud.demo.service.StudentCourseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
@AllArgsConstructor
@Slf4j
public class StudentHandler {

    StudentCourseService studentCourseService;

    public Mono<ServerResponse> findAllStudentsWithCourse(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentCourseService.findAllEmployeeDepartments(), StudentCourseDto.class);

    }

    public Mono<ServerResponse> findAllStudentsByAge(ServerRequest request) {

        String age = request.pathVariable("age");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentCourseService.findAllStudentsByAge(Integer.parseInt(age)), Student.class);
    }


}
