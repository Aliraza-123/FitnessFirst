package com.example.fitnessfirst.data.remote;


import com.example.fitnessfirst.data.remote.models.User;

public class CurrentDatabase {

    private static User currentUser;

    public static User getCurrentPublicUser() {
        return currentUser;
    }

    public static void setCurrentPublicUser(User user) {
        CurrentDatabase.currentUser = user;
    }

}