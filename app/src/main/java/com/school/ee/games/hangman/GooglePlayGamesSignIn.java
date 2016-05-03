package com.school.ee.games.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameUtils;

public class GooglePlayGamesSignIn extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;

    private static int RC_SIGN_IN = 9001;

    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInFlow = true;
    private boolean mSignInClicked = false;



    private Button bGoogleSignIn;
    private TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_play_games_sign_in);

        status("initialize API client");
        // Create the Google Api Client with access to the Play Games services
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                        // add other APIs and scopes here as needed
                .build();
        status("Success");


        OnClickListener signInListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                signInClicked();
            }
        };

        bGoogleSignIn = (Button) findViewById(R.id.button_signInToGooglePlayGames);
        bGoogleSignIn.setOnClickListener(signInListener);

    }
    private void status(String s){
        debug = (TextView) findViewById(R.id.gDebugOut);
        debug.append(s + "\n");
    }
    @Override
    protected void onStart() {
        status("Starting...");
        bGoogleSignIn.setText("Starting...");
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        status("stopped");
        bGoogleSignIn.setText("stopped");
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // The player is signed in. Hide the sign-in button and allow the
        // player to proceed.
        status("connected!");
        bGoogleSignIn.setText("Connected!");
        makeToast("Signed in!");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        status("Failed to connect");
        bGoogleSignIn.setText("Failed to connect");
        if (mResolvingConnectionFailure) {
            // already resolving
            return;
        }

        // if the sign-in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInFlow) {
            mAutoStartSignInFlow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;
            status("launch sign-in flow");
            // Attempt to resolve the connection failure using BaseGameUtils.
            // The R.string.signin_other_error value should reference a generic
            // error string in your strings.xml file, such as "There was
            // an issue with sign-in, please try again later."
            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, connectionResult,
                    RC_SIGN_IN, "Something broke!")) {
                mResolvingConnectionFailure = false;
                status("could not resolve the issue");
            }
        }

        // Put code here to display the sign-in button
    }

    @Override
    public void onConnectionSuspended(int i) {
        status("connection suspended");
        bGoogleSignIn.setText("trying again...");
        // Attempt to reconnect
        mGoogleApiClient.connect();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        status("onActivityResult()");
        if (requestCode == RC_SIGN_IN) {
            mSignInClicked = false;
            mResolvingConnectionFailure = false;
            if (resultCode == RESULT_OK) {
                status("try to connect");
                mGoogleApiClient.connect();
            }
            else {
                // Bring up an error dialog to alert the user that sign-in
                // failed. The R.string.signin_failure should reference an error
                // string in your strings.xml file that tells the user they
                // could not be signed in, such as "Unable to sign in."
                status("got something funky back");
                BaseGameUtils.showActivityResultError(this,
                        requestCode, resultCode, R.string.signin_failure);
            }
        }
    }

    // Call when the sign-in button is clicked
    private void signInClicked() {
        status("SignIn clicked");
        mSignInClicked = true;
        mGoogleApiClient.connect();
    }

    // Call when the sign-out button is clicked
    private void signOutclicked() {
        mSignInClicked = false;
        Games.signOut(mGoogleApiClient);
    }

    public void makeToast(String msg) {
        Toast.makeText(GooglePlayGamesSignIn.this, msg, Toast.LENGTH_SHORT).show();
    }


}