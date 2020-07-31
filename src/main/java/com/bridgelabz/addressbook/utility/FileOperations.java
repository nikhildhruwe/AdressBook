package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.PersonDetails;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FileOperations {


    public void writeToFile(ArrayList<PersonDetails> personList, String filePath, int operationType) {
        switch (operationType) {
            case 1:
                    JSONArray personArray = new JSONArray();
                    for (PersonDetails person : personList) {
                        JSONObject personDetails = new JSONObject();
                        personDetails.put("First Name", person.getFirstName());
                        personDetails.put("Last Name", person.getLastName());
                        personDetails.put("Address", person.getAddress());
                        personDetails.put("City", person.getCity());
                        personDetails.put("State", person.getState());
                        personDetails.put("Zip", person.getZip());
                        personDetails.put("Phone No.", person.getPhone());

                        JSONObject personObject = new JSONObject();
                        personObject.put("person", personDetails);
                        personArray.add(personObject);
                    }
                    try {
                        FileWriter fileWriter = new FileWriter(filePath);
                        fileWriter.append(personArray.toJSONString());
                        fileWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            case 2:
                    try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
                        StatefulBeanToCsv<PersonDetails> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                                .build();
                        beanToCsv.write(personList);
                    } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                        e.printStackTrace();
                    }
                    break;
        }
    }

    public ArrayList<PersonDetails> readFromFile(String filePath, int operationType) {
        ArrayList<PersonDetails> personDetailsList = new ArrayList<>();
        switch (operationType) {
            case 1:
                    JSONParser jsonParser = new JSONParser();
                    try {
                        FileReader reader = new FileReader(filePath);
                        Object obj = jsonParser.parse(reader);
                        JSONArray personList = (JSONArray) obj;
                        personList.forEach(person -> personDetailsList.add(parsePersonObject((JSONObject) person)));
                    } catch (ParseException | IOException e) {
                        e.printStackTrace();
                    }
                    break;
            case 2:
                    try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
                        csvReader.readNext();
                        String[] data;
                        while ((data = csvReader.readNext()) != null) {
                            personDetailsList.add(new PersonDetails(data[2], data[3], data[0], data[1], data[5],
                                    Integer.parseInt(data[6]), data[4]));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                break;
            default:
                System.out.println("Invalid Choice");
        }
        return personDetailsList;
    }

    private PersonDetails parsePersonObject(JSONObject person) {
        JSONObject personObj = (JSONObject) person.get("person");
        Long zip = (Long) personObj.get("Zip");
        return new PersonDetails((String) personObj.get("First Name"),
                (String) personObj.get("Last Name"),
                (String) personObj.get("Address"),
                (String) personObj.get("City"),
                (String) personObj.get("State"),
                zip.intValue(),
                (String) personObj.get("Phone No."));
    }
}


