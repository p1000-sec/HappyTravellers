package com.example.wolseytechhr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainPage extends AppCompatActivity {
    private String userName;
    private String password;
    private String companyName;
    private String auth_code;
    private boolean needToResetPassword = false;
    private boolean isErrorGettingAuthCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        // Getting info from log in
        userName = getIntent().getStringExtra("userName");
        Log.i("asd", userName);
        if(userName.equals("1")){
            String[] testUserCredentials = {"thefoursuits@wolsey-tech.com", "Welcome!", "test"}; // Test for working customer
            // String[] testUserCredentials = {"demo1@wolsey-tech.com", "Welcome!", "test"}; // Test for need to reset password
            //String[] testUserCredentials = {"demo2@wolsey-tech.com", "N0access", "test"}; // Test for no access
            userName = testUserCredentials[0];
            password = testUserCredentials[1];
            companyName = testUserCredentials[2];
            RetrieveAuthCode getCode = new RetrieveAuthCode(userName, password, companyName);
            auth_code = getCode.getAuth_code();
        }
        else {
            password = getIntent().getStringExtra("password");
            companyName = getIntent().getStringExtra("companyName");
            auth_code = getIntent().getStringExtra("auth_code");
            needToResetPassword = getIntent().getBooleanExtra("needToResetPassword", false);
        }
        // If password needs to be reset, show password reset info
        if(needToResetPassword){
            TextView passwordResetTitle = findViewById(R.id.passwordResetTitle);
            passwordResetTitle.setVisibility(View.VISIBLE);

            EditText newPassword = findViewById(R.id.newPasswordText);
            newPassword.setVisibility(View.VISIBLE);

            Button submitButton = findViewById(R.id.submitPasswordButton);
            submitButton.setVisibility(View.VISIBLE);

        }
        // Tell the user if there was an error getting their authorization code. This way
        // They know why we cant get their data
        else if(isErrorGettingAuthCode){
            TextView errorGettingAuthCodeDisplay = findViewById(R.id.errorGettingAuthCodeDisplay);
            errorGettingAuthCodeDisplay.setVisibility(View.VISIBLE);
        }
    }

    /**
     * If the user needs to reset their password the password reset button will be displayed.
     * This method will be called when that button is pressed. This method takes in the password
     * they entered, and sends it to to a ResetPassword class. If the password was valid it will
     * tell the user their password was reset and hide the password reset items, otherwise
     * it will tell the user their password was not reset.
     */
    public void createNewPassword(){
        TextView invalidPassword = findViewById(R.id.invalidPasswordDisplay);
        invalidPassword.setVisibility(View.INVISIBLE);

        // Get entered password from front end
        EditText newPasswordEntered = findViewById(R.id.newPasswordText);
        String password = newPasswordEntered.getText().toString();

        // This will create an object that will try to reset the password to the one entered
        // It will not reset the password if the one entered is invalid
        ResetPassword resetPassword = new ResetPassword(userName, password, companyName);

        // If password was not reset
        if(!resetPassword.getIsValidPassword()) {
            // Tell user the password they entered was not valid
            invalidPassword.setVisibility(View.VISIBLE);
        }
        // if password was reset
        else{
            // Tell user password was reset
            invalidPassword.setVisibility(View.VISIBLE);
            invalidPassword.setText("Password Reset Successfully");

            // hiding the password reset items
            TextView passwordResetTitle = findViewById(R.id.passwordResetTitle);
            passwordResetTitle.setVisibility(View.INVISIBLE);

            EditText newPassword = findViewById(R.id.newPasswordText);
            newPassword.setVisibility(View.INVISIBLE);

            Button submitButton = findViewById(R.id.submitPasswordButton);
            submitButton.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * Opens the file center page. Send the authorization code to the new view.
     *
     * It will not let the new view open if we could not get the correct authorization code.
     *
     * @param myView
     */
    public void openFileCenter(View myView){
        if(!isErrorGettingAuthCode) {
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
            } catch (Exception e) {
                TextView noInternetDisplay = findViewById(R.id.noInternetDisplay);
                noInternetDisplay.setVisibility(View.VISIBLE);
            }
        }
    }
    /**
     * Opens the time sheet page. Send the authorization code to the new view.
     *
     * It will not let the new view open if we could not get the correct authorization code.
     * @param myView
     */
    public void openTimeSheet(View myView){
        if(!isErrorGettingAuthCode) {
            try {
                TextView noInternetDisplay = findViewById(R.id.noInternetDisplay);
                noInternetDisplay.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(this, Timesheets.class);
                intent.putExtra("userName", userName);
                intent.putExtra("password", password);
                intent.putExtra("companyName", companyName);
                intent.putExtra("auth_code", auth_code);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                TextView noInternetDisplay = findViewById(R.id.noInternetDisplay);
                noInternetDisplay.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Opens the profile page. Send the authorization code to the new view.
     *
     * it will not let the new view open if we could not get the correct authorization code.
     * @param myView
     */
    public void openProfile(View myView){
        if(!isErrorGettingAuthCode) {
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
            } catch (Exception e) {
                TextView noInternetDisplay = findViewById(R.id.noInternetDisplay);
                noInternetDisplay.setVisibility(View.VISIBLE);
            }
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