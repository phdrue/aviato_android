package com.example.hakkaton.api;

public class Retrofit {

    private retrofit2.Retrofit retrofit;
    private static Retrofit mInstance;

    public Api getApi(){
        return retrofit.create(Api.class);
    }

    public static synchronized Retrofit getInstance(){
        if (mInstance == null){
            mInstance = new Retrofit();
        }
        return mInstance;
    }
}
