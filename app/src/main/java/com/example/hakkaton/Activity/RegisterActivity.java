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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText Login;
    private EditText Email;
    private EditText Snils;
    private EditText Password;
    private Button SignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        SignUp =  (Button)  findViewById( R.id.btnRegister);
        Login =  (EditText)  findViewById( R.id.reg_fullname);
        Password =  (EditText)  findViewById( R.id.reg_password);
        Email =  (EditText)  findViewById( R.id.reg_email);
        Snils = (EditText)  findViewById( R.id.reg_snils);
        SignUp.setOnClickListener(this);
        // Listening to Login Screen link
        loginScreen.setOnClickListener((v) -> {Swap();});

        };
    private void Swap() {
            // Closing registration screen
            // Switching to Login Screen/closing register screen
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
            finish();
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

    @Override
    public void onClick(View view) {
        if ( view ==  SignUp) {
            UserSignUp();
        }
    }



    private void UserSignUp(){
        String email = Email.getText().toString().trim(); //email
        String password = Password.getText().toString().trim(); //password
        String login = Login.getText().toString().trim(); //логин
        String snils = Snils.getText().toString().trim(); //снилс



        if (login.isEmpty()){
            Login.setError("Не правильно введено имя");
            Login.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Напишите майл правильно");
            Email.requestFocus();
            return;
        }

        if(password.length() <8 ){
            Password.setError("Пароль короткий");
            Password.requestFocus();
            return;
        }
        if (login.isEmpty()){
            Login.setError("Не правильно введен логин");
            Login.requestFocus();
            return;
        }


        if(password.isEmpty()){
            Password.setError("Пароль не верный");
            Password.requestFocus();
            return;
        }
        if(snils.isEmpty()){
            Snils.setError("Введите СНИЛС");
            Snils.requestFocus();
            return;
        }
        if(snils.length() <12 ){
            Snils.setError("СНИЛС неверен");
            Snils.requestFocus();
            return;
        }




        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email, login, snils , password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                    if(!response.isSuccessful()){
                        Toast.makeText(  RegisterActivity.this, "Регистрация  не произошла!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(  RegisterActivity.this, "Вы успешно зарегистрированы!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(  RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }



}

