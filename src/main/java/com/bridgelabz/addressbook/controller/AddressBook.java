package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.model.PersonDetails;
import com.bridgelabz.addressbook.services.DBConnection;
import com.bridgelabz.addressbook.utility.DBManipulation;
import com.bridgelabz.addressbook.utility.FileOperations;
import com.bridgelabz.addressbook.services.PersonRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class AddressBook {
    private static final String JSON_FILE_PATH = "./src/main/resources/personList.json";
    private static final String CSV_FILE_PATH = "./src/main/resources/personList.csv";
    private static final String JSON_USING_GSON_FILE_PATH = "./src/main/resources/personListUsingGSON.json";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("\tWelcome to Address Book");
        PersonRecord personRecord = new PersonRecord();
        FileOperations fileOperations = new FileOperations();
        DBManipulation DBManipulation = new DBManipulation();
        Connection connection = null;
        // Menu for operations on Array List
        Scanner scan = new Scanner(System.in);
        int operationType = 0;
        String filePath = null;
        System.out.println("Select file operation type \n1. JSON File Conversion" + "\n2. CSV file Conversion\n" +
                "3. JSON using gson\n4. Operations using Data Base");
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
            case 4:
                connection = DBConnection.getConnection();
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
                            if (operationChoice == 4 ){
                                DBManipulation.addPerson(connection);
                                break;
                            }
                            personList = fileOperations.readFromFile(filePath, operationType);
                            List<PersonDetails> personDetails = personRecord.addPerson(personList);
                            fileOperations.writeToFile(personDetails, filePath, operationType);
                            break;
                        case 2:
                            if (operationChoice == 4 ){
                                DBManipulation.deletePerson(connection);
                                break;
                            }
                            personList = fileOperations.readFromFile(filePath, operationType);
                            personRecord.deletePerson(personList);
                            fileOperations.writeToFile(personList, filePath, operationType);
                            break;
                        case 3:
                            if (operationChoice == 4 ){
                                DBManipulation.display(connection);
                                break;
                            }
                            personList = fileOperations.readFromFile(filePath, operationType);
                            personRecord.print(personList);
                            break;
                        case 4:
                            if (operationChoice == 4 ){
                                DBManipulation.edit(connection);
                                break;
                            }
                            personList = fileOperations.readFromFile(filePath, operationType);
                            personRecord.editPerson(personList);
                            fileOperations.writeToFile(personList, filePath, operationType);
                            break;
                        case 5:
                            if (operationChoice == 4 ){
                                DBManipulation.sortByName(connection);
                                break;
                            }
                            personList = fileOperations.readFromFile(filePath, operationType);
                            personRecord.sortByName(personList);
                            fileOperations.writeToFile(personList, filePath, operationType);
                            break;
                        case 6:
                            if (operationChoice == 4 ){
                                DBManipulation.sortByStateCityZip(connection);
                                break;
                            }
                            personList = fileOperations.readFromFile(filePath, operationType);
                            personRecord.sortByCityStateZip(personList);
                            fileOperations.writeToFile(personList, filePath, operationType);
                            break;
                        case 7:
                            if (operationChoice == 4 ){
                                DBManipulation.viewByCityAndState(connection);
                                break;
                            }
                            personList = fileOperations.readFromFile(filePath, operationType);
                            personRecord.viewPersonCityState(personList);
                            fileOperations.writeToFile(personList, filePath, operationType);
                            break;
                        case 8:
                            if (operationChoice == 4 ){
                                DBManipulation.viewByCityORState(connection);
                                break;
                            }
                            personList = fileOperations.readFromFile(filePath, operationType);
                            personRecord.cityOrState(personList);
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