package com.exam.service.impl;

import com.exam.model.User;
import com.exam.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private com.exam.repository.userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if(user== null){
            System.out.println("User not found");
//            throw  new UsernameNotFoundException("No user Found");
        }
        else {
            return  user;
        }


        return null;
    }
}
