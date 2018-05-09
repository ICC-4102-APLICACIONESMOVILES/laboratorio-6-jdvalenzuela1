package com.example.lenovo.lab2intento2;

import android.app.Fragment;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lenovo.lab2intento2.Database.FormDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Fragment1.OnFragmentInteractionListener,
        Fragment2.OnFragmentInteractionListener, Fragment3.OnFragmentInteractionListener,
        Fragment5.OnFragmentInteractionListener {

    private TextView emailText;
    public static final String MY_PREFS_NAME = "credencialesAcceso";
    private static final String DATABASE_NAME = "forms_db";
    private NetworkManager networkManager;
    public FormDatabase formDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkManager = NetworkManager.getInstance(this);

        emailText= findViewById(R.id.emailMainId);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String tokenString = prefs.getString("Token", null);

        formDatabase = Room.databaseBuilder(getApplicationContext(),
                FormDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();

        if (tokenString != null) {
            tokenString = prefs.getString("Token", "");
          //  emailText.setText(emailString);
        }
        else {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivityForResult(intent, 1);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.Fragment fragment = null;
        Boolean FragmentoSeleccionado = false;

        if (id == R.id.frag1) {
            fragment = new Fragment1();
        } else if (id == R.id.frag2) {
            fragment = new Fragment2();
        } else if (id == R.id.frag3) {
            fragment = new Fragment3();
        } else if (id == R.id.frag5) {
            fragment = new Fragment5();
        } else if (id == R.id.logOut) {
            Logout();
            return true;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Logout(){
      //  emailText.setText("");
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        new Thread(new Runnable() {
            @Override
            public void run() {
                formDatabase.daoForms().deleteAllForms();
            }
        }) .start();

        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int request, int result, Intent data)
    {
        super.onActivityResult(request, result, data);
        if(request==1)
        {
            String emailIntentContent = data.getStringExtra("EmailIntent");
            String passwordIntentContent = data.getStringExtra("PasswordIntent");

            emailIntentContent = "ignacio@magnet.cl";
            passwordIntentContent = "usuarioprueba";

            try {

                networkManager.login(emailIntentContent, passwordIntentContent, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject headers = response.optJSONObject("headers");
                        String token = headers.optString("Authorization", "");

                        Toast.makeText(getApplicationContext(), "Usuario ingresado con exito", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("Token", token);
                        editor.apply();

                        //LoginForms loginForms = new LoginForms(networkManager, formDatabase);
                        //loginForms.getForms();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println(error);
                        Logout();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
