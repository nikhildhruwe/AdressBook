package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.PersonDetails;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonRecordUtil {
    UserInputValidation user = new UserInputValidation();
    public PersonDetails addPerson(List<PersonDetails> personList) {
        String firstName;
        String lastName;
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\nAdd Person Details :");
            firstName = user.getFirstName();
            lastName = user.getFirstName();
            String finalFirstName = firstName;
            String finalLastName = lastName;
            boolean status = personList.stream().anyMatch(personName -> (personName.getFirstName() + " "
                    + personName.getLastName()).equalsIgnoreCase(finalFirstName + " " + finalLastName));
            if (!status)
                break;
            System.out.println("Already available, please enter another name.");
        }
        String address = user.getAddress();
        String city = user.getCity();
        String state = user.getState();
        System.out.print("Enter Zip: ");
        int zip = scan.nextInt();
        String phone = user.getPhoneNumber();
        return new PersonDetails(firstName, lastName, address, city, state, zip, phone);
    }

    public PersonDetails deletePerson(List<PersonDetails> personList) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();

        return personList.stream().filter(name -> (name.getFirstName() + " "
                + name.getLastName()).equalsIgnoreCase(firstName
                + " " + lastName)).findAny().orElse(null);
    }

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
        }
    }

    public int getUserChoice() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

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

    public void viewByCityOrState(List<PersonDetails> personList) {
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
}
