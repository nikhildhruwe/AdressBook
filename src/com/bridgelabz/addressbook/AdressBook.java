package com.bridgelabz.addressbook;
import java.util.ArrayList;
import java.util.Scanner;

public class AdressBook {
    public static void main(String[] args) {
        System.out.println("\tWelcome to Adress Book");
        AdressBook adressBook=new AdressBook();
        Scanner scan = new Scanner(System.in);
        boolean status=true;
        while(status==true)
        {
            System.out.println("Select : \n 1: Add Person details \n2: Delete \n3: Display \n4: Exit");
            int num=scan.nextInt();
            switch (num)
            {
                case 1 : PersonComputation.addPerson();
                    break;
                case 2: PersonComputation.deletePerson();
                    break;
                case 3 : PersonComputation.print();
                    break;
                case 4 :status=false;
                    break;
                default:
                    System.out.println("Invalid choise");

            }
        }
    }
}