package com.example.lenovo.lab2intento2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.lab2intento2.Database.FormDatabase;
import com.example.lenovo.lab2intento2.Modelos.Answers;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class FormRespuestas extends AppCompatActivity {
    Button enviar;
    private FusedLocationProviderClient mFuseLocationClient;
    private Location latLng;
    private static final String DATABASE_NAME = "forms_db";
    private FormDatabase formDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_respuestas);
        mFuseLocationClient = LocationServices.getFusedLocationProviderClient(this);


        enviar = (Button) findViewById(R.id.aceptar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                final Answers answers = new Answers();
                answers.setDate("20/agosto/1993");
                answers.setQuestionId(1);

                mFuseLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            answers.setLatitude((float) location.getLatitude());
                            answers.setLongitude((float) location.getLongitude());
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    formDatabase.daoAnswers().insertOnlySingleAnswer(answers);
                                }
                            }) .start();
                        }
                    }
                });
                setResult(1, intent);
                finish();
            }
        });
    }


}
