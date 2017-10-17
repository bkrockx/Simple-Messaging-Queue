package com.phonepe.service;

import com.phonepe.model.ConsumerDependencyGraph;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Stack;

@Component
public interface MessageDeliveryService {

    void deliveryUtil(int parentConsumerId, LinkedList<Integer> dependencyMatrix[], boolean visited[], Stack stack);

    Stack delivery(ConsumerDependencyGraph cDGraph);
}
