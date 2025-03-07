package com.example.GreetingApp.service;

import com.example.GreetingApp.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public Greeting getGreeting(String firstName, String lastName) {
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) {
            return new Greeting("Namaste Usr!!");
        }

        String fullName = (firstName != null ? firstName : "") +
                (lastName != null ? " " + lastName : "");

        return new Greeting("Namaste " + fullName.trim());
    }
}