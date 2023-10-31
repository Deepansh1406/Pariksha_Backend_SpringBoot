package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {
    public  User createUser(User user, Set<UserRole> userRole);

//    Get user by username
    public  User  getUser(String username);

//    Delete user by ID
     public  void deleteUser(Long userId);


}
