package com.example.onsen;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class RingtonePlayingService extends Service {

    MediaPlayer mediaSong;
    Boolean flag;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId ){

//        Log.e("ERROR", "In the servbice java start command!!!!!!!!!!:)");

        String state = intent.getExtras().getString("extra");

        assert state != null;
        if(state.equals("reminder on")){
            flag = true;
        }
        else if(state.equals("reminder off")){
            flag = false;
        }else{
            flag = false;
        }

        if(flag == true) {
            mediaSong = MediaPlayer.create(this, R.raw.remindme);
            mediaSong.start();
        }
        return START_NOT_STICKY;
    }


}
