package com.moviles1.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class signUp extends AppCompatActivity
{
    EditText etName;
    EditText etEmail2;
    EditText etBirth;
    EditText etPassword2;
    Button btSaveData;
    View tvBackToIn;
    Spinner sList;
    EditText etPais;
    EditText etCiudad;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etName = findViewById(R.id.etName);
        etEmail2 = findViewById(R.id.etEmail2);
        etBirth = findViewById(R.id.etBirth);
        etPassword2 = findViewById(R.id.etPassword2);
        tvBackToIn = findViewById(R.id.tvBackToIn);
        etPais = findViewById(R.id.etPais);
        etCiudad = findViewById(R.id.etCiudad);
        sList = findViewById(R.id.sList);

        String [] opciones = {"User","Shop"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
        sList.setAdapter(adapter);

        String selection = sList.getSelectedItem().toString();
        sList.setPrompt(selection);

    }

    public  void createNewUser(View v)
    {
        String email = etEmail2.getText().toString();
        String password = etPassword2.getText().toString();
        String name = etName.getText().toString();
        String birth = etBirth.getText().toString();

        if(email.isEmpty()||password.isEmpty()||name.isEmpty()||birth.isEmpty())
        {
            Toast.makeText(this, "Ningun campo puede estar vacio", Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<8||password.contains("*")==false)
        {
            Toast.makeText(this, "Tu password debe contener minimo de 8 caracteres y  simbolo =(*) ", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Created Successfully", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getSharedPreferences("uservalues", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("birth", birth);
            editor.putString("password", password);
            editor.commit();
        }


    }
    public void goToSignIn(View v)
    {
        Intent changeScreen = new Intent(signUp.this, MainActivity.class);
        startActivity(changeScreen);

    }







}