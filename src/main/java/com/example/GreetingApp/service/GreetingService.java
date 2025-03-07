package com.example.GreetingApp.service;


import com.example.GreetingApp.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public Greeting getGreeting() {
        return new Greeting("Namaste User!!");
    }

    public Greeting postGreeting(Greeting greeting) {
        return new Greeting("Received message: " + greeting.getMessage());
    }


    public Greeting putGreeting(Greeting greeting) {
        return new Greeting("Received message: " + greeting.getMessage());
    }
}