package com.bridgelabz.addressbook;

public class AdressBook {
    public static void main(String[] args) {
        System.out.println("\tWelcome to Adress Book");

        PersonDetails personDetails = new PersonDetails("Nikhil", "Dhruwe", "lingampally", "Telangana", "Hyderabad", 500032, "9988776655");
        personDetails.print();

    }
}