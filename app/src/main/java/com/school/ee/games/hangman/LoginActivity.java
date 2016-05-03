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

    public static String NEW_USER_ADDED;
    public String current_user = null;
    public String current_pass = null;
    public String get_user;
    public String get_pass;

    public boolean isLoggedIn = false;
    public boolean user_found = false;
    public boolean login_match = false;

    LoginDatabase loginDb;

    Cursor cursor;

    //SharedPreferences prefs;

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

        cursor = loginDb.query();

    }

    public void addLoginData() {
        button_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        while (cursor.moveToNext()){

                            get_user = cursor.getString(cursor.getColumnIndex(LoginDatabase.USER_ID));
                            Log.d(TAG,"user from db: "+ get_user );
                            current_user = etUsername.getText().toString();
                            if(get_user.contentEquals(current_user)){
                                Log.d(TAG, "USER: " + current_user);
                                user_found = true;
                            }

                            get_pass = cursor.getString(cursor.getColumnIndex(LoginDatabase.USER_PASS));
                            Log.d(TAG,"pass from db: "+ get_pass );
                            current_pass = etPassword.getText().toString();
                            if(get_pass.contentEquals(current_pass)) {
                                Log.d(TAG, "Pass: " + current_pass);
                            }

                            if(get_pass.contentEquals(current_pass) && get_user.contentEquals(current_user)) {
                                isLoggedIn = true;
                                Log.d(TAG, "isLoggedin: " + isLoggedIn);
                                Toast.makeText(LoginActivity.this,"Logged in",Toast.LENGTH_SHORT).show();
                                login_match = true;
                            }

                        }
                        if(user_found == false){
                            Toast.makeText(LoginActivity.this,"User not Found, Register?",Toast.LENGTH_SHORT).show();
                        }
                        if(login_match == false){
                            Toast.makeText(LoginActivity.this,"Username and Password do not match",Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(LoginActivity.this, TitleActivity.class);
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
                        Intent intent = new Intent(LoginActivity.this, TitleActivity.class);
                        intent.putExtra("username", current_user);
                        startActivity(intent);

                    }
                }
        );


        button_register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*try {
                            EditText etUsername = (EditText) findViewById(R.id.etUsername);
                            EditText etPassword = (EditText) findViewById(R.id.etPassword);
                            Log.d(TAG, "inserted Login info");
                        } catch (Exception e) {
                            //e.printStackTrace();
                            Log.d(TAG, e.getMessage());
                        }*/

                        String addU = etUsername.getText().toString();
                        String addP = etPassword.getText().toString();
                        Log.d(TAG,"username: "+addU+" pass: "+addP);

                        //Intent intent = getIntent();
                        //String image = intent.getStringExtra(IMAGE_STRING);

                        if(addU.matches("") || addP.matches("")){
                            Toast.makeText(LoginActivity.this,"Username or Password can't be left blank",Toast.LENGTH_SHORT).show();


                        }
                        else {

                            loginDb.addLoginData(addU, addP, null, 0);
                            Toast.makeText(LoginActivity.this,"User registered, go ahead and Login",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, TitleActivity.class);
                            intent.putExtra("username", current_user);
                            startActivity(intent);

                        }

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