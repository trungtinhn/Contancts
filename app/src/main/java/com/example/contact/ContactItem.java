package com.example.contact;

public class ContactItem {
    private String Name;
    private String Number;

    public ContactItem(String name, String phone){
        this.Name = name;
        this.Number = phone;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
