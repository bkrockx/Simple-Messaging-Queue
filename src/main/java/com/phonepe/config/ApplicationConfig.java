package com.phonepe.config;

import com.phonepe.model.Consumer;
import com.phonepe.model.ConsumerDependencyGraph;
import com.phonepe.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;


@Configuration
public class ApplicationConfig {

    @Bean
    public ConsumerDependencyGraph consumerDependencyGraph(List<Consumer> consumerList){
        int size = consumerList.size();
        ConsumerDependencyGraph cDGraph = new ConsumerDependencyGraph(size);
        cDGraph.addDependents(1,2);
        cDGraph.addDependents(2,3);
        return cDGraph;
    }

    @Bean
    public List<Consumer> consumerList(){
        List consumerList = new LinkedList<Consumer>();
        String url = "http://localhost:8911/consumer/consumeMessage/";
        Consumer consumer1 = new Consumer(1,"aaa", url + "1");
        Consumer consumer2 = new Consumer(2,"bbb", url + "2");
        Consumer consumer3 = new Consumer(3,"aaa", url + "3");

        consumerList.add(consumer1);
        consumerList.add(consumer2);
        consumerList.add(consumer3);
        return consumerList;
    }

    @Bean
    public ConsumerService consumerService(MessageDeliveryService messageDeliveryService,
                                           ConsumerDependencyGraph consumerDependencyGraph,
                                           List<Consumer> consumerList){
        return new ConsumerServiceImpl(messageDeliveryService, consumerDependencyGraph, consumerList);

    }

    @Bean
    public MessageDeliveryService messageDeliveryService(){
        return new MessageDeliveryServiceImpl();
    }

    @Bean
    public ProducerService producerService(){
        return new ProducerServiceImpl();
    }
}
