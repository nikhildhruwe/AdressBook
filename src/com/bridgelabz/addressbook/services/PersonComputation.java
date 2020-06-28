package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.model.PersonDetails;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Stream;

public class PersonComputation implements IAddressBook {
    private final ArrayList<PersonDetails> personList = new ArrayList<>();

    //******Adding new Person Record to List.*****//
    public void addPerson() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nTo add person");

        System.out.print("\nEnter first name: ");
        String firstName = scan.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scan.nextLine();
        if (equals(firstName + " " + lastName))
            System.out.println("Name already exists");
        else{
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
    }

    //******Deleting person record*****//
    public void deletePerson() {
        Scanner scan = new Scanner(System.in);
        boolean check = true;

        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        PersonDetails personDetails = personList.stream().filter(name->(name.getFirstName() + " "
                + name.getLastName()).equalsIgnoreCase(firstName
                + " " + lastName)).findAny().orElse(null);
        if (personDetails!=null)
            personList.remove(personDetails);
        else
            System.out.println("No Records Found.");
    }

    //Editing person record except their name*****//
    public void editPerson() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        PersonDetails personDetails = personList.stream().filter(name->(name.getFirstName() + " "
                + name.getLastName()).equalsIgnoreCase(firstName
                + " " + lastName)).findAny().orElse(null);
                if ( personDetails!=null){
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
                }
                else
                System.out.println("Record does not exist");
        }

    //Sorting list by name
    public void sortByName() {
        personList.sort(Comparator.comparing(PersonDetails::getFirstName));
    }

    //Sorting list by City,Zip Or state.
    public void sortByCityStateZip() {
        Scanner scan = new Scanner(System.in);
        System.out.println("choose:\n1:City\n2:State\n3:Zip");
        int Choice = scan.nextInt();
        switch (Choice) {
            case 1:
                personList.sort(Comparator.comparing(PersonDetails::getCity));
                print();
                break;
            case 2:
                personList.sort(Comparator.comparing(PersonDetails::getState));
                print();
                break;
            case 3:
                personList.sort(Comparator.comparingInt(PersonDetails::getZip));
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
       personList.stream().
                filter(name->(name.getState().equals(state) && name.getCity().equals(city))).
                findAny().ifPresentOrElse
                (personDetails ->System.out.println("Person: " + personDetails.getFirstName() + " "
                        + personDetails.getLastName()),
                        ()->System.out.println("No Records Found"));;
    }

    //******View person in particular city or state.******//
    public void cityOrState() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter State");
        String state = scan.nextLine();
        System.out.println("Enter City");
        String city = scan.nextLine();
        personList.stream().
                filter(name -> (name.getState().equals(state))).findAny().
                ifPresentOrElse(personState -> System.out.println("Person: " + personState.getFirstName() +
                        " " + personState.getLastName() + " State :" + personState.getState())
                , ()-> System.out.println("State " + state +" Not Found"));

        personList.stream().
                filter(name -> (name.getCity().equals(city))).findAny().
                ifPresentOrElse
                        (personCity -> System.out.println("Person: " + personCity.getFirstName() +
                " " + personCity.getLastName() + " City :" + personCity.getCity()),
                                ()-> System.out.println("City " + city + " not found"));
    }

    //Print the contents of address book
    public void print() {
        System.out.println("ADDRESS BOOK DETAILS : ");
        personList.forEach(details -> System.out.println(details));
    }

    @Override
    public boolean equals(Object name) {
        return personList.stream().anyMatch(personName -> (personName.getFirstName() + " "
                + personName.getLastName()).equalsIgnoreCase((String) name));
    }
}