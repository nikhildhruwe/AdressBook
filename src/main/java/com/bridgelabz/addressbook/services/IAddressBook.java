package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.model.PersonDetails;

import java.util.ArrayList;

public interface IAddressBook {
    ArrayList<PersonDetails> addPerson(ArrayList<PersonDetails> personList);
    ArrayList<PersonDetails> deletePerson(ArrayList<PersonDetails> personList);
    void editPerson(ArrayList<PersonDetails> personList);
    void sortByName(ArrayList<PersonDetails> personList);
    void sortByCityStateZip(ArrayList<PersonDetails> personList);
    void viewPersonCityState(ArrayList<PersonDetails> personList);
    void cityOrState(ArrayList<PersonDetails> personList);
    void print(ArrayList<PersonDetails> personList);

}
