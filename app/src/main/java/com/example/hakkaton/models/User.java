package com.example.hakkaton.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    private int id, is_admin;
    private String email, name, email_verified_at;

    public User(int id, String email, String name,String email_verified_at, Integer is_admin) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.email_verified_at = email_verified_at;
        this.is_admin = is_admin;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return email;
    }

    public String getLogin() {
        return name;
    }
}

   /* public class User {
        @SerializedName("id")
        @Expose
        private int user_id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("email")
        @Expose
        private String user_email;

        @SerializedName("password")
        @Expose
        private String user_password;

        @SerializedName("is_admin")
        @Expose
        private Integer is_admin;

        @SerializedName("email_verified_at")
        @Expose
        private String email_verified_at;*/



       /* @SerializedName("error")
        @Expose
        private String error;

        @SerializedName("repass")
        @Expose
        private String user_repass;*/

        /*public User(String name, String user_email, String user_password,String email_verified_at, Integer is_admin) {
            this.name = name;
            this.user_email = user_email;
            this.user_password = user_password;
            this.email_verified_at = email_verified_at;
            this.is_admin = is_admin;
        }

        public User(String user_login, String user_email, String user_password, String user_repass) {
            this.name = name;
            this.user_email = user_email;
            this.user_password = user_password;
           // this.user_repass = user_repass;
        }

        public int getUser_id() {
            return user_id;
        }

       /* public String getUser_login() {
            return name;
        }*/

       /* public String getUser_email() {
            return user_email;
        }

        public String getUser_password() {
            return user_password;
        }

        public String getName() {
            return name;
        }

        public Integer getIs_admin() {
            return is_admin;
        }

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        /*public String getError() {
            return error;
        }
    }*/


