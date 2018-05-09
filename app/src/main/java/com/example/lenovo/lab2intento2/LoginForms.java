package com.example.lenovo.lab2intento2;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lenovo.lab2intento2.Database.FormDatabase;
import com.example.lenovo.lab2intento2.Modelos.Forms;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 24-04-2018.
 */

public class LoginForms {

    NetworkManager networkManager;
    FormDatabase formDatabase;

    public LoginForms(NetworkManager networkManager, FormDatabase formDatabase) {
        this.formDatabase = formDatabase;
        this.networkManager = networkManager;

    }
    public void getForms(){
        networkManager.getForms(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray formsJson = response.getJSONArray("0");

                    String name;
                    String date;
                    String category;
                    String comment;
                    int questions;

                    category = "Categoria 1";
                    comment = "Comentario";

                    final List<Forms> formsList =  new ArrayList<Forms>();

                    Forms form;

                    for (int i=0; i<formsJson.length(); i++){
                        form = new Forms();
                        System.out.println("----");
                        name = formsJson.getJSONObject(Integer.parseInt(Integer.toString(i))).get("name").toString();
                        date = formsJson.getJSONObject(Integer.parseInt(Integer.toString(i))).get("created_at").toString();
                        questions = formsJson.getJSONObject(Integer.parseInt(Integer.toString(i))).getJSONArray("fieldsets").length();

                        form.setName(name);
                        form.setDate(date);
                        form.setComment(comment);
                        form.setCategory(category);
                        form.setQuestions(questions);

                        formsList.add(form);
                    }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            formDatabase.daoForms().insertMultipleForms(formsList);
                        }
                    }) .start();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


}