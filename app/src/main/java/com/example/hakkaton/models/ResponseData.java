package com.example.hakkaton.models;

import com.google.gson.annotations.SerializedName;

public class ResponseData {
    @SerializedName("user")
    private User user;

    @SerializedName("token")
    private Token token;

    //private String token;
    //----------------
    public ResponseData(User user, Token token) {

        this.user = user;
        this.token = token;
    }



    public User getData() {
        return user;
    }

    public void setData(User data) {
        this.user = data;
    }

   public Token getToken() {
        return token;
    }

   /* public void setToken(String token) {
        this.token = token;
    }*/





}
