package com.phonepe.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputRequest {
    @JsonProperty("content")
    private String content;
    @JsonProperty("id")
    private String id;

    public InputRequest() {
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }
}
