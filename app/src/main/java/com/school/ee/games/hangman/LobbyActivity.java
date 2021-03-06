package com.school.ee.games.hangman;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.View.OnTouchListener;


public class LobbyActivity extends AppCompatActivity {

    public Button bFindGame;
    public Button bInvite;
    public ListView lvCurrentGames;
    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Launch a new game with a random opponent (create Intent)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Create all of the Listeners here
        OnClickListener bListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                int thisButtonId = v.getId();
                switch (thisButtonId){
                    case R.id.bFindGame:
                        launchGameRoom();
                        break;

                    case R.id.bInvite:
                        launchFriendList();
                        break;

                    default:
                        makeToast("Games coming soon!");
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
        bFindGame   = (Button)findViewById(R.id.bFindGame);
        bInvite     = (Button)findViewById(R.id.bInvite);
        lvCurrentGames = (ListView)findViewById(R.id.lvCurrentGames);

        // TESTING SECTION
        Button g1 = (Button)findViewById(R.id.bCurrGame1);
        Button g2 = (Button)findViewById(R.id.bCurrGame2);
        Button g3 = (Button)findViewById(R.id.bCurrGame3);

        g1.setOnTouchListener(touchListener);
        g2.setOnTouchListener(touchListener);
        g3.setOnTouchListener(touchListener);
        bFindGame.setOnTouchListener(touchListener);
        bInvite.setOnTouchListener(touchListener);

        buttons = new Button[5];
        buttons[0] = g1;
        buttons[1] = g2;
        buttons[2] = g3;
        buttons[3] = bFindGame;
        buttons[4] = bInvite;

        // Attach Listeners to the Views
        for(int i=0;i<buttons.length;i++){
            buttons[i].setOnClickListener(bListener);
            buttons[i].setOnTouchListener(touchListener);

        }


        // Do stuff here
    }



    public void launchGameRoom(){
        Intent intent = new Intent(getBaseContext(),GameRoomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        //overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }

    public void launchFriendList(){
        Intent intent = new Intent(getBaseContext(),FriendListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        //overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }

    public void makeToast(String msg) {
        Toast.makeText(LobbyActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}

