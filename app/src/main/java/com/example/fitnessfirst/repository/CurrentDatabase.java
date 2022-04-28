package com.example.fitnessfirst.repository;


public class CurrentDatabase {

    private static User currentUser;

    public static User getCurrentPublicUser() {
        return currentUser;
    }

    public static void setCurrentPublicUser(User user) {
        CurrentDatabase.currentUser = user;
    }

}