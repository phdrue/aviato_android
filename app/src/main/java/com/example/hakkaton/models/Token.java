package com.example.hakkaton.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

        public class Token {

        private String token;

        public Token(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }


}

/*public class Token{
    @SerializedName("id")
    @Expose
    private int token_id;

    @SerializedName("user_id")
    @Expose
    private int user_id;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("expired_at")
    @Expose
    private long expired_at;

    @SerializedName("createdAt")
    @Expose
    private long createdAt;

//    @SerializedName("status")
//    @Expose
//    private String status;

    public Token(Integer user_id, String token, long expired_at) {
        this.user_id = user_id;
        this.token = token;
        this.expired_at = expired_at;
    }

    public Token(Integer user_id, String token, long expired_at, long createdAt) {
        this.user_id = user_id;
        this.token = token;
        this.expired_at = expired_at;
        this.createdAt = createdAt;
    }

    public Integer getTokenID() {
        return token_id;
    }

    public Integer getUserID() { return user_id; }

    public String getToken() {
        return token;
    }

    public long getExpiredAt() { return expired_at; }

    public long getCreatedAt() { return createdAt; }

}*/
