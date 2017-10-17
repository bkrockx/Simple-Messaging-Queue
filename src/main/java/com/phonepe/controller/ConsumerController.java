package com.phonepe.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/consumeMessage")
@Slf4j
public class ConsumerController {

    @RequestMapping(path = "/1",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity requestForConsumingMessageForConsumer1(@RequestBody String jsonInput){
        log.info("Received a request to request to consume message for consumer 1 {} ", jsonInput);

        log.info("Returning the response for consumer 1");
        return new ResponseEntity(jsonInput, HttpStatus.CREATED);
    }


    @RequestMapping(path = "/2",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity requestForConsumingMessageForConsumer2(@RequestBody String jsonInput){
        log.info("Received a request to request to consume message for consumer 2 {} ", jsonInput);

        log.info("Returning the response for consumer 2");
        return new ResponseEntity(jsonInput, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/3",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity requestForConsumingMessageForConsumer3(@RequestBody String jsonInput){
        log.info("Received a request to request to consume message for consumer 3 {} ", jsonInput);

        log.info("Returning the response for consumer 3");
        return new ResponseEntity(jsonInput, HttpStatus.CREATED);
    }
}
