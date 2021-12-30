package com.abhishekmaurya.quizapp.Models;

public class User {


    private String name, email, password, profileImg, refercode;
    private long coins = 05;


    public User(String name, String email, String password, String refercode) {
        this.name = name;
        this.email = email;
        this.password = password;

        this.refercode = refercode;
    }

    public User() {
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefercode() {
        return refercode;
    }

    public void setRefercode(String refercode) {
        this.refercode = refercode;
    }
}
