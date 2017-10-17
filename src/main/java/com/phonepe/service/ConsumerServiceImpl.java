package com.phonepe.service;

import com.phonepe.model.Consumer;
import com.phonepe.model.ConsumerDependencyGraph;
import com.phonepe.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryException;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDependencyGraph consumerDependencyGraph;

    @Autowired
    private List<Consumer> consumerList;

    private Stack consumerOrdering;

    public ConsumerServiceImpl(MessageDeliveryService messageDeliveryService,
                               ConsumerDependencyGraph consumerDependencyGraph,
                               List<Consumer> consumerList){
        this.consumerList = consumerList;
        this.consumerDependencyGraph = consumerDependencyGraph;
        this.consumerOrdering = messageDeliveryService.delivery(consumerDependencyGraph);
    }

    public Consumer getConsumerById(Integer id, List<Consumer> consumerList){
        for(Consumer consumer : consumerList){
            if(id == consumer.getId()){
                return consumer;
            }
        }
        return null;
    }

    public void handleDeliveryFailure(Integer consumerId){
        System.out.println("Delivery failure for consumer with id : " + consumerId);
    }

    public void consumeMessage(Message msg) {

        Stack stack = new Stack();
        stack.addAll(this.consumerOrdering);

        Set<Integer> invalidIds = new HashSet<Integer>();
        while(!stack.empty()){
            Integer consumerId = (Integer) stack.pop();
            if (invalidIds.contains(consumerId)){
                handleDeliveryFailure(consumerId);
                continue;
            }

            Consumer consumer = getConsumerById(consumerId,consumerList);
            if(msg.getContent().contains(consumer.getMsgPreference())){
                try {
                    consumer.handleNewMessage(msg.getContent());
                }catch (RetryException e) {
                    handleDeliveryFailure(consumerId);
                    for(Integer id : consumerDependencyGraph.getDependencies(consumerId)){
                        invalidIds.add(id);
                    }
                }
            }
        }
    }
}
