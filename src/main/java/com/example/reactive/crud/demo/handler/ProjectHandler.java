package com.example.reactive.crud.demo.handler;

import com.example.reactive.crud.demo.entity.Employee;
import com.example.reactive.crud.demo.entity.Project;
import com.example.reactive.crud.demo.service.EmployeeService;
import com.example.reactive.crud.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ProjectHandler {
    private final ProjectService projectService;
    public Mono<ServerResponse> getProjectDetails(ServerRequest serverRequest) {

        var projectId = serverRequest.pathVariable("projectId");
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(projectService.findByProjectId(projectId), Project.class);
    }



}
