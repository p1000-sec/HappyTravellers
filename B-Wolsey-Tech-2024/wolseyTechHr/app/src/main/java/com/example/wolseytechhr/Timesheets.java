package com.example.wolseytechhr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Timesheets extends AppCompatActivity {
    private String userName = "default";
    private String password = "default";
    private String companyName = "default";
    private String auth_code = "default";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesheets);
        userName = getIntent().getStringExtra("userName");
        password = getIntent().getStringExtra("password");
        companyName = getIntent().getStringExtra("companyName");
        auth_code = getIntent().getStringExtra("auth_code");
    }
    /**
     * Opens the profile page
     * @param myView
     */
    public void openProfile(View myView){
        try {
            TextView noInternetDisplay = findViewById(R.id.noInternetDisplay);
            noInternetDisplay.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("userName", userName);
            intent.putExtra("password", password);
            intent.putExtra("companyName", companyName);
            intent.putExtra("auth_code", auth_code);
            startActivity(intent);
            finish();
        }
        catch(Exception e){
            TextView noInternetDisplay = findViewById(R.id.noInternetDisplay);
            noInternetDisplay.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Opens the file center page
     * @param myView
     */
    public void openFileCenter(View myView){
        try {
            TextView noInternetDisplay = findViewById(R.id.noInternetDisplay);
            noInternetDisplay.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, FileCenter.class);
            intent.putExtra("userName", userName);
            intent.putExtra("password", password);
            intent.putExtra("companyName", companyName);
            intent.putExtra("auth_code", auth_code);
            startActivity(intent);
            finish();
        }
        catch (Exception e){
            TextView noInternetDisplay = findViewById(R.id.noInternetDisplay);
            noInternetDisplay.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Logs the user out, takes user back to log in page
     * @param myView
     */
    public void logout(View myView){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}