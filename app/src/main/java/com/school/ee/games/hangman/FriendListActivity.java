package com.school.ee.games.hangman;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class FriendListActivity extends AppCompatActivity {

    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
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
        OnClickListener bClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisButtonId = v.getId();
                String friendId = ((Button) v).getText().toString();
                makeToast("Coming soon: '" + friendId + "'");


                switch (thisButtonId){
                    default:
                        //makeToast("Invalid View Id found!");
                        break;
                }
            }
        };
        OnTouchListener bTouchListener = new OnTouchListener() {
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


        // TESTING SECTION
        Button g1 = (Button)findViewById(R.id.bFriend1);
        Button g2 = (Button)findViewById(R.id.bFriend2);
        Button g3 = (Button)findViewById(R.id.bFriend3);

        g1.setOnTouchListener(bTouchListener);
        g2.setOnTouchListener(bTouchListener);
        g3.setOnTouchListener(bTouchListener);

        g1.setOnClickListener(bClickListener);
        g2.setOnClickListener(bClickListener);
        g3.setOnClickListener(bClickListener);


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
//                bgResource = getResources().getDrawable(R.drawable.buttons2);
                b.setBackgroundResource(R.drawable.buttons_selected);
            }
            else{
//                bgResource = getResources().getDrawable(R.drawable.buttons);
                b.setBackgroundResource(R.drawable.buttons);
            }
//            b.setBackground(bgResource);
        }
    }



    // Standard functions
    public void makeToast(String msg) {
        Toast.makeText(FriendListActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
