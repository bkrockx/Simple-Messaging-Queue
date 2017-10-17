package com.phonepe.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
public class HTTP {

    RestTemplate template;

    public ResponseEntity postRequest(String url, String input) {
        RequestEntity requestEntity = null;
        ResponseEntity responseEntity;
        template = new RestTemplate();
        URI uri;
        try {
            uri = new URI(url);
            requestEntity = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(input, String.class);
        } catch (URISyntaxException e) {
            log.info("There was exception in url construction {}", e.getMessage());
        }

        responseEntity = template.exchange(requestEntity, String.class);
        return responseEntity;
    }


}
