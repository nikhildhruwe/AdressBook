package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.model.PersonDetails;
import com.bridgelabz.addressbook.utility.PersonRecordUtil;

import java.util.Comparator;
import java.util.List;

public class PersonRecord implements IAddressBook {
    PersonRecordUtil personRecordUtil = new PersonRecordUtil();

    //******Adding new Person Record to List.*****//
    public List<PersonDetails> addPerson(List<PersonDetails> personList) {
        PersonDetails personDetails = personRecordUtil.addPerson(personList);
        personList.add(personDetails);
        return personList;
    }

    //******Deleting person record*****//
    public void deletePerson(List<PersonDetails> personList) {
        PersonDetails personDetails = personRecordUtil.deletePerson(personList);
        if (personDetails != null)
            personList.remove(personDetails);
        else
            System.out.println("No Records Found.");
    }

    //Editing person record except their name*****//
    public void editPerson(List<PersonDetails> personList) {
        personRecordUtil.editPerson(personList);
    }

    //Sorting list by name
    public void sortByName(List<PersonDetails> personList) {
        personList.sort(Comparator.comparing(PersonDetails::getFirstName));
    }

    //Sorting list by City,Zip Or state.
    public void sortByCityStateZip(List<PersonDetails> personList) {
        System.out.println("choose:\n1:City\n2:State\n3:Zip");
        int userChoice = personRecordUtil.getUserChoice();
        switch (userChoice) {
            case 1:
                personList.sort(Comparator.comparing(PersonDetails::getCity));
                print(personList);
                break;
            case 2:
                personList.sort(Comparator.comparing(PersonDetails::getState));
                print(personList);
                break;
            case 3:
                personList.sort(Comparator.comparingInt(PersonDetails::getZip));
                print(personList);
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    //******View person available by giving state and city name.****//
    public void viewPersonCityState(List<PersonDetails> personList) {
        personRecordUtil.viewPersonCityState(personList);
    }

    //******View person in particular city or state.******//
    public void cityOrState(List<PersonDetails> personList) {
        personRecordUtil.viewByCityOrState(personList);
    }

    //Print the contents of address book
    public void print(List<PersonDetails> personList) {
        System.out.println("ADDRESS BOOK DETAILS : ");
        personList.forEach(System.out::println);
    }
}