package br.com.ifms.microservices.exception;

import java.util.Date;

import lombok.Data;


@Data
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String uri;

    public ExceptionResponse(String message, String uri) {
        this.timestamp = new Date();
        this.message = message;
        this.uri = uri;
    }
}
