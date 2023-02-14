package com.jwt;

import lombok.extern.java.Log;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log
@Component
public class DatabaseHealthCheck implements InitializingBean {
    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                log.info("INFO: Database is up and running.");
            } else {
                log.severe("SEVERE: Unable to connect to database.");
                System.exit(1);
            }
        } catch (SQLException e) {
            log.severe("SEVERE: Unable to connect to database. \n" + e.getMessage());
            System.exit(1);
        }
    }
}