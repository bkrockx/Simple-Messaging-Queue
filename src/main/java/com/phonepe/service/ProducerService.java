package com.phonepe.service;

import com.phonepe.model.InputRequest;
import com.phonepe.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProducerService {

    Message produceNewMessage(String content);

    List<Message> produceBulkMessage(List<InputRequest> inputRequestList);
}
