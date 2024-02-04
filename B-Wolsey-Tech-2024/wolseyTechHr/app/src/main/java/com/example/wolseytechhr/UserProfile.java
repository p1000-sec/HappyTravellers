package com.example.wolseytechhr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    private String userName = "default";
    private String password = "default";
    private String companyName = "default";
    private String auth_code = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userName = getIntent().getStringExtra("userName");
        password = getIntent().getStringExtra("password");
        companyName = getIntent().getStringExtra("companyName");
        auth_code = getIntent().getStringExtra("auth_code");

        displayEmployeeInfo();
    }
    public void displayEmployeeInfo(){
        // DISPLAY EMPLOYEE INFO IN TEXTVIEWS

        Employee employee = new Employee(userName, password, companyName, auth_code);

        TextView noteTextView = findViewById(R.id.noteDisplay);
        noteTextView.setText(employee.getNote());

        TextView firstNameTextView = findViewById(R.id.firstNameDisplay);
        firstNameTextView.setText(employee.getFirstName());

        TextView middleNameTextView = findViewById(R.id.middleNameDisplay);
        middleNameTextView.setText(employee.getMiddleName());

        TextView lastNameTextView = findViewById(R.id.lastNameDisplay);
        lastNameTextView.setText(employee.getLastName());

        TextView birthDayTextView = findViewById(R.id.birthdateDisplay);
        birthDayTextView.setText(employee.getBirthDay());

        TextView addressTextView = findViewById(R.id.address1Display);
        addressTextView.setText(employee.getAddress1());

        TextView address2TextView = findViewById(R.id.address2Display);
        address2TextView.setText(employee.getAddress2());

        TextView postalCodeView = findViewById(R.id.postalCodeDisplay);
        postalCodeView.setText(employee.getPostalCode());

        TextView cityTextView = findViewById(R.id.cityDisplay);
        cityTextView.setText(employee.getCity());

        TextView provinceTextView = findViewById(R.id.provinceDisplay);
        provinceTextView.setText(employee.getProvince());

        // NEED TO ADD ADDRESS 2

        TextView phoneNumberTextView = findViewById(R.id.phoneDisplay);
        phoneNumberTextView.setText(employee.getPhoneNumber());

        TextView cellNumberTextView = findViewById(R.id.cellDisplay);
        cellNumberTextView.setText(employee.getCellNumber());

        TextView emailAddressTextView = findViewById(R.id.emailDisplay);
        emailAddressTextView.setText(employee.getEmailAddress());

        TextView emergencyContactTextView = findViewById(R.id.emergencyContactDisplay);
        emergencyContactTextView.setText(employee.getEmergencyContact());

        TextView jobTitleTextView = findViewById(R.id.jobTitleDisplay);
        jobTitleTextView.setText(employee.getJobTitle());

        TextView jobLocationTextView = findViewById(R.id.jobLocationDisplay);
        jobLocationTextView.setText(employee.getJobLocation());

        TextView supervisorTextView = findViewById(R.id.supervisorDisplay);
        supervisorTextView.setText(employee.getSupervisor());

        TextView hireDateTextView = findViewById(R.id.hireDateDisplay);
        hireDateTextView.setText(employee.getHireDate());

        TextView previousRehireTextView = findViewById(R.id.previousrehireDatesDisplay);
        previousRehireTextView.setText(employee.getPreviousRehireDates());

        TextView newRehireDateTextView = findViewById(R.id.newHireDisplay);
        newRehireDateTextView.setText(employee.getNewRehireDate());

        TextView safeWorkDateTextView = findViewById(R.id.safeWorkDateDisplay);
        safeWorkDateTextView.setText(employee.getSafeWorkDate());

        TextView companyTextView = findViewById(R.id.companyDisplay);
        companyTextView.setText(employee.getCompany());

        TextView isCommercialDriverTextView = findViewById(R.id.commercialDriverDisplay);
        isCommercialDriverTextView.setText(employee.getIsCommercialDriver());

        TextView hasBenefitsTextView = findViewById(R.id.benefitsDisplay);
        hasBenefitsTextView.setText(employee.getHasBenefits());

        TextView referredByTextView = findViewById(R.id.referredByDisplay);
        referredByTextView.setText(employee.getReferredBy());

        TextView isActiveTextView = findViewById(R.id.activeDisplay);
        isActiveTextView.setText(employee.getIsActive());
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
     * Opens the time sheet page
     * @param myView
     */
    public void openTimeSheet(View myView){
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