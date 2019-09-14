package com.example.hateoas.domain;

import org.springframework.hateoas.ResourceSupport;

public class Greet extends ResourceSupport {
    private String message;
    public Greet(String message) {
    this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
