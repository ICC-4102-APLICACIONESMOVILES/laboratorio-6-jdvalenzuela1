package com.example.lenovo.lab2intento2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText mailText;
    EditText passText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mailText = findViewById(R.id.mailId);
        passText = findViewById(R.id.passId);
        loginButton = findViewById(R.id.loginButtonId);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailStringText = mailText.getText().toString();
                String passStringText = passText.getText().toString();

                if (mailStringText.matches("") || passStringText.matches("")) {
                    Toast.makeText(getApplicationContext(), "Falta ingresar Email o Contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("EmailIntent", mailStringText);
                    intent.putExtra("PasswordIntent", passStringText);
                    setResult(1, intent);
                    finish();
                }
            }
        });
    }
}
