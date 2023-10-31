package com.webapp.revShop.service;

import com.webapp.revShop.entity.Users;
import com.webapp.revShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try
        {
            if(validateSignUpMap(requestMap))
            {
                Users user = userRepository.findByEmail(requestMap.get("email"));
                if(Objects.isNull(user))
                {
                    userRepository.save(getUserFromMap(requestMap));
                    return new ResponseEntity<String>("SUCCESSFULLY REGISTERED!", HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<String>("EMAIL ALREADY EXISTS!", HttpStatus.BAD_REQUEST);
                }
            }
            else
            {
                return new ResponseEntity<String>("INVALID DATA!", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("SOMETHING WENT WRONG IN VALIDATE SIGNUP SERVICE", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap)
    {
        if(requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("password"))
        {
            return true;
        }
        else
            return false;
    }

    private Users getUserFromMap(Map<String, String> requestMap) {

        Users user = new Users();
        user.setUser_name(requestMap.get("user_name"));
        user.setUser_email(requestMap.get("user_email"));
        user.setUser_password(requestMap.get("user_password"));
        //user.setUser_role(requestMap.get("user_role"));
        user.setUser_role("customer");

        return user;
    }

}
