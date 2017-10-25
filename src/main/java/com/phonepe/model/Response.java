package com.phonepe.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    @JsonProperty("message")
    private String message;

    public Response(String message){
        this.message = message;
    }
}
