package com.lunlun.fenhow1219;

public class LoginUserList {
    private int position;
    private String userID;
    private String userEmail;
    private String userName;
    private String userPassword;

    public LoginUserList(int position, String userID, String userEmail, String userName, String userPassword) {
        this.position=position;
        this.userID = userID;
        this.userEmail = userEmail;
        this.userName=userName;
        this.userPassword = userPassword;
    }
    public int getPosition() {
        return this.position;
    }

    public void setPosition(int n) {
        this.position=n;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String str) {
        this.userID=userID;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String str) {
        this.userEmail = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String str) {
        this.userPassword = str;
    }


}
