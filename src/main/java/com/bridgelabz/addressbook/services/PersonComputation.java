package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.model.PersonDetails;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonComputation implements IAddressBook {


    //******Adding new Person Record to List.*****//
    public ArrayList<PersonDetails> addPerson(ArrayList<PersonDetails> personList) {
//            ArrayList<PersonDetails> personList = personData;
            Scanner scan = new Scanner(System.in);
            System.out.println("\nTo add person");

            System.out.print("\nEnter first name: ");
            String firstName = scan.nextLine();

            System.out.print("Enter last name: ");
            String lastName = scan.nextLine();
            boolean status = personList.stream().anyMatch(personName -> (personName.getFirstName() + " "
                    + personName.getLastName()).equalsIgnoreCase((String) firstName + " " + lastName));
            if (status) {
                System.out.println("Name already exists");
            }
            else {
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
            return personList;
    }

    //******Deleting person record*****//
    public ArrayList<PersonDetails> deletePerson(ArrayList<PersonDetails> personList) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();

        PersonDetails personDetails = personList.stream().filter(name -> (name.getFirstName() + " "
                + name.getLastName()).equalsIgnoreCase(firstName
                + " " + lastName)).findAny().orElse(null);
        if (personDetails != null)
            personList.remove(personDetails);
        else
            System.out.println("No Records Found.");
        return personList;
    }

    //Editing person record except their name*****//
    public void editPerson(ArrayList<PersonDetails> personList) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your First name");
            String firstName = scan.next();
            System.out.println("Enter your Last name");
            String lastName = scan.next();

            PersonDetails personDetails = personList.stream().filter(name -> (name.getFirstName() + " "
                    + name.getLastName()).equalsIgnoreCase(firstName
                    + " " + lastName)).findAny().orElse(null);
            if (personDetails != null) {
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
            } else
                System.out.println("Record does not exist");
        }catch (Exception e){
            System.out.println("Invalid input, please check again");
            editPerson(personList);
        }
    }

    //Sorting list by name
    public void sortByName(ArrayList<PersonDetails> personList) {
        personList.sort(Comparator.comparing(PersonDetails::getFirstName));
    }

    //Sorting list by City,Zip Or state.
    public void sortByCityStateZip(ArrayList<PersonDetails> personList) {
        Scanner scan = new Scanner(System.in);
        System.out.println("choose:\n1:City\n2:State\n3:Zip");
        int Choice = scan.nextInt();
        switch (Choice) {
            case 1:
                personList.sort(Comparator.comparing(PersonDetails::getCity));
                print(personList);
                break;
            case 2:
                personList.sort(Comparator.comparing(PersonDetails::getState));
                print(personList);
                break;
            case 3:
                personList.sort(Comparator.comparingInt(PersonDetails::getZip));
                print(personList);
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    //******View person available by giving state and city name.****//
    public void viewPersonCityState(ArrayList<PersonDetails> personList) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter State");
        String state = scan.nextLine();
        System.out.println("Enter City");
        String city = scan.nextLine();
        List<PersonDetails> collect = personList.stream().
                filter(name -> (name.getState().equals(state) && name.getCity().equals(city))).
                collect(Collectors.toList());
        collect.forEach(personDetails -> System.out.println("Person: " + personDetails.getFirstName() + " "
                + personDetails.getLastName()));
        if (collect.isEmpty())
            System.out.println("No Records Found.");
    }

    //******View person in particular city or state.******//
    public void cityOrState(ArrayList<PersonDetails> personList) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter State");
        String state = scan.nextLine();
        System.out.println("Enter City");
        String city = scan.nextLine();

        List<PersonDetails> collectState = personList.stream().filter(name -> (name.getState().equals(state))).
                collect(Collectors.toList());
        collectState.forEach(personState -> System.out.println("Person: " + personState.getFirstName() +
                " " + personState.getLastName() + " State :" + personState.getState()));
        if (collectState.isEmpty())
            System.out.println("Person With State " + state + " Not Found");

        List<PersonDetails> collectCity = personList.stream().filter(name -> (name.getCity().equals(city))).
                collect(Collectors.toList());
        collectCity.forEach(personCity -> System.out.println("Person: " + personCity.getFirstName() +
                " " + personCity.getLastName() + " City :" + personCity.getCity()));
        if (collectCity.isEmpty())
            System.out.println("Person With City " + city + " Not Found");
    }

    //Print the contents of address book
    public void print(ArrayList<PersonDetails> personList) {
        System.out.println("ADDRESS BOOK DETAILS : ");
        personList.forEach(System.out::println);
    }
}