package com.bridgelabz.addressbook;

import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonComputation {

    private ArrayList<PersonDetails> personList=new ArrayList<PersonDetails>();
    public  void addPerson()
    {
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
        PersonDetails personDetails=new PersonDetails(firstName,lastName,address,city,state,zip,phone);
        personList.add(personDetails);
    }
    public  void deletePerson()
    {
        Scanner scan=new Scanner(System.in);
        boolean check=true;

        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        for ( PersonDetails personDetails : personList)
        {
            if( personDetails.firstName.equals(firstName) )
            {
                if(personDetails.lastName.equals(lastName))
                {
                    check=false;
                    personList.remove(personDetails);
                    break;
                    }
                }
            }

        if(check == true)
        {
            System.out.println("Record does not exist");
        }

    }
    public void editPerson(){
        Scanner scan=new Scanner(System.in);
        boolean check=true;

        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        for ( PersonDetails personDetails : personList)
        {
            if( personDetails.firstName.equals(firstName) )
            {
                if(personDetails.lastName.equals(lastName))
                {
                    check=false;

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
                    PersonDetails personDetails1=new PersonDetails(firstName,lastName,address,city,state,zip,phone);
                    personList.add(personDetails1);
                    break;
                }
            }
        }

        if(check == true)
        {
            System.out.println("Record does not exist");
        }
    }
    public  void print()
    {
        System.out.println("ADDRESS BOOK DETAILS : ");
        for ( PersonDetails details : personList)
        {
            System.out.println("NAME: "+details.firstName+" "+details.lastName+"  "+"ADDRESS: "+details.address+"  "+"CITY: "+details.city+"  "+"STATE: "+details.state+"  "+"ZIPCODE: "+details.zip+"  "+"PHONE NO.: "+details.phone);

        }

    }
}