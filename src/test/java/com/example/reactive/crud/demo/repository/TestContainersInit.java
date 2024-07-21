package com.example.reactive.crud.demo.repository;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public abstract class TestContainersInit {

    public static PostgreSQLContainer container;
    public static Network network = Network.newNetwork();

    static {
        container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:9.6.18-alpine"))
                .withNetwork(network)
                .withDatabaseName("postgres")
                .withUsername("archie")
                .withPassword("archie")
                .withCopyFileToContainer(MountableFile.forClasspathResource("schema.sql"), "/docker-entrypoint-initdb.d/schema.sql");
    }

    public static void startContainer(){
        if(!container.isRunning()) {
            container.start();
            Startables.deepStart(container).join();
        }
    }

    public static void stopContainer(){
        if(container.isRunning()) {
            container.stop();
        }
    }

    @DynamicPropertySource
    public static void setDynamicProperties(DynamicPropertyRegistry registry){
        registry.add("spring.r2dbc.url", () -> "r2dbc:postgresql://127.0.0.1:" +
                container.getFirstMappedPort() + "/" + container.getDatabaseName());
        registry.add("spring.r2dbc.username", container::getUsername);
        registry.add("spring.r2dbc.password", container::getPassword);

    }

}
