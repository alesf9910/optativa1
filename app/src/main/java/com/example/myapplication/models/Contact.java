package com.example.myapplication.models;

public class Contact {
    private String name;
    private String phone;
    private int id;

    public Contact(int id, String contactName, String contactNumber) {
        this.id = id;
        name = contactName;
        phone = contactNumber;
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }
}

