package com.school.ee.games.hangman;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View.OnTouchListener;

public class GameRoomActivity extends AppCompatActivity {

    private Button[] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);
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
                switch (thisButtonId){
                    default:
                        //makeToast("Invalid View Id found!");
                        break;
                }
            }
        };
        OnTouchListener touchListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    int thisButtonId = v.getId();
                    updateButtonBackground(thisButtonId);
                }
                else{
                    updateButtonBackground(0);
                }
                return false;
            }
        };


        // Create the references to all of the Views


        // TESTING SECTION
        Button g1 = (Button)findViewById(R.id.bRandGame1);
        Button g2 = (Button)findViewById(R.id.bRandGame2);
        Button g3 = (Button)findViewById(R.id.bRandGame3);

        g1.setOnTouchListener(touchListener);
        g2.setOnTouchListener(touchListener);
        g3.setOnTouchListener(touchListener);

        buttons = new Button[3];
        buttons[0] = g1;
        buttons[1] = g2;
        buttons[2] = g3;

        // Do stuff here

    }

    public void updateButtonBackground(int id){

        Button b;
        Drawable bgResource;
        for(int i=0;i<buttons.length;i++){
            b = buttons[i];
            if(b.getId()==id){
                bgResource = getResources().getDrawable(R.drawable.buttons2);
            }
            else{
                bgResource = getResources().getDrawable(R.drawable.buttons);
            }
            b.setBackground(bgResource);
        }
    }

}
