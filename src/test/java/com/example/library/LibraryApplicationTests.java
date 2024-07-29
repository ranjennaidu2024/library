package com.example.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("server.port", () -> "8081");
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAllBooksReturnsListOfBooks() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/books", String.class);
        assertThat(response.getBody()).contains("Book Title 1");
    }

    @Test
    void testGetAllBorrowersReturnsListOfBorrowers() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/borrowers", String.class);
        assertThat(response.getBody()).contains("Ranjen Naidu");
    }
}
