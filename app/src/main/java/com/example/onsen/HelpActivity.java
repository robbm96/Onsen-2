package com.example.onsen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void goHome(View view){
        Intent intent = new Intent(HelpActivity.this, ReportsActivity.class);
        startActivity(intent);
    }

    public void questionShow(View view){
//        Toast.makeText(getApplicationContext(), "QUESTIONSSSS", Toast.LENGTH_LONG).show();
        
        switch (view.getId()) {
            case (R.id.editTextQ1):
                    TextView txtView1 = findViewById(R.id.editTextA1);
                    if (txtView1.getVisibility() == View.VISIBLE)
                        txtView1.setVisibility(View.INVISIBLE);
                    else
                        txtView1.setVisibility(View.VISIBLE);
                break;
            case (R.id.editTextQ2):
                    TextView txtView2 = findViewById(R.id.editTextA2);
                    if (txtView2.getVisibility() == View.VISIBLE)
                        txtView2.setVisibility(View.INVISIBLE);
                    else
                        txtView2.setVisibility(View.VISIBLE);
                break;
            case (R.id.editTextQ3):
                    TextView txtView3 = findViewById(R.id.editTextA3);
                    if (txtView3.getVisibility() == View.VISIBLE)
                        txtView3.setVisibility(View.INVISIBLE);
                    else
                        txtView3.setVisibility(View.VISIBLE);
                break;
            case (R.id.editTextQ4):
                    TextView txtView4 = findViewById(R.id.editTextA4);
                    if (txtView4.getVisibility() == View.VISIBLE)
                        txtView4.setVisibility(View.INVISIBLE);
                    else
                        txtView4.setVisibility(View.VISIBLE);
                break;
            case (R.id.editTextQ5):
                    TextView txtView5 = findViewById(R.id.editTextA5);
                    if (txtView5.getVisibility() == View.VISIBLE)
                        txtView5.setVisibility(View.INVISIBLE);
                    else
                        txtView5.setVisibility(View.VISIBLE);
                break;
        }


    }
}