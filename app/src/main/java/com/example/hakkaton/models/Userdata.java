
package com.example.hakkaton.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Userdata {

    private int id, user_id;
    private String first_name, second_name, last_name, snils;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public Userdata(int id, int user_id, String first_name, String second_name, String last_name, String snils) {
        this.id = id;
        this.user_id = user_id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.last_name = last_name;
        this.snils = snils;
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


