package com.webapp.revShop.controller;

import com.webapp.revShop.service.UserService;
import com.webapp.revShop.service.UserServiceImpl;
import com.webapp.revShop.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserControllerImpl implements UserController{
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(@RequestBody Map<String, String> requestMap) {
        try
        {
            return userService.signUp(requestMap);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        try
        {
            return userService.login(requestMap);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try
        {
            return userService.getAllUsers();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap)
    {
        try
        {
            return userService.update(requestMap);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("SOMETHING WENT WRONG IN UserController line 70", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
