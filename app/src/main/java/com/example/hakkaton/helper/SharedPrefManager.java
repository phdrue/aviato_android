package com.example.hakkaton.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.hakkaton.models.Queue;
import com.example.hakkaton.models.Token;
import com.example.hakkaton.models.User;
import com.example.hakkaton.models.Userdata;


public class SharedPrefManager {

    private static final String SHARED_PREF_NAME ="my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if (mInstance == null){
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }
    public static synchronized SharedPrefManager getInstance(){
        if (mInstance == null){
            Log.d("messageshared","instance not initilized");
        }
        return mInstance;
    }
    public void saveUser(User user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("name", user.getLogin());
        editor.putString("email", user.getNickname());
        editor.putString("email_verified_at", user.getEmail_verified_at());
        editor.putInt("is_admin", user.getIs_admin());


        editor.apply();
    }

    public void saveUserdata(Userdata userdata){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("first_name", userdata.getFirst_name());
        editor.putString("second_name", userdata.getSecond_name());
        editor.putString("last_name", userdata.getLast_name());
        editor.putString("snils_name", userdata.getSnils());

        editor.apply();
    }

    public String getLast_name(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("last_name", null);
    }
    public String getFirst_name(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("first_name", null);
    }
    public String getSecond_name(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("second_name", null);
    }
    public String get_snils(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("snils_name", null);
    }


    public String getUser_Email(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("email", null);
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public String getMessage(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("message", null);
    }

    public void saveMessage(String message){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("message", message);

        editor.apply();
    }

    public void saveStatId(Integer statId){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", statId);

        editor.apply();
    }

    public Integer getStatId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", 0);
    }

    public String LoginReq(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return String.valueOf(sharedPreferences.getString("login", null) != null);
    }

    /*public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("login",  null),
                sharedPreferences.getString("email", null)
        );
    }*/

   /* public Quiz getQuiz(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Quiz(
                sharedPreferences.getString("quiz_id", null),
                sharedPreferences.getString("author_id", null),
                sharedPreferences.getString("quizname", null),
                sharedPreferences.getString("author_name", null)
        );
    }*/

    public void saveToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return
                sharedPreferences.getString("token", null);

    }




    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}

