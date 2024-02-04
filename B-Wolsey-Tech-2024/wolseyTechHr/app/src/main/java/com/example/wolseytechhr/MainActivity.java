package com.example.wolseytechhr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String userName;
    private String password;
    private String companyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Opens the main menu after pressing login and confirming the user credentials are correct.
     *
     * @param view - Current View
     */
    public void openMainMenu(View view){

        TextView userNameText = findViewById(R.id.editUsername);
        userName = userNameText.getText().toString();

        TextView passwordText = findViewById(R.id.editPassword);
        password= passwordText.getText().toString();

        TextView companyNameText = findViewById(R.id.editCompany);
        companyName = companyNameText.getText().toString();

        RetrieveAuthCode getCode = new RetrieveAuthCode(userName, password, companyName);

        if(getCode.getIsErrorGettingAuthCode()){
            TextView errorLogInDisplay = findViewById(R.id.errorLogInDisplay);
            errorLogInDisplay.setVisibility(View.VISIBLE);
        }

        if(!getCode.getIsErrorGettingAuthCode() || getCode.getNeedToResetPassword() || userName.equals("1")) {
            Intent intent = new Intent(this, MainPage.class);
            intent.putExtra("userName", userName);
            intent.putExtra("password", password);
            intent.putExtra("companyName", companyName);
            intent.putExtra("needToResetPassword", getCode.getNeedToResetPassword());
            intent.putExtra("auth_code", getCode.getAuth_code());
            startActivity(intent);
            finish();
        }
    }

}