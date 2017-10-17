package com.phonepe.service;

import com.phonepe.config.ServerProperties;
import com.phonepe.model.Consumer;
import com.phonepe.model.ConsumerDependencyGraph;
import com.phonepe.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Slf4j
@Component
public class RouterService {

    @Autowired
    ServerProperties properties;

    @Autowired
    private MessageDeliveryService messageDeliveryService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ConsumerDependencyGraph cDGraph;

    @Autowired
    private List<Consumer> consumerList;

    private int size;

    private int QUEUE_SIZE = 1024;
    private Queue<Message> messageQueue;
    private Thread watcherThread;

    public RouterService() {
        messageQueue = new ArrayBlockingQueue<Message>(QUEUE_SIZE, true);
        this.startListening();
    }


    public boolean publishMessage(Message message){
        // handles locking on the queue, whether required ?
        return messageQueue.offer(message);
    }

    public void startListening(){
        watcherThread = new Thread(){
            @Override
            public void run() {
                try {
                    while (true) {
                        while (!messageQueue.isEmpty()) {
                            Message message = messageQueue.poll();
                            consumerService.consumeMessage(message);
                        }
                        // sleep since no messages in queue
                        sleep(1000L);
                    }
                } catch (InterruptedException e) {
                    watcherThread = null;
                    e.printStackTrace();
                }
            }
        };
        watcherThread.start();
    }

    public void shutdownListener(){
        Thread thread = watcherThread;
        if (thread != null)
            thread.interrupt();
    }
}
