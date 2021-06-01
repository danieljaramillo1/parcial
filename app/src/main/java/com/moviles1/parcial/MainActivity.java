package com.moviles1.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{


    EditText etEmail;
    EditText etPassword;
    Button btSigin;
    View tvSignup;
    View tvForgot;
    CheckBox cbSaveSession;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String userState = loadPreferences();
        if(userState.equals("ok"))
        {
            Toast.makeText(this, "Validated", Toast.LENGTH_SHORT).show();
            Intent changeScreen = new Intent(MainActivity.this, home.class);
            startActivity(changeScreen);

        }
        else{
            setContentView(R.layout.activity_main);
        }
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btSigin = findViewById(R.id.btSignin);
        tvSignup = findViewById(R.id.tvSignup);
        tvForgot = findViewById(R.id.tvforgot);



    }



        public void validateUser(View v)
        {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            SharedPreferences preferencias = getSharedPreferences("uservalues",Context.MODE_PRIVATE);
            String userEmail = preferencias.getString("email","error");
            String userPass = preferencias.getString("password","error");

            if(email.isEmpty()||password.isEmpty())
            {
                Toast.makeText(this, "Debes ingresar Email y Password", Toast.LENGTH_SHORT).show();
            }
            else if(password.length()<8||password.contains("*")==false)
            {
                Toast.makeText(this, "Tu password debe contener minimo de 8 caracteres y  simbolo =(*) ", Toast.LENGTH_LONG).show();
            }
            else if(password.equals(userPass) && email.equals(userEmail))
            {

                savePreferences();
                Toast.makeText(this, "Validated", Toast.LENGTH_SHORT).show();
                Intent changeScreen = new Intent(MainActivity.this, home.class);
                startActivity(changeScreen);
                finish();

            }
            else if(userEmail.equals("error")|userPass.equals("error"))

            {
                Toast.makeText(this, "wrong email or pass", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "wrong email or pass", Toast.LENGTH_SHORT).show();
            }



        }

        public void goToSignUp(View v)
        {
            Intent changeScreen = new Intent(MainActivity.this, signUp.class);
            startActivity(changeScreen);

        }
        public void goToReset(View v)
        {
            Intent changeScreen = new Intent(MainActivity.this, reset.class);
            startActivity(changeScreen);

        }
        public void savePreferences()
        {
            SharedPreferences  preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("userState","ok");
            editor.commit();
        }

        public String loadPreferences()
        {
            SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            String UserState = preferences.getString("userState","error");
            return UserState;

        }


    }
