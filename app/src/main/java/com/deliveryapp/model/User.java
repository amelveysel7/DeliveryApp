package com.deliveryapp.model;

public class User {
    private String name;
    private String lname;
    private String password;
    private String cpassword;
    private String mail;
    private UserType type;

    public User(String name, String lname, String password, String cpassword, String mail, UserType type) {
        this.name = name;
        this.lname = lname;
        this.password = password;
        this.cpassword = cpassword;
        this.mail = mail;
        this.type = type;
    }
}
