package com.example.onsen;

import android.app.Application;
import android.widget.Toast;
//import com.example.onsen.R;
import com.parse.Parse;
import com.parse.ParseInstallation;

public class App extends Application {


    //This piece of code is responsible for setting up the Parse server
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        ParseInstallation.getCurrentInstallation().saveInBackground();
        //testing to make sure the Parse Server has been installed properly
       // Toast.makeText(LoginActivity.this, "Parse worked", Toast.LENGTH_LONG).show();

    }

}
