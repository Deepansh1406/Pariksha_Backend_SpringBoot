package com.exam.service.impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.roleRepository;
import com.exam.repository.userRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private com.exam.repository.userRepository userRepository;

    @Autowired
    private com.exam.repository.roleRepository roleRepository;



//    USer Creation
    @Override
    public User createUser(User user, Set<UserRole> userRole) {

        User local = this.userRepository.findByUsername(user.getUsername());

        if(local!=null){
            System.out.println("User already exist");
//            throw  new Exception("User is already");
        }
        else {
            //user create
            for(UserRole ur:userRole){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRole);
            local =this.userRepository.save(user);


        }

        return local;
    }



//    Getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
