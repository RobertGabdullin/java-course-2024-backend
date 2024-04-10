package edu.java.scrapper;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostgresTest extends IntegrationTest {

    @Test
    void testPostgresContainerStarted() {
        assertTrue(POSTGRES.isRunning(), "PostgreSQL container should be running");
    }

}
