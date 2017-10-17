package com.phonepe.service;

import com.phonepe.model.ConsumerDependencyGraph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

@Slf4j
@Component
public class MessageDeliveryServiceImpl implements MessageDeliveryService {


    public void deliveryUtil(int parentConsumerId, LinkedList<Integer> dependencyMatrix[], boolean[] visited, Stack stack) {
        visited[parentConsumerId] = true;
        Iterator<Integer> it = dependencyMatrix[parentConsumerId].iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            if (!visited[i]) {
                deliveryUtil(i, dependencyMatrix, visited, stack);
            }
        }
        stack.push(new Integer(parentConsumerId));
    }

    public Stack delivery(ConsumerDependencyGraph cDGraph) {

        int size = cDGraph.getGraphSize();
        boolean visited[] = new boolean[size + 1];

        Stack deliveryStackOfConsumerIds = new Stack();

        LinkedList<Integer> dependencyMatrix[] = cDGraph.getDependencyMatrix();

        for (int i = 1; i <= size; i++) {
            visited[i] = false;
        }

        for (int i = 1; i <= size; i++) {
            if (visited[i] == false) {
                deliveryUtil(i, dependencyMatrix, visited, deliveryStackOfConsumerIds);
            }
        }

        return deliveryStackOfConsumerIds;

    }
}
