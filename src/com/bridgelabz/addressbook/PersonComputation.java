package com.bridgelabz.addressbook;

import java.time.Period;
import java.util.*;

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
        for ( PersonDetails personDetails : personList)
        {
            if( personDetails.firstName.equals(firstName) )
            {
                if(personDetails.lastName.equals(lastName))
                {
                    System.out.println("Person with this name already exists");
                    return;
                }
            }
        }

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
                    personDetails.setAddress(address);

                    System.out.print("Enter City: ");
                    String city=scan.nextLine();
                    personDetails.setCity(city);

                    System.out.print("Enter State: ");
                    String state=scan.nextLine();
                    personDetails.setState(state);

                    System.out.print("Enter Zip: ");
                    int zip=scan.nextInt();
                    personDetails.setZip(zip);

                    System.out.print("Enter Phone Number: ");
                    String phone=scan.next();
                    personDetails.setPhone(phone);
                    break;
                }
            }
        }

        if(check == true)
        {
            System.out.println("Record does not exist");
        }
    }

    public void sortByName(){
        Collections.sort(personList, new Comparator<PersonDetails>() {
            @Override
            public int compare(PersonDetails o1, PersonDetails o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
    }
   public void sortByCityStateZip(){
        Scanner scan=new Scanner(System.in);


       System.out.println("choose:\n1:City\n2:S tate\n3:Zip");
       int Choice = scan.nextInt();
       switch (Choice)
       {
           case 1: Collections.sort(personList, new Comparator<PersonDetails>() {
               @Override
               public int compare(PersonDetails o1, PersonDetails o2) {
                   return o1.getCity().compareTo(o2.getCity());
               }
           });
           print();
               break;
           case 2: Collections.sort(personList, new Comparator<PersonDetails>() {
               @Override
               public int compare(PersonDetails o1, PersonDetails o2) {
                   return o1.getState().compareTo(o2.getState());
               }
                 });
           print();
               break;
           case 3: Collections.sort(personList, new Comparator<PersonDetails>() {
               @Override
               public int compare(PersonDetails o1 , PersonDetails o2) {
                   return Integer.valueOf(o1.getZip()).compareTo(o2.getZip());
               }
                });
           print();
               break;
           default:
               System.out.println("Ivalid option");

       }
   }
    public void viewPersonCityState(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter State");
        String state=scan.nextLine();
        System.out.println("Enter City");
        String city=scan.nextLine();
        boolean check=true;
        for ( PersonDetails personDetails : personList)
        {
            if( personDetails.state.equals(state) )
            {
                if(personDetails.city.equals(city))
                {
                    check=false;
                    System.out.println("Person: "+personDetails.firstName+" "+personDetails.lastName);
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
            System.out.println(details);
        }

    }
}