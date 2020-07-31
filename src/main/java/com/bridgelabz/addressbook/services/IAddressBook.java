package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.model.PersonDetails;

import java.util.List;

public interface IAddressBook {
    List<PersonDetails> addPerson(List<PersonDetails> personList);
    void deletePerson(List<PersonDetails> personList);
    void editPerson(List<PersonDetails> personList);
    void sortByName(List<PersonDetails> personList);
    void sortByCityStateZip(List<PersonDetails> personList);
    void viewPersonCityState(List<PersonDetails> personList);
    void cityOrState(List<PersonDetails> personList);
    void print(List<PersonDetails> personList);

}
