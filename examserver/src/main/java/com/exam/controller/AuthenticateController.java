package com.exam.controller;


import com.exam.config.jwtUtils;
import com.exam.model.User;
import com.exam.model.jstResponse;
import com.exam.model.jwtRequest;
import com.exam.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {


          @Autowired
          private AuthenticationManager authenticationManager;

          @Autowired
          private UserDetailsServiceImpl userDetailsService;

          @Autowired
          private com.exam.config.jwtUtils jwtUtils;



//          Genrate Token


    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody jwtRequest jwtrequest) throws Exception {
        try{

            authenticate(jwtrequest.getUsername(), jwtrequest.getPassword());
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found");
        }

//        User is Autheticated!!!!-----

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtrequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new jstResponse(token));



    }


    private  void authenticate(String username, String password) throws Exception {

        try {
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (DisabledException e){
            throw new Exception("User Disabled");
        }
        catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials  " + e.getLocalizedMessage());

        }

    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
    return  ((User) this.userDetailsService.loadUserByUsername(principal.getName()));

    }
}
