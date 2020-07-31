package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.model.PersonDetails;
import com.bridgelabz.addressbook.utility.JSONOperations;
import com.bridgelabz.addressbook.services.PersonComputation;


import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    private static final String JSON_FILE_PATH = "./src/main/resources/personList.json";;

    public static void main(String[] args) {

        System.out.println("\tWelcome to Address Book");
        PersonComputation personComputation = new PersonComputation();
        JSONOperations jsonOperations = new JSONOperations();
        // Menu for operations on Array List
        Scanner scan = new Scanner(System.in);
        boolean status = true;
        try {
        while (status) {
            System.out.println("\nSelect : \n1: Add Person details \n2: Delete \n3: Display \n4: Edit Record" +
                    " \n5: Sort by Name \n6: Sort by city,State or Zip \n7: View Person by City and State" +
                    " \n8: View Person by City or State \n9: Exit");

            ArrayList<PersonDetails> personList;
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    personList = jsonOperations.readFromJSON(JSON_FILE_PATH);
                    ArrayList<PersonDetails> personDetails = personComputation.addPerson(personList);
                    jsonOperations.writeToJSONFile(personDetails, JSON_FILE_PATH);
                    break;
                case 2:
                    personList = jsonOperations.readFromJSON(JSON_FILE_PATH);
                    personComputation.deletePerson(personList);
                    jsonOperations.writeToJSONFile(personList, JSON_FILE_PATH);
                    break;
                case 3:
                    personList = jsonOperations.readFromJSON(JSON_FILE_PATH);
                    personComputation.print(personList);
                    break;
                case 4:
                    personList = jsonOperations.readFromJSON(JSON_FILE_PATH);
                    personComputation.editPerson(personList);
                    jsonOperations.writeToJSONFile(personList, JSON_FILE_PATH);
                    break;
                case 5:
                    personList = jsonOperations.readFromJSON(JSON_FILE_PATH);
                    personComputation.sortByName(personList);
                    jsonOperations.writeToJSONFile(personList, JSON_FILE_PATH);
                    break;
                case 6:
                    personList = jsonOperations.readFromJSON(JSON_FILE_PATH);
                    personComputation.sortByCityStateZip(personList);
                    jsonOperations.writeToJSONFile(personList, JSON_FILE_PATH);
                    break;
                case 7:
                    personList = jsonOperations.readFromJSON(JSON_FILE_PATH);
                    personComputation.viewPersonCityState(personList);
                    jsonOperations.writeToJSONFile(personList, JSON_FILE_PATH);
                    break;
                case 8:
                    personList = jsonOperations.readFromJSON(JSON_FILE_PATH);
                    personComputation.cityOrState(personList);
                    jsonOperations.writeToJSONFile(personList, JSON_FILE_PATH);
                    break;
                case 9:
                    status = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
           }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}