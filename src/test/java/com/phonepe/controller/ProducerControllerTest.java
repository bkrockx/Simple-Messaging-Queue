package com.phonepe.controller;

import com.phonepe.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProducerControllerTest {

    @LocalServerPort
    private int port;


    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Test
    public void testShouldProduceASingleMessage() throws Exception {
        String input = "{\n" +
                "\t\"content\": \"aaa some value\"\n," +
                "\t\"id\": \"some id\"\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<String>(input, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/producer/produceMessage"),
                HttpMethod.POST, entity, String.class);

    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}