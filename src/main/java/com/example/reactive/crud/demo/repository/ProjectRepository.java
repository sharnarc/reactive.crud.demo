package com.example.reactive.crud.demo.repository;

import com.example.reactive.crud.demo.entity.Project;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectRepository extends R2dbcRepository<Project,Long> {

  Mono<Project> findByProjectId(String projectId);
}
