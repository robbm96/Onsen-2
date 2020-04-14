package com.example.onsen;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.IOException;
import java.sql.Time;

import static java.lang.Math.abs;
import static java.lang.Math.log10;

public class MonitorActivity extends AppCompatActivity {
    //https://www.youtube.com/watch?v=8Veyw4e1MX0
    //https://stackoverflow.com/questions/14181449/android-detect-sound-level

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;
    private MediaRecorder recorder = null;
    float motionsDetected = 0;
    float motionsToParse = 0;
    float noiseLevel = 0;
    double noiseInDecibels = 0;
    double noiseToParse = 0;

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    //From android studio developers guide
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    //audio functions from stackoverflow and developers handbook
    public void startRecording() {
        if (recorder == null){
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.setOutputFile("/dev/null"); //saves to nowhere
            try {
                recorder.prepare();
            } catch (IOException e){
                Toast.makeText(this, "Audio Recording Error", Toast.LENGTH_SHORT);
                finish();
            }
            recorder.start();
        }
    }

    public void stopRecording() {
        if (recorder != null)
        {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        long startTime = System.currentTimeMillis();
        setContentView(R.layout.activity_monitor);
        final TextView textView = findViewById(R.id.textView2);
        final ToggleButton toggleButtonGyro = findViewById(R.id.toggleButton);
        final ToggleButton toggleButtonAudio = findViewById(R.id.toggleButton2);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscopeSensor == null) {
            Toast.makeText(this, "Error: no gyroscope", Toast.LENGTH_SHORT).show();
            finish();
        }

        toggleButtonGyro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    motionsDetected = 0;
                }
                else {
                    ParseObject MotionData = new ParseObject("MotionData");
                    motionsToParse = motionsDetected;
                    MotionData.put("userID", ParseUser.getCurrentUser().getObjectId());
                    MotionData.put("Motions", motionsToParse);
                    MotionData.saveInBackground();
                    Toast.makeText(MonitorActivity.this, "Motion data saved", Toast.LENGTH_SHORT).show();
                    motionsDetected = 0;
                }
            }
        });

        toggleButtonAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (recorder == null) {
                        startRecording();
                        recorder.getMaxAmplitude();
                    }
                }
                else {
                    ParseObject NoiseData = new ParseObject("NoiseData");
                    noiseLevel = recorder.getMaxAmplitude();
                    stopRecording();
                    //decibel conversion formula modified from https://stackoverflow.com/questions/32419602/get-more-range-in-decibel-values-from-androidrecoreder-maxamplitude
                    noiseInDecibels = 20 * log10(noiseLevel/2700);
                    noiseToParse = noiseInDecibels;
                    NoiseData.put("userID", ParseUser.getCurrentUser().getObjectId());
                    NoiseData.put("loudestNoise", noiseToParse);
                    Toast.makeText(MonitorActivity.this, "Noise data saved", Toast.LENGTH_SHORT).show();
                    NoiseData.saveInBackground();

                }
            }
        });


        gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //if the average amount of motion is above a certain threshold it is noted
                if ((abs(event.values[2]) + abs(event.values[1]) + abs(event.values[0]) / 3) > 0.7f) {
                    motionsDetected++;
                }
                //textView.setText(Float.toString(noiseLevel));

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


        BottomNavigationView bottomNavBar = findViewById(R.id.navigationBar);
        Menu menu = bottomNavBar.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_reports:
//                        Toast.makeText(MonitorActivity.this, "Reports!!!!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MonitorActivity.this, ReportsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_meditate:
//                        Toast.makeText(MonitorActivity.this, "Meditate!!!!", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(MonitorActivity.this, MeditateActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_monitor:
//                        Toast.makeText(MonitorActivity.this, "Monitor!!!!", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(MonitorActivity.this, MonitorActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_settings:
//                        Toast.makeText(MonitorActivity.this, "Settings!!!!", Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent(MonitorActivity.this, SettingsActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_reminder:
//                        Toast.makeText(MonitorActivity.this, "Reminder!!!!", Toast.LENGTH_LONG).show();
                        Intent intent4 = new Intent(MonitorActivity.this, ReminderActivity.class);
                        startActivity(intent4);
                        break;
                }

            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_UI);
    }

}
