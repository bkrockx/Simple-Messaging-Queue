package com.phonepe.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chintu.kumar
 * @date 10/16/17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    @JsonProperty("message")
    private String message;

    public Response(String message){
        this.message = message;
    }
}
