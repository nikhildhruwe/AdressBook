package com.bridgelabz.addressbook;
import java.util.ArrayList;
import java.util.Scanner;

public class AdressBook {
    public static void main(String[] args) {
        System.out.println("\tWelcome to Adress Book");
//        ArrayList<PersonDetails> list = new ArrayList<>();
        PersonDetails person = new PersonDetails("Nikhil","Dhruwe","lingampally","Hyderabad","Telangana",500032,"789654123");
       // person.addPerson();
        //System.out.println(list);
        //person.print();
        Scanner scan = new Scanner(System.in);
        boolean status=true;
        while(status==true)
        {
            System.out.println("Select : \n 1: Add Person details \n2: Edit \n3: Display \n4: Exit");
            int num=scan.nextInt();
            switch (num)
            {
                case 1 : person.addPerson();
                    break;
                case 2: person.editPerson();
                    break;
                case 3 : person.print();
                    break;
                case 4 :status=false;
                    break;
                default:
                    System.out.println("Invalid choise");

            }
        }
    }
}