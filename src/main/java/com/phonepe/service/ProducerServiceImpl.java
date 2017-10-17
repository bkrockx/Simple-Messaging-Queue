package com.phonepe.service;

import com.phonepe.model.InputRequest;
import com.phonepe.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Slf4j
@Component
public class ProducerServiceImpl implements ProducerService {


    public Message produceNewMessage(String content) {
        Message msg = new Message(content);
        return msg;
    }

    public List<Message> produceBulkMessage(List<InputRequest> inputRequestList) {
        int size = inputRequestList.size();
        List<Message> messageList = new LinkedList<Message>();
        for(int i = 0; i < size; i++){
            InputRequest inputRequest = inputRequestList.get(i);
            Message msg = produceNewMessage(inputRequest.getContent());
            messageList.add(msg);
        }
        return messageList;
    }
}
