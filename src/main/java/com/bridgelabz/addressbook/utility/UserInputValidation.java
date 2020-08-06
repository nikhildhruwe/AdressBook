package com.bridgelabz.addressbook.utility;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInputValidation {
    Scanner scan = new Scanner(System.in);
    private static final String NAME_PATTERN = "^[A-Z][a-z]{2,}$";
    private static final String ADDRESS_PATTERN = "^[a-zA-Z0-9,-/#&@. ]{1,}$";
    private static final String ZIP_PATTERN = "^[1-9][0-9]{5}$";
    private static final String PHONE_PATTERN = "^[1-9][0-9]{9}$";

    private boolean validateInput(String input, String validator) {
        Pattern pattern = Pattern.compile(validator);
        if (pattern.matcher(input).matches())
            return true;
        else
            System.out.print("Invalid Input. Please Enter Again.\n");
        return false;
    }

    public String getFirstName() {
        String firstName;
        do {
            System.out.println("Enter your First Name");
            firstName = scan.next();
        } while (!validateInput(firstName, NAME_PATTERN));
        return firstName;
    }

    public String getLastName() {
        String lastName;
        do {
            System.out.println("Enter your First Name");
            lastName = scan.next();
        } while (!validateInput(lastName, NAME_PATTERN));
        return lastName;
    }

    public String getAddress() {
        String address;
        do {
            System.out.println("Enter your Address");
            address = scan.next();
        } while (!validateInput(address, ADDRESS_PATTERN));
        return address;
    }

    public String getCity() {
        String city;
        do {
            System.out.println("Enter your City");
            city = scan.next();
        } while (!validateInput(city, NAME_PATTERN));
        return city;
    }

    public String getState() {
        String state;
        do {
            System.out.println("Enter your State");
            state = scan.next();
        } while (!validateInput(state, NAME_PATTERN));
        return state;
    }

    public String getZipCode() {
        String zip;
        do {
            System.out.println("Enter your Zip code");
            zip = scan.next();
        } while (!validateInput(zip, ZIP_PATTERN));
        return zip;
    }

    public String getPhoneNumber() {
        String phoneNumber;
        do {
            System.out.println("Enter your Phone Number");
            phoneNumber = scan.next();
        } while (!validateInput(phoneNumber, PHONE_PATTERN));
        return phoneNumber;
    }
}
