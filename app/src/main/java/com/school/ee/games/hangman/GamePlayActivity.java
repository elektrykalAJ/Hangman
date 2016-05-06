package com.school.ee.games.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import static com.school.ee.games.hangman.R.drawable.tile;
import static com.school.ee.games.hangman.R.drawable.tile_selected;

public class GamePlayActivity extends AppCompatActivity {

    public Button butA;
    public Button butB;
    public Button butC;
    public Button butD;
    public Button butE;
    public Button butF;
    public Button butG;
    public Button butH;
    public Button butI;
    public Button butJ;
    public Button butK;
    public Button butL;
    public Button butM;
    public Button butN;
    public Button butO;
    public Button butP;
    public Button butQ;
    public Button butR;
    public Button butS;
    public Button butT;
    public Button butU;
    public Button butV;
    public Button butW;
    public Button butX;
    public Button butY;
    public Button butZ;
    public Button[] tiles;  //  Array to hold all of the tiles for mass-access



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    //////////////////////////////////////////////
    // Define the listeners for letter tiles    //
    //////////////////////////////////////////////
        OnClickListener tileClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                String tile = ((Button)v).getText().toString();
                makeToast("Clicked '" + tile + "'");

//                // specific tile operations:
//                int tileId = v.getId();
//                switch(tileId){
//                    case R.id.butA:
//                        break;
//                    ...
//                    case R.id.butZ:
//                        break;
//                }

            }
        };

        OnTouchListener tileTouchListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                TextView tvOutput = (TextView) findViewById(R.id.tvOutput);
                int action = event.getActionMasked();
                String t = ((Button)v).getText().toString();
//                float x = event.getX();
//                tvOutput.setText(String.valueOf(x) + " " + action);

                if(action == MotionEvent.ACTION_DOWN){
                    tvOutput.setText(t + " Selected");
                    v.setBackgroundResource(tile_selected);
                }
                if(action == MotionEvent.ACTION_UP){
                    v.setBackgroundResource(tile);
                }
                if(action == MotionEvent.ACTION_MOVE){
                    v.setBackgroundResource(tile_selected);
                }

                return false;
            }



        };

        butA = (Button) findViewById(R.id.butA);
        butB = (Button) findViewById(R.id.butB);
        butC = (Button) findViewById(R.id.butC);
        butD = (Button) findViewById(R.id.butD);
        butE = (Button) findViewById(R.id.butE);
        butF = (Button) findViewById(R.id.butF);
        butG = (Button) findViewById(R.id.butG);
        butH = (Button) findViewById(R.id.butH);
        butI = (Button) findViewById(R.id.butI);
        butJ = (Button) findViewById(R.id.butJ);
        butK = (Button) findViewById(R.id.butK);
        butL = (Button) findViewById(R.id.butL);
        butM = (Button) findViewById(R.id.butM);
        butN = (Button) findViewById(R.id.butN);
        butO = (Button) findViewById(R.id.butO);
        butP = (Button) findViewById(R.id.butP);
        butQ = (Button) findViewById(R.id.butQ);
        butR = (Button) findViewById(R.id.butR);
        butS = (Button) findViewById(R.id.butS);
        butT = (Button) findViewById(R.id.butT);
        butU = (Button) findViewById(R.id.butU);
        butV = (Button) findViewById(R.id.butV);
        butW = (Button) findViewById(R.id.butW);
        butX = (Button) findViewById(R.id.butX);
        butY = (Button) findViewById(R.id.butY);
        butZ = (Button) findViewById(R.id.butZ);

        tiles = new Button[26];
        tiles[0] = butA;
        tiles[1] = butB;
        tiles[2] = butC;
        tiles[3] = butD;
        tiles[4] = butE;
        tiles[5] = butF;
        tiles[6] = butG;
        tiles[7] = butH;
        tiles[8] = butI;
        tiles[9] = butJ;
        tiles[10] = butK;
        tiles[11] = butL;
        tiles[12] = butM;
        tiles[13] = butN;
        tiles[14] = butO;
        tiles[15] = butP;
        tiles[16] = butQ;
        tiles[17] = butR;
        tiles[18] = butS;
        tiles[19] = butT;
        tiles[20] = butU;
        tiles[21] = butV;
        tiles[22] = butW;
        tiles[23] = butX;
        tiles[24] = butY;
        tiles[25] = butZ;







        for(int i=0;i<tiles.length;i++){
            tiles[i].setOnClickListener(tileClickListener);
            tiles[i].setOnTouchListener(tileTouchListener);
        }



    }

    // Standard functions
    public void makeToast(String msg) {
        Toast.makeText(GamePlayActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
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
        if (id == R.id.activity_pick) {

            Intent intent = new Intent(GamePlayActivity.this,PickImageActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
