package com.example.onsen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Intermediate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
    }

    public void boatClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Boat.class);
        startActivity(myIntent);
    }

    public void bowClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Bow.class);
        startActivity(myIntent);
    }

    public void dolphinClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Dolphin.class);
        startActivity(myIntent);
    }

    public void craneClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Crane.class);
        startActivity(myIntent);
    }

    public void eagleClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Eagle.class);
        startActivity(myIntent);
    }
}
