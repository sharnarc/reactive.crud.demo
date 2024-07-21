package com.example.reactive.crud.demo.service;

import com.example.reactive.crud.demo.entity.Project;
import com.example.reactive.crud.demo.repository.CourseRepository;
import com.example.reactive.crud.demo.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    @Override
    public Mono<Project> findByProjectId(String projectId) {
        return projectRepository.findByProjectId(projectId);
    }

}
