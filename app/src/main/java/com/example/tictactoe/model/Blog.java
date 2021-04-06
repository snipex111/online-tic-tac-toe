package com.example.tictactoe.model;

public class Blog {
    // Variable to store data corresponding
    // to firstname keyword in database
    private String BLOG;

    // Variable to store data corresponding
    // to lastname keyword in database

    // Mandatory empty constructor
    // for use of FirebaseUI
    public Blog() {
    }

    // Getter and setter method
    public String getBLOG() {
        return BLOG;
    }

    public void setBLOG(String BLOG) {
        this.BLOG = BLOG;
    }

}
