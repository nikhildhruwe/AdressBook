package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.PersonDetails;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonRecordUtil {
    UserInputValidation user = new UserInputValidation();
    Scanner scan = new Scanner(System.in);

    public PersonDetails addPerson(List<PersonDetails> personList) {
        String firstName;
        String lastName;
        while (true) {
            System.out.println("\nAdd Person Details :");
            firstName = user.getFirstName();
            lastName = user.getLastName();
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
        int zip = user.getZipCode();
        String phone = user.getPhoneNumber();
        return new PersonDetails(firstName, lastName, address, city, state, zip, phone);
    }

    public PersonDetails deletePerson(List<PersonDetails> personList) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        return personList.stream().filter(name -> (name.getFirstName() + " "
                + name.getLastName()).equalsIgnoreCase(firstName
                + " " + lastName)).findAny().orElse(null);
    }

    public void editPerson(List<PersonDetails> personList) {
        try {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
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
                            String address = user.getAddress();
                            personDetails.setAddress(address);
                            break;
                        case 2:
                            String city = user.getCity();
                            personDetails.setCity(city);
                            break;
                        case 3:
                            String state = user.getState();
                            personDetails.setState(state);
                            break;
                        case 4:
                            int zip = user.getZipCode();
                            personDetails.setZip(zip);
                            break;
                        case 5:
                            String phone = user.getPhoneNumber();
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
        String state = user.getState();
        String city = user.getCity();
        List<PersonDetails> collect = personList.stream().
                filter(name -> (name.getState().equals(state) && name.getCity().equals(city))).
                collect(Collectors.toList());
        collect.forEach(personDetails -> System.out.println("Person: " + personDetails.getFirstName() + " "
                + personDetails.getLastName()));
        if (collect.isEmpty())
            System.out.println("No Records Found.");
    }

    public void viewByCityOrState(List<PersonDetails> personList) {
        String state = user.getState();
        String city = user.getCity();
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
