package org.capgemini.carmen.demo.webservice.controllers;

import org.capgemini.carmen.demo.webservice.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
    private MockMvc mock;

    @Test
    public void  addNewUser() {
        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/demo/add")
                .queryParam("name", "carmen")
                .queryParam("email", "carmen@capgemini.com")
                .build()
                .encode()
                .toUri();

        String addUserResponse = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(addUserResponse).contains("carmen@capgemini.com");
    }

    @Test
    public void getUserById() {
        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/demo/all/{id}")
                .queryParam("id", Long.valueOf(1))
                .build()
                .encode()
                .toUri();

        String getUsersResponse = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(getUsersResponse).contains("flori@capgemini.com");
    }

    @Test
    public void getAllUsers() {
        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/demo/all")
                .queryParam("name", "carmen")
                .queryParam("email", "carmen@capgemini.com")
                .queryParam("name", "Maria")
                .queryParam("email", "maria.Ion@capgemini.com")
                .build()
                .encode()
                .toUri();

        String getUsersResponse = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(getUsersResponse).contains("carmen@capgemini.com");
    }

    @Test
    public void updateUser() {
        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/demo/update")
                .queryParam("name", "Luana-Maria")
                .queryParam("email", "Luana@capgemini.com")
                .build()
                .encode()
                .toUri();

        String getUsersResponse = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(getUsersResponse.contains("Luana-Maria"));
    }


    @Test
    public void deleteUser() {
        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/demo/delete")
                .queryParam("name", "Luana")
                .queryParam("email", "Luana@capgemini.com")
                .build()
                .encode()
                .toUri();

        String getUsersResponse = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(!(getUsersResponse).contains("Luana@capgemini.com"));
    }
}