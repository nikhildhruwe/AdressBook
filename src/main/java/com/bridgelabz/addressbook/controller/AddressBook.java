package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.model.PersonDetails;
import com.bridgelabz.addressbook.utility.FileOperations;
import com.bridgelabz.addressbook.services.PersonComputation;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class AddressBook {
    private static final String JSON_FILE_PATH = "./src/main/resources/personList.json";
    private static final String CSV_FILE_PATH = "./src/main/resources/personList.csv";
    private static final String JSON_USING_GSON_FILE_PATH = "./src/main/resources/personListUsingGSON.json";

    public static void main(String[] args) {
        System.out.println("\tWelcome to Address Book");
        PersonComputation personComputation = new PersonComputation();
        FileOperations fileOperations = new FileOperations();
        // Menu for operations on Array List
        Scanner scan = new Scanner(System.in);
        int operationType = 0;
        String filePath = null;
        System.out.println("Select file operation type \n1. JSON File Conversion" +
                                                                "\n2. CSV file Conversion\n3. JSON using gson");
        int operationChoice = scan.nextInt();
        switch (operationChoice) {
            case 1:
                operationType = 1;
                filePath = JSON_FILE_PATH;
                break;
            case 2:
                operationType = 2;
                filePath = CSV_FILE_PATH;
                break;
            case 3:
                operationType = 3;
                filePath = JSON_USING_GSON_FILE_PATH;
                break;
            default:
                System.out.println("Invalid Input");
                exit(0);
        }
        boolean status = true;
        try {
            while (status) {
                System.out.println("\nSelect : \n1: Add Person details \n2: Delete \n3: Display \n4: Edit Record" +
                        " \n5: Sort by Name \n6: Sort by city,State or Zip \n7: View Person by City and State" +
                        " \n8: View Person by City or State \n9: Exit");

                List<PersonDetails> personList;
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        personList = fileOperations.readFromFile(filePath, operationType);
                        List<PersonDetails> personDetails = personComputation.addPerson(personList);
                        fileOperations.writeToFile(personDetails, filePath, operationType);
                        break;
                    case 2:
                        personList = fileOperations.readFromFile(filePath, operationType);
                        personComputation.deletePerson(personList);
                        fileOperations.writeToFile(personList, filePath, operationType);
                        break;
                    case 3:
                        personList = fileOperations.readFromFile(filePath, operationType);
                        personComputation.print(personList);
                        break;
                    case 4:
                        personList = fileOperations.readFromFile(filePath, operationType);
                        personComputation.editPerson(personList);
                        fileOperations.writeToFile(personList, filePath, operationType);
                        break;
                    case 5:
                        personList = fileOperations.readFromFile(filePath, operationType);
                        personComputation.sortByName(personList);
                        fileOperations.writeToFile(personList, filePath, operationType);
                        break;
                    case 6:
                        personList = fileOperations.readFromFile(filePath, operationType);
                        personComputation.sortByCityStateZip(personList);
                        fileOperations.writeToFile(personList, filePath, operationType);
                        break;
                    case 7:
                        personList = fileOperations.readFromFile(filePath, operationType);
                        personComputation.viewPersonCityState(personList);
                        fileOperations.writeToFile(personList, filePath, operationType);
                        break;
                    case 8:
                        personList = fileOperations.readFromFile(filePath, operationType);
                        personComputation.cityOrState(personList);
                        fileOperations.writeToFile(personList, filePath, operationType);
                        break;
                    case 9:
                        status = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}