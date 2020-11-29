package com.example.hakkaton.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakkaton.MainActivity;
import com.example.hakkaton.R;
import com.example.hakkaton.api.RetrofitClient;
import com.example.hakkaton.helper.SharedPrefManager;
import com.example.hakkaton.models.Data;
import com.example.hakkaton.models.Userdata;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginActivity extends Activity implements View.OnClickListener {

    private Button SignIn, stat;
    private EditText emailPole;
    private EditText pass;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        // setting default screen to login.xml
//        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        SignIn = findViewById(R.id.b_autoriz);
        emailPole = findViewById(R.id.et_lodin);
        pass = findViewById(R.id.et_password);


        /*stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, StatActivity.class);
                    startActivity(intent);
            }
        });*/

        SignIn.setOnClickListener(this);
        // Listening to register new account link
            //registerScreen.setOnClickListener((v) -> {Swapp();});


        };

        private void Swapp() {
            // Switching to Login Screen/closing register screen
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
            finish();
        }


    @Override
    public void onClick(View view) {
        if ( view ==  SignIn) {
            userLogin();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private void userLogin(){

        String email = emailPole.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (email.isEmpty()){
                emailPole.setError("Не правильно введен email");
                emailPole.requestFocus();
                return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailPole.setError("Напишите email правильно");
            emailPole.requestFocus();
            return;
        }
        if(password.isEmpty()){
            pass.setError("Пароль не верный");
            pass.requestFocus();
            return;
        }

        if(password.length() <8 ) {
            pass.setError("Пароль короткий");
            pass.requestFocus();
            return;
        }

        SharedPrefManager s = SharedPrefManager.getInstance(this);

        Call<Data> call = RetrofitClient
                .getInstance().getApi().getUserByInput(email,password,s.getToken());

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Не верный логин или пароль!", Toast.LENGTH_SHORT);
                    toast.show();}
                else {

                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(data.getResponse());
                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUserdata(data.getUserdata());

                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveToken(data.getToken());
                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveToken(data.getToken());
                    Toast.makeText(getApplicationContext(),"Здравствуй,  " + s.getFirst_name() + " " + s.getSecond_name() + " " + s.getLast_name(),Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }

        });
    }


    }




