package com.school.ee.games.hangman;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class UsernameActivity extends AppCompatActivity {
    public static final String TAG = "UsernameActivity";

    SimpleCursorAdapter adapter;
    Cursor cursor;
    ListView list;
    String FROM[] = {LoginDatabase.USER_ID,LoginDatabase.USER_PASS,LoginDatabase.USER_SCORE};
    int TO[] = {R.id.text_username,R.id.text_password,R.id.text_score};
    LoginDatabase loginDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        loginDatabase = new LoginDatabase(this);

        cursor = loginDatabase.query();
        adapter.changeCursor(cursor);

        list = (ListView) findViewById(R.id.list);

        adapter = new SimpleCursorAdapter(this,R.layout.row,cursor,FROM,TO,0);
        adapter.setViewBinder(VIEW_BINDER);
        list.setAdapter(adapter);
    }

    static final SimpleCursorAdapter.ViewBinder VIEW_BINDER = new SimpleCursorAdapter.ViewBinder() {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {

            if (view.getId()!=R.id.text_password)
                return false;

            String password = cursor.getString(cursor.getColumnIndex(LoginDatabase.USER_PASS));

            ((TextView)view).setText(password);
            return true;

        }
    };
}
