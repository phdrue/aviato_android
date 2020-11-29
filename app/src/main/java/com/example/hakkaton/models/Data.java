package com.example.hakkaton.models;


import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("token")
    private String token;
    @SerializedName("user")
    User user;

    @SerializedName("userdata")
    private Userdata userdata;

    @SerializedName("message")
    String message;


    public String getMessage() {
        return message;
    }

    public Data(String message) {
        this.message = message;
    }

    public String isStatus() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public Userdata getUserdata() {
        return userdata;
    }

    public Data(String token, ResponseData response, Userdata userdata) {
        this.token = token;
        this.user = user;
        this.userdata = userdata;
    }




    public User getResponse() {
        return user;
    }
    public String getToken(){return token;}



   /* public void setResponse(ResponseData response) {
        this.response = response;
    }


    public void setStatus(String status) {
        this.token = token;
    }*/




}
