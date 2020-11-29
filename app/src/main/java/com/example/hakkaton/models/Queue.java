package com.example.hakkaton.models;

import java.util.List;

public class Queue {
    private String message;
    private List<Queue> queue;


    public Queue(String message){
        this.message = message;

    }


    public String getMessage() {
        return message;
    }

    public List<Queue> getQueue() {
        return queue;
    }
}
