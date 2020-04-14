package com.example.onsen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Beginners extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginners);
    }

    public void mountainClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Mountain.class);
        startActivity(myIntent);
    }

    public void downwardClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Downward.class);
        startActivity(myIntent);
    }

    public void plankClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Plank.class);
        startActivity(myIntent);
    }

    public void triangleClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Triangle.class);
        startActivity(myIntent);
    }

    public void treeClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Tree.class);
        startActivity(myIntent);
    }
}
