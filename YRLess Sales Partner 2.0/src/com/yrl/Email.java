package com.yrl;

public class Email {
    private int emailId;
    private String email;

    public Email(int emailId, String email) {
        this.emailId = emailId;
        this.email = email;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailId=" + emailId +
                ", email='" + email + '\'' +
                '}';
    }
}