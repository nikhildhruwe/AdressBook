package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.model.PersonDetails;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PersonComputation implements IAddressBook {

    //******Adding new Person Record to List.*****//
    public List<PersonDetails> addPerson(List<PersonDetails> personList) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nAdd Person Details :");

        System.out.print("\nEnter first name: ");
        String firstName = scan.nextLine();


        System.out.print("Enter last name: ");
        String lastName = scan.nextLine();
        boolean status = personList.stream().anyMatch(personName -> (personName.getFirstName() + " "
                + personName.getLastName()).equalsIgnoreCase(firstName + " " + lastName));
        if (status) {
            System.out.println("Name already exists");
        } else {
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
    public void deletePerson(List<PersonDetails> personList) {
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
    }

    //Editing person record except their name*****//
    public void editPerson(List<PersonDetails> personList) {
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
                boolean editStatus = true;
                while (editStatus) {
                    System.out.println("\nChoose option to edit \n1. Address\n2. City\n3. State\n" +
                            "4. Zip\n5. Phone number\n6. Save and exit\n");
                    int choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Enter new Address: ");
                            String address = scan.next();
                            personDetails.setAddress(address);
                            break;
                        case 2:
                            System.out.print("Enter new City: ");
                            String city = scan.next();
                            personDetails.setCity(city);
                            break;
                        case 3:
                            System.out.print("Enter new State: ");
                            String state = scan.next();
                            personDetails.setState(state);
                            break;
                        case 4:
                            System.out.print("Enter new Zip: ");
                            int zip = scan.nextInt();
                            personDetails.setZip(zip);
                            break;
                        case 5:
                            System.out.print("Enter new Phone Number: ");
                            String phone = scan.next();
                            personDetails.setPhone(phone);
                            break;
                        case 6:
                            System.out.println(" Saved");
                            editStatus = false;
                            break;
                        default:
                            System.out.println("Invalid input.");

                    }
                }
            } else
                System.out.println("Record does not exist");
        } catch (Exception e) {
            System.out.println("Invalid input, please check again");
            editPerson(personList);
        }
    }

    //Sorting list by name
    public void sortByName(List<PersonDetails> personList) {
        personList.sort(Comparator.comparing(PersonDetails::getFirstName));
    }

    //Sorting list by City,Zip Or state.
    public void sortByCityStateZip(List<PersonDetails> personList) {
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
    public void viewPersonCityState(List<PersonDetails> personList) {
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
    public void cityOrState(List<PersonDetails> personList) {
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
    public void print(List<PersonDetails> personList) {
        System.out.println("ADDRESS BOOK DETAILS : ");
        personList.forEach(System.out::println);
    }
}