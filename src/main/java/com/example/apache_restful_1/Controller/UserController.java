package com.example.apache_restful_1.Controller;

import com.example.apache_restful_1.Entity.User;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Add a log statement here to check the user object
        System.out.println("Received user: " + user);
        //producerTemplate.sendBody("direct:saveUser");
        producerTemplate.sendBody("direct:saveUser", user);

        return ResponseEntity.ok().build();
    }
}
