package com.example.guessthecharacterv2;

import java.util.ArrayList;

// This class represents the details of a user,
// including their username, password, and email.
public class UserDetails {

    private String userName;

    private String userPwd;

    private String userEmail;


    ArrayList userDetails = new ArrayList<UserDetails>();

    public ArrayList getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(ArrayList UserDetails) {
        this.userDetails = userDetails;
    }

    public UserDetails(String userName, String userPwd, String userEmail){
        this.userName = userName;
        this.userPwd = userPwd;
        this.userEmail = userEmail;


    }

    public UserDetails(String userEmail, String userPwd){
        this.userPwd = userPwd;
        this.userEmail = userEmail;

    }


    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }




    public String getUserPwd(){
        return userPwd;
    }
    public void setUserPwd(String userPwd){
        this.userPwd = userPwd;
    }




    public String getUserEmail(){
        return userEmail;
    }
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }








}

//
//    public String getUserPhone(){
//        return userPhone;
//    }
//    public void setUserPhone(String userPhone){
//        this.userPhone = userPhone;
//    }