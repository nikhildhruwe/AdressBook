package com.bridgelabz.addressbook;

import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonComputation {


    public static void addPerson()
    {
        ArrayList<PersonDetails> personDetails=new ArrayList<PersonDetails>();
        Scanner scan=new Scanner(System.in);
        System.out.println("\nTo add person");

        System.out.print("\nEnter first name: ");
        String firstName = scan.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scan.nextLine();

        System.out.print("Enter Address: ");
        String address=scan.nextLine();

        System.out.print("Enter City: ");
        String city=scan.nextLine();

        System.out.print("Enter State: ");
        String state=scan.nextLine();

        System.out.print("Enter Zip: ");
        int zip=scan.nextInt();

        System.out.print("Enter Phone Number: ");
        String phone=scan.next();
        //list.add(this.addPerson());
        personDetails.add(new PersonDetails(firstName,lastName,address,city,state,zip,phone));
    }
    public static void deletePerson()
    {
        ArrayList<PersonDetails> personDetails=new ArrayList<PersonDetails>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Name");
        String name=scan.next();
        personDetails.remove(name);
    }
    public static void print()
    {
        ArrayList<PersonDetails> personDetails=new ArrayList<PersonDetails>();
        System.out.println("Person Details : \n");
        System.out.println(personDetails);

    }
}