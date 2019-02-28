package com.deliveryapp.login.services;

import com.deliveryapp.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseService {
    private static final DatabaseService ourInstance = new DatabaseService();

    public static DatabaseService getInstance() {
        return ourInstance;
    }

    DatabaseReference databaseReference;
    DatabaseReference databaseUser;


    private DatabaseService() { databaseReference = FirebaseDatabase.getInstance().getReference();}
    private void addUser(User user){
        databaseUser = databaseReference.child("users");
        String id = databaseUser.push().getKey();
        databaseUser.child(id).setValue(user);
    }
    private void getUser(){
        //TODO:
    }
}
