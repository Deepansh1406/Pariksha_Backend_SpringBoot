package com.exam.model;


public class jstResponse {
    String token;

    public jstResponse(){

    }

    public jstResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
