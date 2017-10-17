package com.phonepe.service;

import com.phonepe.model.Consumer;
import com.phonepe.model.ConsumerDependencyGraph;
import com.phonepe.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConsumerService {

    void consumeMessage(Message messageList);

    Consumer getConsumerById(Integer id, List<Consumer> consumerList);
}
