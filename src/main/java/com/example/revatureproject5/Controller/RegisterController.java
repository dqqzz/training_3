package com.example.revatureproject5.Controller;

import com.example.revatureproject5.JPA.UserRepository;
import com.example.revatureproject5.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

@Autowired
//private RegisterRepository registerRepository;
private UserRepository userRepository;

@CrossOrigin(origins = "http://localhost:3000")
@GetMapping("/register_Get")
public User showRegistrationForm(User user, @RequestParam("userName") String userName, @RequestParam("password") String password) {
    user.setUserName(userName);
    user.setPassword(password);
    userRepository.save(user) ;
    System.out.println("register successfully and user name is: "+user.getUserName());
    //return "redirect:/Login.html";
    return user;
    //return "successful";
}
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public User registerUser(User user, @RequestParam("userName") String userName, @RequestParam("password") String password) {
        // Check if a user with the same username already exists
        userName = userName.trim();
        password=password.trim();
        User existingUser = userRepository.findByUserName(userName);

        if (existingUser!=null) {
            // A user with the same username already exists, handle this case
            System.out.println("user name already existed");
            //return "redirect:/error.html?message=Username+already+exists";
           // return "redirect:/error.html";
             //return  "user name already existed";
            return null;

        } else {
            // Save the new user to the repository
            user.setUserName(userName);
            user.setPassword(password);
            userRepository.save(user) ;
            System.out.println("userId= "+user.getUserId());
            System.out.println("userName= "+user.getUserName());
            return user;
            //return "redirect:/Login.html";
            //return "redirect:/response=:.html?userName=" + user.getUserName() + "&password=" + user.getPassword();
//         return "add new user successfully";
        }

    }
}
