package com.bridgelabz.addressbook.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBManipulation {
    Scanner scan = new Scanner(System.in);
    UserInputValidation user = new UserInputValidation();

    public void addPerson(Connection con) {
        final String INSERT_PERSON_QUERY = "INSERT INTO person (first_name, last_name, address, city, state, zip, phone)" +
                " VALUES (?,?,?,?,?,?,?)";
        System.out.println("\nAdd Person Details :");
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String address = user.getAddress();
        String city = user.getCity();
        String state = user.getState();
        int zip = user.getZipCode();
        String phone = user.getPhoneNumber();
        try {
            PreparedStatement statement = con.prepareStatement(INSERT_PERSON_QUERY);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, city);
            statement.setString(5, state);
            statement.setInt(6, zip);
            statement.setString(7, phone);
            statement.execute();
            System.out.println("Person details added succesfuly....");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePerson(Connection con) {
        String DELETE_PERSON_QUERY = "DELETE FROM person WHERE " +
                "first_name = ? AND last_name = ?";
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(DELETE_PERSON_QUERY);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.execute();
            System.out.println("Person details added succesfuly....");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void display(Connection connection) {
        String DISPLAY_PERSON_QUERY = "SELECT * FROM person";
        this.displayDBDetails(DISPLAY_PERSON_QUERY, connection);
    }

    private void displayDBDetails(String query, Connection connection) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String phone = resultSet.getString("phone");
                System.out.println("ID:" + id + ",NAME: " + firstName + " " + lastName + "  " + "ADDRESS: " +
                        address + "  " + "CITY: " + city + "  " + "STATE: " + state +
                        "  " + "ZIPCODE: " + zip + "  " + "PHONE: " + phone);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void edit(Connection connection) {
        String fieldName = null;
        PreparedStatement statement ;
        String newValue = null;
        boolean status = false;
        String query = "SELECT * FROM person";
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        try {
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String DBFirstName = resultSet.getString("first_name");
                String DBlastName = resultSet.getString("last_name");
                if (firstName.equals(DBFirstName) && lastName.equals(DBlastName)){
                    status = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(!status){
            System.out.println("No such record found...");
            return;
        }
        while (status) {
            System.out.println("Choose the field to update\n1. Address\n2. City\n3. State\n" + "4. Zip\n" +
                    "5. Phone number\n6. Save.");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    fieldName = "address";
                    newValue = user.getAddress();
                    break;
                case 2:
                    fieldName = "city";
                    newValue= user.getCity();
                    break;
                case 3:
                    fieldName = "state";
                    newValue = user.getState();
                    break;
                case 4:
                    fieldName = "zip";
                    newValue = String.valueOf(user.getZipCode());
                    break;
                case 5:
                    fieldName = "phone";
                    newValue = user.getPhoneNumber();
                    break;
                case 6:
                    System.out.println("Saved.");
                    status = false;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
            if (status) {
                String UPDATE_PERSON_QUERY = "UPDATE person SET " + fieldName + " = '" + newValue + "' WHERE first_name" +
                        " = '" + firstName + "' AND last_name = '" + lastName + "'";
                try {
                    statement = connection.prepareStatement(UPDATE_PERSON_QUERY);
                    statement.execute();
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void sortByName(Connection connection) {
        String SORT_PERSON_NAME_QUERY = "SELECT * FROM person ORDER BY name ASC";
        try {
            PreparedStatement statement = connection.prepareStatement(SORT_PERSON_NAME_QUERY);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void sortByStateCityZip(Connection connection) {
        String query = null;
        System.out.println("choose:\n1:City\n2:State\n3:Zip");
        int userChoice = scan.nextInt();
        switch (userChoice) {
            case 1:
                query = "SELECT * FROM person ORDER BY city ASC";
                break;
            case 2:
                query = "SELECT * FROM person ORDER BY state ASC";
                break;
            case 3:
                query = "SELECT * FROM person ORDER BY zip ASC";
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        this.displayDBDetails(query, connection);
    }

    public void viewByCityAndState(Connection connection) {
        String query = "SELECT * FROM person WHERE city = ? AND state = ?";
        String city = user.getCity();
        String state = user.getState();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city);
            statement.setString(2, state);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.displayDBDetails(query, connection);
    }

    public void viewByCityORState(Connection connection) {
        String query = "SELECT * FROM person WHERE city = ? OR state = ?";
        String city = user.getCity();
        String state = user.getState();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city);
            statement.setString(2, state);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.displayDBDetails(query, connection);
    }
}
