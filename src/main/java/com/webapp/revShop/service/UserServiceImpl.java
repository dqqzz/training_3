package com.webapp.revShop.service;

import com.webapp.revShop.entity.Users;
import com.webapp.revShop.jwt.CustomerDetailService;
import com.webapp.revShop.jwt.JwtFilter;
import com.webapp.revShop.jwt.JwtUtil;
import com.webapp.revShop.repository.UserRepository;
import com.webapp.revShop.wrapper.UserWrapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

//com.webapp.revShop.wrapper.UserWrapper
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerDetailService customerDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try
        {
            if(validateSignUpMap(requestMap))
            {
                Users user = userRepository.findByEmail(requestMap.get("user_email"));
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

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try
        {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("user_email"), requestMap.get("user_password")));
            if (auth.isAuthenticated())
            {
                if(customerDetailService.getUserDetails().getUser_status().equalsIgnoreCase("true"))
                {
                    return new ResponseEntity<String>("{\"token\":\"" + jwtUtil.generateToken(customerDetailService.getUserDetails().getUser_email(), customerDetailService.getUserDetails().getUser_role()) + "\"}", HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<String>("{\"message\":\"" + "WAIT FOR ADMIN APPROVAL." + "\"}", HttpStatus.BAD_REQUEST);
                }
            }
            else
                return new ResponseEntity<String>("INVALID DATA.", HttpStatus.BAD_REQUEST);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"" + "BAD CREDENTIALS." + "\"}", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try
        {
            if(jwtFilter.isAdmin())
            {
                return new ResponseEntity<>(userRepository.getAllUsers(), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap)
    {
        try
        {
            if(jwtFilter.isAdmin())
            {
                Optional<Users> optional =userRepository.findById(Integer.parseInt(requestMap.get("user_id")));
                if(!optional.isEmpty())
                {
                    userRepository.updateStatus(requestMap.get("user_status"), Integer.parseInt(requestMap.get("user_id")));
                    return new ResponseEntity<String>("STATUS CHANGED!", HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<String>("USER DOES NOT EXIST.", HttpStatus.OK);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("UNAUTHORIZED ACCESS.", HttpStatus.UNAUTHORIZED);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap)
    {
        if(requestMap.containsKey("user_name") && requestMap.containsKey("user_email") && requestMap.containsKey("user_password") && requestMap.containsKey("user_role"))
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
        user.setUser_role(requestMap.get("user_role"));
        user.setUser_status("true");

        return user;
    }

}
