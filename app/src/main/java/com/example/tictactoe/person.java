package com.example.tictactoe;

public class person {
    // Variable to store data corresponding
    // to firstname keyword in database
    private String firstname;

    // Variable to store data corresponding
    // to lastname keyword in database

    // Mandatory empty constructor
    // for use of FirebaseUI
    public person() {
    }

    // Getter and setter method
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}
