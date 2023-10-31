package com.webapp.revShop.jwt;

import com.webapp.revShop.entity.Users;
import com.webapp.revShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

//com.webapp.revShop.jwt.CustomerDetailService
@Service
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private Users userEntity;


    /**
     * Takes a username/email and finds a corresponding entry in the repository to fetch its email and passwor
     * and store it as a org.springframework.security.core.userdetails.User entity.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userEntity = userRepository.findByEmail(username);
        if(!Objects.isNull(userEntity))
            return new User(userEntity.getUser_email(), userEntity.getUser_password(), new ArrayList<>());
        else
            throw new UsernameNotFoundException("USER DOES NOT EXIST.");
    }

    public Users getUserDetails()
    {
        Users user = userEntity;
        user.setUser_password(null);
        return user;
    }


}
