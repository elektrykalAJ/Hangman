package com.school.ee.games.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public String current_user = null;
    public String current_pass = null;
    public boolean isLoggedIn = false;

    LoginDatabase loginDb;

    SharedPreferences prefs;

    public final static String TAG = "LoginActivity";

    Button button_login;
    Button button_logout;
    Button button_register;
    Button button_camera;
    Button button_pickImage;
    EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        button_login = (Button) findViewById(R.id.button_login);
        button_logout = (Button) findViewById(R.id.button_logout);
        button_register = (Button) findViewById(R.id.button_register);
        button_camera = (Button) findViewById(R.id.button_camera);
        button_pickImage = (Button) findViewById(R.id.button_pickImage);
        loginDb = new LoginDatabase(this);
        addLoginData();
    }

    public void addLoginData() {
        button_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        current_user = etUsername.getText().toString();
                        Log.d(TAG, "USER " + current_user);
                        current_pass = etPassword.getText().toString();
                        Log.d(TAG, "Pass: " + current_pass);
                        isLoggedIn = true;
                        Log.d(TAG, "isLoggedin: " + isLoggedIn);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", current_user);
                        startActivity(intent);


                    }

                }


        );


        button_logout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText etUsername = (EditText) findViewById(R.id.etUsername);
                        EditText etPassword = (EditText) findViewById(R.id.etPassword);

                        etPassword.setText("");
                        etUsername.setText("");

                        if (current_user == null)
                            Toast.makeText(LoginActivity.this, "User not Logged in", Toast.LENGTH_SHORT).show();
                        else {
                            current_user = null;
                            Log.d(TAG, "user logout: " + current_user);
                            current_pass = null;
                            Log.d(TAG, "pass logout: " + current_pass);
                            isLoggedIn = false;
                            Log.d(TAG, "isLoggedin: " + isLoggedIn);
                        }

                    }
                }
        );


        button_register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            EditText etUsername = (EditText) findViewById(R.id.etUsername);
                            EditText etPassword = (EditText) findViewById(R.id.etPassword);



                            Log.d(TAG, "inserted Login info");
                        } catch (Exception e) {
                            //e.printStackTrace();
                            Log.d(TAG, e.getMessage());
                        }

                        String addU = etUsername.getText().toString();
                        String addP = etPassword.getText().toString();


                        //loginDb.addLoginData(addU,addP,imgDecodableString,0);



                    }
                }
        );

        button_camera.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG,"onClick camera");
                        startActivity(new Intent(LoginActivity.this,CameraActivity.class));
                    }
                }

        );

        button_pickImage.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG,"onClick Select image");
                        startActivity(new Intent(LoginActivity.this,PickImageActivity.class));
                    }
                }
        );

    }
}