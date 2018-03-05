package org.capgemini.carmen.demo.webservice.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class MainControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void  addNewUser() {
        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)  // Build the base link
                .path("/demo/add")                            // Add path
                .queryParam("name", "carmen")
                .queryParam("email", "carmen@capgemini.com")
                .build()
                .encode()
                .toUri();

        String response = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(response).contains("carmen@capgemini.com");

    }

//    @Test
//    void getAllUsers() {
//    }
}