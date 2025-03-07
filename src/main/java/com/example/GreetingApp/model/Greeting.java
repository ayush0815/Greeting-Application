package com.example.GreetingApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields in JSON response
public class Greeting {
    private String firstName;
    private String lastName;
    private String message;

    public Greeting() {}

    public Greeting(String message) {
        this.message = message;
    }

    public Greeting(String firstName, String lastName, String message) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
    }

}