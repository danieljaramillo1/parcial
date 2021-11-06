package com.moviles1.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.moviles1.parcial.databinding.ActivityHomeBinding;

public class home extends AppCompatActivity {
    Button boton;
    Button btlogOut;
    TextView tvName;
    TextView tvEmail;
    TextView tvBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        btlogOut = findViewById(R.id.btlogOut);
        tvName =findViewById(R.id.tvName);
        tvEmail =findViewById(R.id.tvEmail);
        tvBirth =findViewById(R.id.tvBirth);
        boton = findViewById(R.id.button);

        SharedPreferences preferencias = getSharedPreferences("uservalues", Context.MODE_PRIVATE);
        String userEmail = preferencias.getString("email","error");
        String userBirth = preferencias.getString("birth","error");
        String userName = preferencias.getString("name","error");

        tvName.setText(userName);
        tvBirth.setText(userBirth);
        tvEmail.setText(userEmail);
    }


   public void logOut(View v)
   {


       SharedPreferences  preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = preferences.edit();
       editor.putString("userState","error");
       editor.commit();

       SharedPreferences pref = getSharedPreferences("", Context.MODE_PRIVATE);
       Intent changeScreen = new Intent(home.this, MainActivity.class);
       startActivity(changeScreen);
   }

   public void tolist(View v)
   {
       Intent changeScreen = new Intent(home.this, showProduct.class);
       startActivity(changeScreen);
   }

}