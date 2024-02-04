package com.example.wolseytechhr;

public class ResetPassword {
    private String userName;
    private String password;
    private String companyName;
    private boolean isValidPassword; // Used to indicate successful reset of password

    /**
     * This class is used to reset a password. It checks if the password is valid, if it is the
     * password will be reset.
     * @param userName
     * @param password
     * @param companyName
     */
    public ResetPassword(String userName, String password, String companyName){
        this.userName = userName;
        this.password = password;
        this.companyName = companyName;
        // If it is valid reset their password to the new one
        if(checkIfValidPassword()){
            // Reset the password
            resetPassword();
            isValidPassword = true;
        }
        // otherwise tell user their password is invalid.
        else{
            isValidPassword = false;
        }
    }


    /**
     * This method checks if a password is valid. A password is valid if it is at least 8 characters
     * long and has at least one letter and one number.
     *
     * @return if the password is valid
     */
    private boolean checkIfValidPassword(){
        boolean hasLetter = false;
        boolean hasNumber = false;
        // If the password is big enough
        if(password.length() > 7){
            for(int i = 0; i < password.length(); i++){
                if(Character.isLetter(password.charAt(i))){
                    hasLetter = true;
                }
                if(Character.isDigit(password.charAt(i))){
                    hasNumber = true;
                }
            }
            if(hasNumber && hasLetter){
                return true;
            }
        }

        return false;
    }

    /**
     * This method creates a link to the password reset and runs it so that the password is reset.
     */
    public void resetPassword(){
        String linkToReset = "https://hr-demo.wolsey-tech.com/get_auth_code.asp?user_name=" + userName + "&password=" + password + "&company_name=" + companyName + "&password_reset=n";
        // TODO: need to run the link
    }

    // Used to indicate if the password was reset
    public boolean getIsValidPassword(){
        return isValidPassword;
    }
}
