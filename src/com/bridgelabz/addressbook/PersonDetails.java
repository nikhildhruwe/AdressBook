package com.bridgelabz.addressbook;
import java.util.Scanner;
public class PersonDetails {

    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    int zip;
    String phone;
    String name;
    Scanner scan=new Scanner(System.in);

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
    public void addPerson()
    {
        System.out.println("\nTo add person");

        System.out.print("\nEnter first name: ");
        this.firstName=scan.nextLine();

        System.out.print("Enter last name: ");
        this.lastName=scan.nextLine();
        name=firstName.concat(" "+lastName);

        System.out.print("Enter Address: ");
        this.address=scan.nextLine();

        System.out.print("Enter City: ");
        this.city=scan.nextLine();

        System.out.print("Enter State: ");
        this.state=scan.nextLine();

        System.out.print("Enter Zip: ");
        this.zip=scan.nextInt();

        System.out.print("Enter Phone Number: ");
        this.phone=scan.next();
        //list.add(this.addPerson());
    }
    public void print()
    {
        System.out.println("Person Details : \n");
        System.out.println("NAME: "+this.name+"\nADDRESS: "+this.address+"\nCITY: "+this.city+"\nSTATE: "+this.state+"\nZIP-CODE: "+this.zip+"\nPHONE: "+this.phone);

    }
}
