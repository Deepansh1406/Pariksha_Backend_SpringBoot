package com.exam.controller;


import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");

//        Encoding password with b BCryptPasswordEncoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles= new HashSet<>();

        Role role= new Role();
        role.setRoleId(45L);
        role.setRoleName("Normal");

        UserRole userRole= new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
        return   this.userService.createUser(user,roles);

    }

    @GetMapping("/{username}")
    public  User getUser(@PathVariable("username") String username){
        return  this.userService.getUser(username);

    }

//    Delete the use by userID;

    @DeleteMapping("/{userId}")
    public  void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);


    }
}
