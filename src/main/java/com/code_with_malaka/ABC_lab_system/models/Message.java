package com.code_with_malaka.ABC_lab_system.models;

public class Message {
    String contactNumber;
    String text;

    public Message() {

    }

    public Message(String contactNumber, String message) {
        this.contactNumber = contactNumber;
        this.text = message;
    }

    public String getContactNumber() {
      return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
      this.contactNumber = contactNumber;
    }

    public String getMessage() {
      return text;
    }

    public void setMessage(String message) {
      this.text = message;
    }
}
