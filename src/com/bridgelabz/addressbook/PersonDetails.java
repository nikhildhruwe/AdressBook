package com.bridgelabz.addressbook;

public class PersonDetails {

    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    int zip;
    String phone;

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

    public void print()
    {
        System.out.println("Person Details : \n");
        System.out.println("NAME: "+this.firstName+" "+this.lastName+"\nADDRESS: "+this.address+"\nCITY: "+this.city+"\nSTATE: "+this.state+"\nZIP-CODE: "+this.zip+"\nPHONE: "+this.phone);

    }
}
