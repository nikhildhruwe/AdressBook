package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.PersonDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PersonComputation{
    private ArrayList<PersonDetails> personList = new ArrayList<PersonDetails>();

    //******Adding new Person List.*****//
    public void addPerson() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nTo add person");

        System.out.print("\nEnter first name: ");
        String firstName = scan.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scan.nextLine();
        for (PersonDetails personDetails : personList) {
            if (personDetails.getFirstName().equals(firstName)) {
                if (personDetails.getLastName().equals(lastName)) {
                    System.out.println("Person with this name already exists");
                    return;
                }
            }
        }
        System.out.print("Enter Address: ");
        String address = scan.nextLine();

        System.out.print("Enter City: ");
        String city = scan.nextLine();

        System.out.print("Enter State: ");
        String state = scan.nextLine();

        System.out.print("Enter Zip: ");
        int zip = scan.nextInt();

        System.out.print("Enter Phone Number: ");
        String phone = scan.next();
        PersonDetails personDetails = new PersonDetails(firstName, lastName, address, city, state, zip, phone);
        personList.add(personDetails);
    }

    //******Deleting person record*****//
    public void deletePerson() {
        Scanner scan = new Scanner(System.in);
        boolean check = true;

        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        for (PersonDetails personDetails : personList) {
            if (personDetails.getFirstName().equals(firstName)) {
                if (personDetails.getLastName().equals(lastName)) {
                    check = false;
                    personList.remove(personDetails);
                    break;
                }
            }
        }
        if (check) {
            System.out.println("Record does not exist");
        }
    }

    //Editing person record except their name*****//
    public void editPerson() {
        Scanner scan = new Scanner(System.in);
        boolean check = true;

        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        for (PersonDetails personDetails : personList) {
            if (personDetails.getFirstName().equals(firstName)) {
                if (personDetails.getLastName().equals(lastName)) {
                    check = false;

                    System.out.print("Enter Address: ");
                    String address = scan.nextLine();
                    personDetails.setAddress(address);

                    System.out.print("Enter City: ");
                    String city = scan.nextLine();
                    personDetails.setCity(city);

                    System.out.print("Enter State: ");
                    String state = scan.nextLine();
                    personDetails.setState(state);

                    System.out.print("Enter Zip: ");
                    int zip = scan.nextInt();
                    personDetails.setZip(zip);
                    System.out.print("Enter Phone Number: ");
                    String phone = scan.next();
                    personDetails.setPhone(phone);
                    break;
                }
            }
        }
        if (check) {
            System.out.println("Record does not exist");
        }
    }

    //Sorting list by name
    public void sortByName() {
        Collections.sort(personList, (o1, o2) ->
                o1.getFirstName().compareTo(o2.getFirstName()));
    }

    //Sorting list by City,Zip Or state.
    public void sortByCityStateZip() {
        Scanner scan = new Scanner(System.in);
        System.out.println("choose:\n1:City\n2:State\n3:Zip");
        int Choice = scan.nextInt();
        switch (Choice) {
            case 1:
                Collections.sort(personList, (o1, o2) -> o1.getCity().compareTo(o2.getCity()));
                print();
                break;
            case 2:
                Collections.sort(personList, (o1, o2) -> o1.getState().compareTo(o2.getState()));
                print();
                break;
            case 3:
                Collections.sort(personList, (o1, o2) -> Integer.valueOf(o1.getZip()).compareTo(o2.getZip()));
                print();
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    //******View person available by giving state and city name.****//
    public void viewPersonCityState() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter State");
        String state = scan.nextLine();
        System.out.println("Enter City");
        String city = scan.nextLine();
        boolean check = true;
        for (PersonDetails personDetails : personList) {
            // if (personDetails.state.equals(state)) {
            if (personDetails.getCity().equals(city)) {
                check = false;
                System.out.println("Person: " + personDetails.getFirstName() + " " + personDetails.getLastName());
                break;
            }
        }
        if (check) {
            System.out.println("Record does not exist");
        }
    }

    //******View person in particular city or state.******//
    public void cityOrState() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter State");
        String state = scan.nextLine();
        System.out.println("Enter City");
        String city = scan.nextLine();
        boolean check = true;
        for (PersonDetails personDetails : personList) {
            if (personDetails.getState().equals(state)) {
                check = false;
                System.out.println("Person: " + personDetails.getFirstName() + " " + personDetails.getLastName() +
                        " State :" + personDetails.getState());
            }
            if (personDetails.getCity().equals(city)) {
                check = false;
                System.out.println("Person: " + personDetails.getFirstName() +
                        " " + personDetails.getLastName() + " City :" + personDetails.getCity());
            }
        }
        if (check == true) {
            System.out.println("Record does not exist");
        }
    }

    public void print() {
        System.out.println("ADDRESS BOOK DETAILS : ");
        personList.forEach( details -> System.out.println(details));
        }
}