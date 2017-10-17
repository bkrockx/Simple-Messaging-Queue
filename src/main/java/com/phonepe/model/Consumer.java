package com.phonepe.model;

import com.phonepe.util.HTTP;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public class Consumer {

    private int id;
    private String url;
    private String msgPreference;
    private HTTP http;

    public Consumer(int id, String msgPreference, String url) {
        this.id = id;
        this.msgPreference = msgPreference;
        this.url = url;
        http = new HTTP();
    }

    public int getId() {
        return id;
    }


    public String getMsgPreference() {
        return msgPreference;
    }

    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(maxDelay = 2000))
    public void handleNewMessage(String message){
        ResponseEntity responseEntity = http.postRequest(url, message);
        if (responseEntity.getStatusCode().is2xxSuccessful() == false)
            throw new RetryException("callback request failed");
    }
}
