package com.example.GreetingApp.service;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting getGreeting(String firstName, String lastName) {
        String message;
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) {
            message = "Namaste User!!";
        } else {
            String fullName = (firstName != null ? firstName : "") +
                    (lastName != null ? " " + lastName : "");
            message = "Namaste " + fullName.trim();
        }

        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public Greeting getGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.orElseThrow(() -> new RuntimeException("No greeting found with id: " + id));
    }

    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }
}