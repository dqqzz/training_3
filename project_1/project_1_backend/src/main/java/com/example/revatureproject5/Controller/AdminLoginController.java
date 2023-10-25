package com.example.revatureproject5.Controller;

import com.example.revatureproject5.JPA.RegisterRepository;
import com.example.revatureproject5.JPA.UserRepository;
import com.example.revatureproject5.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminLoginController {
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private UserRepository userRepository;
    private User user;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/adminlogin_Get")
    public User showForm(@RequestParam String userName, @RequestParam String password) {
        userName = userName.trim();
        password = password.trim();
//        User user=new User(userName, password);
//        userRepository.save(user);
        User existingUser = userRepository.findByUserName(userName);
        if (existingUser.getUserName()!=null) {
            // Compare the entered password with the stored user's password
            if (password.equals("david123")) {

                System.out.println("user name is: "+ existingUser.getUserName());
                // Passwords match, redirect to the desired page
                return existingUser;
            }
            return null;
        }
        // Username or password is incorrect, redirect to the error page
        return null;

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/adminlogin")
    public User handleLogin(@RequestParam String userName, @RequestParam String password) {
        userName = userName.trim();
        password = password.trim();
//        User user=new User(userName, password);

        // Retrieve the user by username
        User existingUser = userRepository.findByUserName(userName);

        if (existingUser != null) {
            // Compare the entered password with the stored user's password
            if (password.equals("david123")) {
                // Passwords match, redirect to the desired page
                return existingUser;
            }
            return null;
        }

        // Username or password is incorrect, redirect to the error page
        return null;
    }
}