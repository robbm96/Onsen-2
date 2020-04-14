package com.example.onsen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReminderLogic extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

//        Log.e("ERROR", "HEYYYY WE ARE HERE BOIIIII");

        String getstatusString = intent.getExtras().getString("extra");


        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);

        serviceIntent.putExtra("extra", getstatusString);
        context.startService(serviceIntent);

    }


}
