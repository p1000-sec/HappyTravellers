package com.example.wolseytechhr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.*;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class FileCenter extends AppCompatActivity {

    private String userName;
    private String password;
    private Strinsr fdh fhg company;
    private String companyName = "default";
    private String auth_code = "default";
    List<String[]> employeeFileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_center);
        userName = getIntent().getStringExtra("userName");
        password = getIntent().getStringExtra("password");
        companyName = getIntent().getStringExtra("companyName");
        auth_code = getIntent().getStringExtra("auth_code");
        employeeFileInfo = new EmployeeFiles(userName, password, company, auth_code).retrieveInfo(userName, password, company);
        putInfoIntoTable();



/*
        TableLayout dailyTable = findViewById(R.id.dailyTable); // selecting the daily table
        TableLayout weeklyTable = (TableLayout) findViewById(R.id.weeklyTable); // selecting the weekly table
        int numRowsDailyTable = dailyTable.getChildCount();
        int numRowsWeeklyTable = weeklyTable.getChildCount();
        for(int i = 0; i < numRowsDailyTable && i < numRowsWeeklyTable; i++){

            TableRow dailyTableRow = (TableRow) dailyTable.getChildAt(i);
            TableRow weeklyTableRow = (TableRow) weeklyTable.getChildAt(i);
            if(i % 2 == 0){ // coloring each even row of the table white
                if(dailyTableRow != null)
                    dailyTableRow.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.even_row_color, null));
                if(weeklyTableRow != null)
                    weeklyTableRow.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.even_row_color, null));

            }
            else{ // coloring each odd row of the table gray
                if(dailyTableRow != null)
                    dailyTableRow.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.odd_row_color, null));
                if(weeklyTableRow != null)
                    weeklyTableRow.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.odd_row_color, null));

            }
        }
*/
        // Applying click event listeners to the files

    }

    /**
     * This function puts the data given by employeeFile into the file tables in the front
     * end. It does this by creating new rows and adding them to the dailyTable or weekly table
     * as needed so the tables contain the correct info.
     * the file info
     *
     * .addView from https://stackoverflow.com/questions/37697218/how-to-use-addview-in-loop-to-make-a
     * -rows-in-table-layout
     */
    public void putInfoIntoTable(){
        TableLayout dailyTable = findViewById(R.id.dailyTable); // selecting the daily table
        TableLayout weeklyTable = findViewById(R.id.weeklyTable); // selecting the daily table
        for (int i = 0; i < employeeFileInfo.size() ; i++) {
            String[] fileData = employeeFileInfo.get(i);
            // Creating new row
            TableRow row = new TableRow(this);

            // Creating text views that will be added to the row to show info
            TextView fileNameView = new TextView(this);
            fileNameView.setText(fileData[1]);

            TextView fileUploaderView = new TextView(this);
            fileUploaderView.setText(fileData[5] + ", " + fileData[5]);

            TextView dateView = new TextView(this);
            dateView.setText(fileData[2]);

            // Adding the new views to the row
            row.addView(fileNameView);
            row.addView(fileUploaderView);
            row.addView(dateView);

            // Putting the row into the correct table
            if(fileData[6] != null && (fileData[6].equals("Daily]") || fileData[6].equals("Daily"))) {

                dailyTable.addView(row);
            }
            else{
                weeklyTable.addView(row);
            }
        }
    }


    /**
     * Method that opens a PDF link when the corresponding title is clicked within the table
     * @param table
     */
    public void setOnClickListenersToTableRows(TableLayout table){

        int numRowsTable = table.getChildCount();
        for(int i = 0; i < numRowsTable; i++){

        }

    }

    /**
     * Method that rows to the table by reading them from a hashmap where the key is the file ID and the value is an ArrayList of various data
     * @param dailyTable
     * @param weeklyTable
     * @param rows
     */
    public void addRowsToTables(TableLayout dailyTable, TableLayout weeklyTable, Map <Integer, String[]> rows) {

        for (Map.Entry<Integer, String[]> entry : rows.entrySet()) {

            ArrayList listOfInfo = new ArrayList(Arrays.asList(entry.getValue()));

            TableRow row = new TableRow(this);

            // 1. Adding the first textview
            TextView fileTitle = new TextView(this);  // Setting up the title view
            fileTitle.setText((String) listOfInfo.get(0)); // Setting the text of the title
            fileTitle.setWidth(convertDpToPx(this, 140)); // Setting the width of the title
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,  // Set width to MATCH_PARENT
                    LinearLayout.LayoutParams.WRAP_CONTENT    // Wrap content for height
            );
            fileTitle.setLayoutParams(layoutParams);
            int paddingValueInPixels = convertDipToPx(this, 3); // Calculating padding value and applying it
            fileTitle.setPadding(paddingValueInPixels, paddingValueInPixels, paddingValueInPixels, paddingValueInPixels);

            row.addView(fileTitle); // Adding the textview to the row

            // 2. Adding the second textview
            TextView uploader = new TextView(this);
            uploader.setText((String) listOfInfo.get(4) + ", " + (String) listOfInfo.get(3));
            uploader.setPadding(paddingValueInPixels, paddingValueInPixels, paddingValueInPixels, paddingValueInPixels);
            uploader.setGravity(Gravity.CENTER);
            uploader.setLayoutParams(layoutParams);

            row.addView(uploader);

            // 3. Adding the third textview
            TextView date = new TextView(this);
            date.setText((String) listOfInfo.get(1));
            LinearLayout.LayoutParams dateLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            dateLayoutParams.setMarginEnd(convertDpToPx(this, 25));
            date.setLayoutParams(dateLayoutParams);
            date.setGravity(Gravity.RIGHT);

            row.addView(date);

            if (listOfInfo.get(5).equals("Daily")) {
                dailyTable.addView(row);
            } else if (listOfInfo.get(5).equals("Weekly")) {
                weeklyTable.addView(row);
            } else {
                throw new RuntimeException("File timeline not found");
            }
        }
    }

    /**
     * This method converts dp units to pixels
     * @param context
     * @param dp
     * @return
     */
    public static int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    /**
     * This method converts dip to pixels
     * @param context
     * @param dipValue
     * @return
     */
    public static int convertDipToPx(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
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