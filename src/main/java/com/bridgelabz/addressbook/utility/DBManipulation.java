package com.bridgelabz.addressbook.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBManipulation {
    UserInputValidation user = new UserInputValidation();
    public void addPerson(Connection con) throws SQLException {
        Scanner scan = new Scanner(System.in);
        final String INSERT_PERSON_QUERRY = "INSERT INTO person (first_name, last_name, address, city, state, zip, phone)" +
                " VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(INSERT_PERSON_QUERRY);
        System.out.println("\nAdd Person Details :");

        String firstName = user.getFirstName();
        String lastName = user.getFirstName();
        String address = user.getAddress();
        String city = user.getCity();
        String state = user.getState();
        System.out.print("Enter Zip: ");
        int zip = scan.nextInt();
        String phone = user.getPhoneNumber();

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
    }

    public void deletePerson(Connection con) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        String DELETE_PERSON_QUERRY = "DELETE FROM person WHERE " +
                "first_name = '" + firstName + "' AND last_name = '" + lastName + "'";

        PreparedStatement statement;
        try {
            statement = con.prepareStatement(DELETE_PERSON_QUERRY);
            statement.execute();
            System.out.println("Person details added succesfuly....");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void display(Connection connection) throws SQLException {
        String DISPLAY_PERSON_QUERRY = "SELECT * FROM person";
        this.displayDBDetails(DISPLAY_PERSON_QUERRY, connection);
    }

    private void displayDBDetails(String querry, Connection connection) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(querry);
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void edit(Connection connection) {
        String fieldName = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();

        boolean status = true;
        while (status) {
            System.out.println("Choose the field to update\n1. Address\n2. City\n3. State\n" + "4. Zip\n" +
                    "5. Phone number\n6. Save.");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    fieldName = "address";
                    break;
                case 2:
                    fieldName = "city";
                    break;
                case 3:
                    fieldName = "state";
                    break;
                case 4:
                    fieldName = "zip";
                    break;
                case 5:
                    fieldName = "phone";
                    break;
                case 6:
                    System.out.println("Saved.");
                    status = false;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
            if (status) {
                System.out.print("Enter new Value: ");
                String newValue = scan.next();
                String UPDATE_PERSON_QUERRY = "UPDATE person SET " + fieldName + " = '" + newValue + "' WHERE first_name" +
                        " = '" + firstName + "' AND last_name = '" + lastName + "'";
                try {
                    PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON_QUERRY);
                    statement.execute();
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void sortByName(Connection connection) {
        String SORT_PERSON_NAME_QUERRY = "SELECT * FROM person ORDER BY name ASC";
        try {
            PreparedStatement statement = connection.prepareStatement(SORT_PERSON_NAME_QUERRY);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void sortByStateCityZip(Connection connection) {
        String query = null;
        Scanner scan = new Scanner(System.in);
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
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter City: ");
        String city = scan.nextLine();
        System.out.print("Enter State: ");
        String state = scan.nextLine();
        String query = "SELECT * FROM person WHERE city = '" + city + "' AND state = '" + state + "'";
        this.displayDBDetails(query, connection);
    }

    public void viewByCityORState(Connection connection) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter City or State: ");
        String place = scan.nextLine();
        String query = "SELECT * FROM person WHERE city = '" + place + "' OR state = '" + place + "'";
        this.displayDBDetails(query, connection);
    }
}
