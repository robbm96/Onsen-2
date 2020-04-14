package com.example.onsen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MeditateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate);

        BottomNavigationView bottomNavBar = findViewById(R.id.navigationBar);
        Menu menu = bottomNavBar.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_reports:
//                        Toast.makeText(MeditateActivity.this, "Reports!!!!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MeditateActivity.this, ReportsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_meditate:
//                        Toast.makeText(MeditateActivity.this, "Meditate!!!!", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(MeditateActivity.this, MeditateActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_monitor:
//                        Toast.makeText(MeditateActivity.this, "Monitor!!!!", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(MeditateActivity.this, MonitorActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_settings:
//                        Toast.makeText(MeditateActivity.this, "Settings!!!!", Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent(MeditateActivity.this, SettingsActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_reminder:
//                        Toast.makeText(MeditateActivity.this, "Reminder!!!!", Toast.LENGTH_LONG).show();
                        Intent intent4 = new Intent(MeditateActivity.this, ReminderActivity.class);
                        startActivity(intent4);
                        break;
                }

            }
        });
    }

    public void onButtonClick1(View v){
        Intent myIntent = new Intent(getBaseContext(),   Beginners.class);
        startActivity(myIntent);
    }

    public void onButtonClick2(View v){
        Intent myIntent = new Intent(getBaseContext(),   Intermediate.class);
        startActivity(myIntent);
    }

    public void onButtonClick3(View v){
        Intent myIntent = new Intent(getBaseContext(),   Advanced.class);
        startActivity(myIntent);
    }

}
