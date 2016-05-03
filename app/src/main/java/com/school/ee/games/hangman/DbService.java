package com.school.ee.games.hangman;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DbService extends Service {
    public DbService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
