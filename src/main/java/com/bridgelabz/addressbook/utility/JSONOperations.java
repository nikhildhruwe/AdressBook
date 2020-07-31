package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.PersonDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONOperations {


    public void writeToJSONFile(ArrayList<PersonDetails> personList, String jsonFilePath) {
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
            FileWriter fileWriter = new FileWriter(jsonFilePath);
            fileWriter.append(personArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PersonDetails> readFromJSON(String jsonFilePath) {
        JSONParser jsonParser = new JSONParser();
        ArrayList<PersonDetails> personDetailsList = new ArrayList<>();
        try {
            FileReader reader = new FileReader(jsonFilePath);
            Object obj = jsonParser.parse(reader);
            JSONArray personList = (JSONArray) obj;
            personList.forEach(person -> personDetailsList.add(parsePersonObject((JSONObject) person)));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(personDetailsList);
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


