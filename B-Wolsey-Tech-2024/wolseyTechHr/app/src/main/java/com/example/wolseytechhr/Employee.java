package com.example.wolseytechhr;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.util.Log;


public class Employee {
    // Variables have been declared in the same order that the developer site's personal information
    // page was ordered
    private final String link = "https://hr-demo.wolsey-tech.com";

    private String linkToGetProfileInfo;
    private String note;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDay;
    private String address1;
    private String address2;
    private String postalCode;
    private String city;
    private String province;
    private String phoneNumber;
    private String cellNumber;
    private String emergencyContact;
    private String emailAddress;
    private String jobTitle;
    private String jobLocation;
    private String supervisor;
    private String hireDate;
    private String newRehireDate;
    private String previousRehireDates;
    private String safeWorkDate;
    private String company;
    private String isCommercialDriver;
    private String hasBenefits;
    private String referredBy;
    private String isActive;
    private String userName;
    private String password;
    private String companyName;
    private String auth_code;
    private String rawProfileInfo;

    /**
     * This class uses threads to retrieve data from the Wolsey tech database. Threads are needed
     * so that one section of the code will have completed before moving onto the next section.
     *
     * @param userName users log in username
     * @param password users log in password
     * @param companyName users company name
     */
    public Employee(String userName, String password, String companyName, String auth_code) {
        this.userName = userName;
        this.password = password;
        this.companyName = companyName;
        this.auth_code = auth_code;



        // Start and complete the thread that will find the raw user profile data
        getUserRawInfoFromServer();

        // Split up the users raw info into the private variables in this class
        convertRawInfo();
    }



    /**
     * This method is used to find the raw profile data of the user. It works by creating a thread,
     * this is important so that we wait and ensure this section completes before moving on.
     * It works by first finding the link of the site needed to scrape using
     * findUserProfileDataLink(), it then scrapes the body of this site and chops off the
     * success message.
     */
    private void getUserRawInfoFromServer() {
        Thread getUserRawInfoFromServerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Make the link to the site that will contain the users profile data
                    linkToGetProfileInfo = findUserProfileDataLink();
                    // Scraping the site to get the body
                    Document doc = Jsoup.connect(linkToGetProfileInfo).get();
                    Elements body = doc.select("body");
                    // Turning the body into a string
                    rawProfileInfo = body.text();
                    // Chopping off the success message
                    rawProfileInfo = rawProfileInfo.substring(35, rawProfileInfo.length());

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
    private String findUserProfileDataLink(){
        String link = this.link;
        link = link + "/get_data.asp?auth_code=" + getAuth_code() + "&query_type=personal_info";
        // Log.e("bad", link);
        return link;
    }


    /**
     * Converts the users raw profile info that is in a string into the correct variables.
     */
    private void convertRawInfo(){
        // Splitting the raw input into an array where each part of the array contains one
        // part of the data
        String[] profileInfoArray = rawProfileInfo.split("\\],\\[");


        // Iterating through the profileInfoArray, matching each bit of data to the correct
        // variable. Each part of the array will be split into 2 parts where the left is the
        // type of data and the right is the data. If there is only one part that means
        // this data field was not filled in
        for (int i = 0; i < profileInfoArray.length; i++) {
            if (profileInfoArray[i].contains("=")) {
                String[] infoArray = profileInfoArray[i].split("=");

                if (infoArray.length == 2) {
                    switch (infoArray[0]) {
                        case "[first_name":
                            firstName = infoArray[1];
                            break;
                        case "middle_name":
                            middleName = infoArray[1];
                            break;
                        case "last_name":
                            lastName = infoArray[1];
                            break;
                        case "address_1":
                            address1 = infoArray[1];
                            break;
                        case "address_2":
                            address2 = infoArray[1];
                            break;
                        case "city":
                            city = infoArray[1];
                            break;
                        case "province_name":
                            province = infoArray[1];
                            break;
                        case "postal_code":
                            postalCode = infoArray[1];
                            break;
                        case "home_phone":
                            phoneNumber = infoArray[1];
                            break;
                        case "cell_phone":
                            cellNumber = infoArray[1];
                            break;
                        case "email":
                            emailAddress = infoArray[1].substring(0,infoArray[1].length()-1);
                            break;
                    }
                }

            }
        }


    }
    // ---------------------------------------- Getters ----------------------------------------- //
    public String getRawProfileInfo(){
        return rawProfileInfo;
    }
    public String getPostalCode(){
        return postalCode;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getPreviousRehireDates() {
        return previousRehireDates;
    }

    public String getLinkToGetProfileInfo(){return linkToGetProfileInfo;}
    public String getAuth_code(){return auth_code; }
    public String getNote() {
        return note;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getAddress1() {
        return address1;
    }
    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getNewRehireDate() {
        return newRehireDate;
    }

    public String getSafeWorkDate() {
        return safeWorkDate;
    }

    public String getCompany() {
        return company;
    }

    public String getIsCommercialDriver() {
        return isCommercialDriver;
    }

    public String getHasBenefits() {
        return hasBenefits;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public String getIsActive() {
        return isActive;
    }

    // ---------------------------------------- Setters ----------------------------------------- //


    public void setNote(String note) {
        this.note = note;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setAddress1(String address) {
        this.address1 = address;
    }
    public void setAddress2(String address) {
        this.address1 = address;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setNewRehireDate(String newRehireDate) {
        this.newRehireDate = newRehireDate;
    }

    public void setSafeWorkDate(String safeWorkDate) {
        this.safeWorkDate = safeWorkDate;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setIsCommercialDriver(String isCommercialDriver) {
        this.isCommercialDriver = isCommercialDriver;
    }

    public void setHasBenefits(String hasBenefits) {
        this.hasBenefits = hasBenefits;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
