package edu.java.scrapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostgresTest extends IntegrationTest {

    @Test
    void testPostgresContainerStarted() {
        assertTrue(POSTGRES.isRunning(), "PostgreSQL container should be running");
    }

}
