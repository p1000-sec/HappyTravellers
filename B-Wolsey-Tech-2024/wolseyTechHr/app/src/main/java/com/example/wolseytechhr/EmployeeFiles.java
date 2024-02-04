package com.example.wolseytechhr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.util.Log;
import java.util.*;

public class EmployeeFiles {
    private final String link = "https://hr-demo.wolsey-tech.com";
    private String linkToGetAuthCode;
    private String linkToGetProfileInfo;
    private String userName;
    private String password;
    private String companyName;
    private String auth_code;
    private String rawFileInfo;
    public String getAuth_code(){return auth_code; }
    public String getRawProfileInfo(){
        return rawFileInfo;
    }

    public  EmployeeFiles(String userName, String password, String companyName, String auth_code) {
        this.userName = userName;
        this.password = password;
        this.companyName = companyName;
        this.auth_code = auth_code;

    }
    public List<String[]>retrieveInfo(String userName, String password, String companyName) {

        // Start and complete the thread that will find the raw user profile data
        getUserRawInfoFromServer();

        List<String[]> convertedInfo = convertRawInfo();



        return convertedInfo;
    }

    /**
     * This method is used to find the raw file data of the user. It works by creating a thread,
     * this is important so that we wait and ensure this section completes before moving on.
     * It works by first finding the link of the site needed to scrape using
     * findUserFileDataLink(), it then scrapes the body of this site and chops off the
     * success message.
     */
    public void getUserRawInfoFromServer() {
        Thread getUserRawInfoFromServerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Make the link to the site that will contain the users profile data
                    linkToGetProfileInfo = findUserFileDataLink();
                    // Scraping the site to get the body
                    Document doc = Jsoup.connect(linkToGetProfileInfo).get();
                    Elements body = doc.select("body");
                    // Turning the body into a string
                    rawFileInfo = body.text();
                    // Chopping off the success message
                    rawFileInfo = rawFileInfo.substring(35, rawFileInfo.length());

                }
                catch (Exception e) {
                    Log.i("bad", "getUserRawInfoFromServer");
                }
            }
        });
        getUserRawInfoFromServerThread.start();

        // https://ducmanhphan.github.io/2020-03-20-Waiting-threads-to-finish-completely-in-
        // Java/#using-join()-method-of-Thread-class
        // .join makes it so this thread completes before the other code continues

        try {
            getUserRawInfoFromServerThread.join();
        }
        catch (InterruptedException e) {

        }
    }

    /**
     * This method creates a link that will lead to a page containing the users profile information
     *
     * @return the link to page containing the profile
     */
    private String findUserFileDataLink(){
        String link = this.link;
        link = link + "/get_data.asp?auth_code=" + getAuth_code() + "&query_type=file_center";

        return link;
    }

    /**
     * This method converts the raw input data into a list containing string arrays where each
     * array in the list has the info for one file.
     * @return
     */
    private List<String[]> convertRawInfo() {
        // Splitting the raw input into the seperate files
        String[] filesInfoArray = rawFileInfo.split("\\] \\[");

        // Initializing a list to store fileInfo arrays
        List<String[]> fileInfoList = new ArrayList<>();

        // Initializing the fileInfo array
        String[] fileInfo = new String[7];

        // Iterating through the profileInfoArray going file by file
        for (String file : filesInfoArray) {
            String[] fileInfoArray = file.split("\\],\\[");
            // looping through the data of the current file
            for(int i = 0; i < fileInfoArray.length; i++) {
              if (fileInfoArray[i].contains("=")) {
                String[] infoArray = fileInfoArray[i].split("=");

                // Matching the data to the correct part in the array of file data
                if (infoArray.length == 2) {
                    switch (infoArray[0]) {
                        case "file_center_id":

                                // putting the array containing file info into the list
                                fileInfoList.add(fileInfo);
                                fileInfo = new String[7];

                            fileInfo[0] = infoArray[1];
                            break;
                        case "file_name":
                            fileInfo[1] = infoArray[1];
                            break;
                        case "date_uploaded":
                            fileInfo[2] = infoArray[1];
                            break;
                        case "file_path":
                            fileInfo[3] = infoArray[1];
                            break;
                        case "first_name":
                            fileInfo[4] = infoArray[1];
                            break;
                        case "last_name":
                            fileInfo[5] = infoArray[1];
                            break;
                        case "file_center_type":
                            Log.i("wad", infoArray[1]);
                            fileInfo[6] = infoArray[1];
                            break;
                    }
                }
            }
            }
        }

        // Adding the last fileInfo to the list

        fileInfoList.add(fileInfo);
        Log.i("filetype",rawFileInfo);
       Log.i("filetype",fileInfoList.get(1)[6]);
        return fileInfoList;
    }



}
