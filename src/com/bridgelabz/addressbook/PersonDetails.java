
package com.bridgelabz.addressbook;
import java.util.Scanner;
public class PersonDetails {
//Class Vairables
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    int zip;
    String phone;
    String name;

//Constructor to initialize.
    public PersonDetails( String firstName,String lastName ,String address, String city,String state,int zip,String phone)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zip=zip;
        this.phone=phone;
    }
//Getter and Setter methods.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String compareTo(PersonDetails comparefname) {
        String compareFirstName = ((PersonDetails) comparefname).getFirstName();
        return this.firstName;
    }

    public String toString() {
        return "NAME: "+firstName+" "+lastName+"  "+"ADDRESS: "+address+"  "+"CITY: "+city+"  "+"STATE: "+state+"  "+"ZIPCODE: "+zip+"  "+"PHONE: "+phone;

    }
}