package com.phonepe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.model.InputRequest;
import com.phonepe.model.Message;
import com.phonepe.model.Response;
import com.phonepe.service.ProducerService;
import com.phonepe.service.RouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/producer")
@Slf4j
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @Autowired
    RouterService routerService;

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(path = "/produceMessage",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity produceMessage(@RequestBody String jsonInput){
        log.info("Received a request to request to produce message to a consumer {} ", jsonInput);

        InputRequest inputRequest;

        try {
            inputRequest = mapper.readValue(jsonInput, InputRequest.class);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Message message = producerService.produceNewMessage(inputRequest.getContent());
        boolean status = routerService.publishMessage(message);

        if (status)
            return new ResponseEntity(new Response("Produced message"), HttpStatus.CREATED);
        else
            return new ResponseEntity(new Response("Unable to produce Message"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
