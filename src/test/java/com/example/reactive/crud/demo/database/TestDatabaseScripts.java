package com.example.reactive.crud.demo.database;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.util.PSQLException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@Testcontainers
@Slf4j
public class TestDatabaseScripts {

    public static final String POSTGRES_IMAGE_VERSION = "postgres:15.2";
    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(POSTGRES_IMAGE_VERSION)
        .withDatabaseName("name")
        .withUsername("username")
        .withPassword("password");

    @Test
    @SneakyThrows
    void testDatabaseConnectionAndUpAndDown() {
        // Set up the database connection parameters
        String dbUrl = postgreSQLContainer.getJdbcUrl();
        String username = postgreSQLContainer.getUsername();
        String password = postgreSQLContainer.getPassword();

        boolean connected = false;
        while (!connected) {
            try {
                DriverManager.getConnection(dbUrl, username, password);
                connected = true;
            } catch (PSQLException e){
                log.info("Waiting for database to start");
                Thread.sleep(1000);
                connected = false;
            }
        }
        Connection connection = DriverManager.getConnection(dbUrl, username, password);

        // Read the SQL script file
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/schema5.sql"));
        StringBuilder scriptBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            scriptBuilder.append(line);
        }
        String script = scriptBuilder.toString();

        Statement statement = connection.createStatement();
        statement.executeUpdate(script);
        reader.close();

       // statement.executeUpdate("insert into employee (roll_number, first_name,last_name,address) values ('A101', 'Archie','Sharma','Bhopal')");
       // statement.executeUpdate("insert into student (first_name,last_name,course_id) values ( 'Archie','Sharma',9)");

      //  statement.executeUpdate("update employee set roll_number = 'A103' where roll_number = 'A101'");
        statement.executeUpdate("insert into agent_requested_invoices_state (scac, agent_customer_code,customer_code,invoice_number,bl_number,status,requester_name) values ('MAEU', 'DK000007951','INV1234','BL12345','Approved','abc','abc')");
        ResultSet rs = statement.executeQuery("select count(*) from agent_requested_invoices_state");
        rs.next();
        int count = rs.getInt(1);
        log.info("Number of rows in agent_requested_invoices_state: {} ", count);
        assertEquals(1, count);

    }

}
