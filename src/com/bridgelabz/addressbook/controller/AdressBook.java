package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.services.PersonComputation;

import java.util.Scanner;

public class AdressBook {
    public static void main(String[] args) {
        System.out.println("\tWelcome to Address Book");
        PersonComputation personComputation = new PersonComputation();

        // Menu for operations on Array List
        Scanner scan = new Scanner(System.in);
        boolean status = true;
        while (status) {
            System.out.println("\nSelect : \n1: Add Person details \n2: Delete \n3: Display \n4: Edit" +
                    " \n5: Sort by Name \n6: Sort by city,State or Zip \n7: Person by City&State \n8: View Person by City/State \n9: Exit");
            int num = scan.nextInt();
            switch (num) {
                case 1:
                    personComputation.addPerson();
                    break;
                case 2:
                    personComputation.deletePerson();
                    break;
                case 3:
                    personComputation.print();
                    break;
                case 4:
                    personComputation.editPerson();
                    break;
                case 5:
                    personComputation.sortByName();
                    break;
                case 6:
                    personComputation.sortByCityStateZip();
                    break;
                case 7:
                    personComputation.viewPersonCityState();
                    break;
                case 8:
                    personComputation.cityOrState();
                    break;
                case 9:
                    status = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}