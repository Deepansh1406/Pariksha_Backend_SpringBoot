package com.exam.model;

import org.springframework.security.core.GrantedAuthority;

public class Authoriry implements GrantedAuthority {

    private  String authority;

    public Authoriry(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
