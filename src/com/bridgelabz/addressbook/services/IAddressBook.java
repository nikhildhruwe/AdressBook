package com.bridgelabz.addressbook.services;

public interface IAddressBook {
    void addPerson();
    void deletePerson();
    void editPerson();
    void sortByName();
    void sortByCityStateZip();
    void viewPersonCityState();
    void cityOrState();
    void print();

}
