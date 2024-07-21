package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.entity.Project;
import reactor.core.publisher.Mono;

public interface ProjectService {
    Mono<Project> findByProjectId(String projectId);

}
