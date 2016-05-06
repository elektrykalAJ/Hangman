package com.school.ee.games.hangman;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class TitleActivity extends AppCompatActivity {
    private static final String TAG = "TitleActivity";


    // Testing some changes
public Button bGoogle;
    public Button bPlay;
    public Button bLogin;
    public TextView tvWelcome;
    private Button[] buttons;
    public TextView tvCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Create all of the Listeners here
        OnClickListener bListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                int thisButtonId = v.getId();
//                updateButtonBackground(thisButtonId);
                switch (thisButtonId){
                    case R.id.bPlay:
                        launchGameLobby();
                        break;

                    case R.id.bLogin:
                        launchLogin();
                        break;

                    case R.id.bLaunchGoogleActivty:
                        launchGoogleActivity();

                    default:
                        makeToast("Invalid View Id found!");
                        break;
                }
            }
        };

        OnTouchListener touchListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                if (action == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.drawable.buttons_selected);
                }
                if (action == MotionEvent.ACTION_UP) {
                    v.setBackgroundResource(R.drawable.buttons);
                }
                return false;
            }
        };



        // Create the references to all of the Views
        bPlay       = (Button)findViewById(R.id.bPlay);
        bLogin      = (Button)findViewById(R.id.bLogin);
        bGoogle     = (Button)findViewById(R.id.bLaunchGoogleActivty);

        buttons = new Button[2];
        buttons[0] = bPlay;
        buttons[1] = bLogin;

        tvWelcome   = (TextView)findViewById(R.id.tvWelcome);
        tvCurrentUser = (TextView)findViewById(R.id.tvCurrentUser);

        // Attach Listeners to the Views
        bPlay.setOnClickListener(bListener);
        bLogin.setOnClickListener(bListener);
        bGoogle.setOnClickListener(bListener);

        bPlay.setOnTouchListener(touchListener);
        bLogin.setOnTouchListener(touchListener);
        // Do stuff here

        Intent intent = getIntent();
        String currentUser = intent.getStringExtra("username");
        Log.d(TAG,"Current User: " + currentUser);

        tvCurrentUser.setText("Current User: "+currentUser);

    }

    public void updateButtonBackground(int id){

        Button b;
        Drawable bgResource;
        for(int i=0;i<buttons.length;i++){
            b = buttons[i];
            if(b.getId()==id){
                bgResource = getResources().getDrawable(R.drawable.buttons_selected);
            }
            else{
                bgResource = getResources().getDrawable(R.drawable.buttons);
            }
            b.setBackground(bgResource);
        }
    }


    public void launchGameLobby(){
        Intent intent = new Intent(getBaseContext(),LobbyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        //overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }

    public void launchLogin(){
        Intent intent = new Intent(getBaseContext(),LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        //overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
//        Intent intent = new Intent(getBaseContext(),LobbyActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intent);
        //overridePendingTransition(R.transition.fade_in, R.transition.fade_out);

    }
    public void launchGoogleActivity(){
        Intent intent = new Intent(getBaseContext(),GooglePlayGamesSignIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        //overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }
    public void makeToast(String msg) {
        Toast.makeText(TitleActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_title, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.ac) {
          //  return true;
        //}

        return super.onOptionsItemSelected(item);
    }*/



}
