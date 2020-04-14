package com.example.onsen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Math.round;

public class ReportsActivity extends AppCompatActivity {


    //back4app documentation was used
    String mdata = "";
    String ndata = "";
    Number motion;
    double noise;
    Date date;
    DecimalFormat df = new DecimalFormat("##.##");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        final TextView motionData = findViewById(R.id.motionData);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MotionData");
        query.whereEqualTo("userID", ParseUser.getCurrentUser().getObjectId());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++)
                    {
                        motion = objects.get(i).getNumber("Motions");
                        date = objects.get(i).getCreatedAt();
                        mdata += DateFormat.getDateTimeInstance().format(date) + ": " + motion.toString() + " motions were detected.\n";
                    }
                    motionData.setText(mdata);
                }
                else {}
            }
        });

        final TextView noiseData = findViewById(R.id.noiseData);
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("NoiseData");
        query2.whereEqualTo("userID", ParseUser.getCurrentUser().getObjectId());
        query2.orderByDescending("createdAt");
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++)
                    {
                        noise = objects.get(i).getDouble("loudestNoise");
                        date = objects.get(i).getCreatedAt();
                        ndata += DateFormat.getDateTimeInstance().format(date) + ": " + df.format(noise) + " decibels was the loudest noise detected.\n";
                    }
                    noiseData.setText(ndata);
                }
                else {}
            }
        });



        BottomNavigationView bottomNavBar = findViewById(R.id.navigationBar);
        Menu menu = bottomNavBar.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_reports:
//                        Toast.makeText(ReportsActivity.this, "Reports!!!!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent( ReportsActivity.this, ReportsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_meditate:
//                        Toast.makeText(ReportsActivity.this, "Meditate!!!!", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent( ReportsActivity.this, MeditateActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_monitor:
//                        Toast.makeText(ReportsActivity.this, "Monitor!!!!", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent( ReportsActivity.this, MonitorActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_settings:
//                        Toast.makeText(ReportsActivity.this, "Settings!!!!", Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent( ReportsActivity.this, SettingsActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_reminder:
//                        Toast.makeText(ReportsActivity.this, "Reminder!!!!", Toast.LENGTH_LONG).show();
                        Intent intent4 = new Intent( ReportsActivity.this, ReminderActivity.class);
                        startActivity(intent4);
                        break;
                }

            }
        });

    }


}
