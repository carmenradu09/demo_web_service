package org.capgemini.carmen.demo.webservice.controllers;

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
                .queryParam("name", "Luana")
                .queryParam("email", "Luana@capgemini.com")
                .build()
                .encode()
                .toUri();

        String addUserResponse = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(addUserResponse).contains("Luana@capgemini.com");
    }

//    @Test
//    public void getUserById() {
//        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)
//                .path("/demo/all/{id}")
//                .queryParam("id", String.valueOf(1093))
//                .queryParam("email", "Luana@capgemini.com")
//                .build()
//                .encode()
//                .toUri();
//
//        String getUsersResponse = this.restTemplate.getForObject(targetUrl, String.class);
//        assertThat(getUsersResponse).contains(String.valueOf(1093));
//    }

    @Test
    public void getAllUsers() {
        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/demo/all")
                .queryParam("name", "Luana")
                .queryParam("email", "Luana@capgemini.com")
                .queryParam("name", "ioan")
                .queryParam("email", "ioan@capgemini.com")
                .build()
                .encode()
                .toUri();

        String getUsersResponse = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(getUsersResponse).contains("Luana@capgemini.com");
    }

    @Test
    public void updateUser() {
        URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/demo/update")
                .queryParam("name", "alexandru.alexandru")
                .queryParam("email", "cip@capgemini.com")
                .build()
                .encode()
                .toUri();

        String getUsersResponse = this.restTemplate.getForObject(targetUrl, String.class);
        assertThat(getUsersResponse.contains("alexandru.alexandru"));
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