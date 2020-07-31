
package com.bridgelabz.addressbook.model;

public class PersonDetails {
    private final String firstName;
    private final String lastName;
    private String address;

    private String city;
    private String state;
    private int zip;
    private String phone;

    public PersonDetails(String firstName, String lastName, String address, String city, String state, int zip, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    //Getter and Setter methods.
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String toString() {
        return "NAME: " + firstName + " " + lastName + "  " + "ADDRESS: " +
                address + "  " + "CITY: " + city + "  " + "STATE: " + state +
                "  " + "ZIPCODE: " + zip + "  " + "PHONE: " + phone;
    }
}