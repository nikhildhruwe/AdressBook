package com.bridgelabz.addressbook;
import java.util.ArrayList;
import java.util.Scanner;

public class AdressBook {
    public static void main(String[] args) {
        System.out.println("\tWelcome to Adress Book");
        ArrayList<PersonDetails> list = new ArrayList<>();
        PersonDetails person = new PersonDetails("Nikhil","Dhruwe","lingampally","Hyderabad","Telangana",500032,"789654123");
        person.addPerson();
        //System.out.println(list);
        person.print();

    }
}