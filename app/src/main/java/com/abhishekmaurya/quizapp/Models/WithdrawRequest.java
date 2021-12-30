package com.abhishekmaurya.quizapp.Models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class WithdrawRequest {

    private String userId;
    private String phoneNumber;
    private String requestedBy;

    @ServerTimestamp
    private Date createAt;

    public WithdrawRequest(Date createAt) {
        this.createAt = createAt;
    }

    public WithdrawRequest(String userId, String phoneNumber, String requestedBy) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.requestedBy = requestedBy;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
