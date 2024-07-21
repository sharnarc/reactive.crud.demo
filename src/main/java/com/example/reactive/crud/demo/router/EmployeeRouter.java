package com.example.reactive.crud.demo.router;

import com.example.reactive.crud.demo.entity.Student;
import com.example.reactive.crud.demo.handler.EmployeeHandler;
import com.example.reactive.crud.demo.handler.ExchangeRateHandler;
import com.example.reactive.crud.demo.handler.SettingsHandler;
import com.example.reactive.crud.demo.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class EmployeeRouter {
    @Bean
    public RouterFunction route(EmployeeHandler employeeHandler/*, StudentHandler studentHandler, SettingsHandler settingsHandler, ExchangeRateHandler rateHandler*/) {
        return RouterFunctions.route()
                .path("/employee", builder -> builder
                        .POST("/saveok", employeeHandler::save)
                        .GET("/{number}", employeeHandler::findByRollNumber)
                        .GET("/branch/{branchid}", employeeHandler::findBranchAndStore)
                       // .GET("/dept/byempid/{empid}", employeeHandler::findDepartmentByEmployeeId)
                        .GET("/dept/{Rollnumber}", employeeHandler::findEmployeeWithDepartment)
                        .GET("/deptId/{deptid}", employeeHandler::findEmployeeByDeptId)
                        .GET("/rollnumber/{numbers}", employeeHandler::findByRollNumbers)
                        .GET("", employeeHandler::all)
                        .POST("/save", employeeHandler::saveAndReturn)
                        .POST("/saveall", employeeHandler::saveAll)
                        .POST("/saveDept", employeeHandler::saveAllDept)
                        .GET("/rollnumber/{name}/{rollNumber}", employeeHandler::findByNameAndRollNumber)
                        .GET("/department/{empid}", employeeHandler::findDepartmentByEmployeeId)
                        .POST("/saveflux", employeeHandler::saveAllFlux)
                        .DELETE("agent/{id}", employeeHandler::deleteAll)
                        .DELETE("/{id}", employeeHandler::deleteByRollNumber)
                        .PUT("", employeeHandler::updateByRollNumber)
                        .PUT("/update/{projectId}", employeeHandler::updateAll)
                        .PUT("/update", employeeHandler::updateAllWithoutIds)
                        .PATCH("",employeeHandler::update)

                )
 /*               .path("student/course", builder -> builder
                        .GET("/find", studentHandler::findAllStudentsWithCourse)
                        .GET("/find/{age}",studentHandler::findAllStudentsByAge)
                        .GET("/{accountId}/user-settings", settingsHandler::getUserSettings)
                        .POST("/user-settings", settingsHandler::saveUserSettings)

                )*/
             /*   .path("exchange/rate", builder -> builder
                        .POST("/save", rateHandler::saveRates)
                        .GET("/find/{date}/{targetCurrency}", rateHandler::findExchangeRateByDateAndTargetCurrency))*/

                .build();
    }
}
