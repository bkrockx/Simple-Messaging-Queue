package com.phonepe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.UUID.randomUUID;

public class Message {

    @JsonProperty("id")
    private String id;

    @JsonProperty("content")
    private String content;

    public Message(String content) {
        this.content = content;
        this.id = randomUUID().toString();
    }

    public Message(String id, String content) {
        this.content = content;
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public String getContent() {
        return content;
    }


}
