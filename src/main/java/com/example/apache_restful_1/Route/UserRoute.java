package com.example.apache_restful_1.Route;

import com.example.apache_restful_1.Entity.User;
import com.example.apache_restful_1.Repository.UserRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoute extends RouteBuilder {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure() throws Exception {
        from("direct:saveUser")
                .process(exchange -> {
                    User user = exchange.getIn().getBody(User.class);
                    // Add a log statement here to check the user object
                    System.out.println("Processing user: " + user);

                    userRepository.save(user);
                });
    }
}
