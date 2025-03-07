package com.example.GreetingApp.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "greetings")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public Greeting() {}

    public Greeting(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}