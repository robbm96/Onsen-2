package com.example.onsen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Advanced extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
    }

    public void lotusClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Lotus.class);
        startActivity(myIntent);
    }

    public void peacockClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Peacock.class);
        startActivity(myIntent);
    }

    public void lordClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Lord.class);
        startActivity(myIntent);
    }

    public void shoulderClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Shoulder.class);
        startActivity(myIntent);
    }

    public void sideClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Side.class);
        startActivity(myIntent);
    }
}
