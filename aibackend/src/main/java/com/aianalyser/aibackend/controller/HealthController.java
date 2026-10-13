
package com.aianalyser.aibackend.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:5173")
public class HealthController {

    private final JdbcTemplate jdbcTemplate;

    public HealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/api/health")
    public Map<String, Object> getHealth() {
        String database = jdbcTemplate.queryForObject(
                "SELECT current_database()",
                String.class
        );

        return Map.of(
                "status", "UP",
                "database", "CONNECTED",
                "databaseName", database
        );
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello Working";
    }
}
