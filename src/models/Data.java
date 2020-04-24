package models;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

import java.nio.file.Files;
import java.util.ArrayList;

public class Data {
    public static boolean writeToJSON(Person person,String path){
        try {

            FileWriter fw=new FileWriter(path);
            Gson gson = new Gson();
            gson.toJson(person,fw);
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Person> readFromJson(String path){
        if(!new File(path).exists()){
            return new ArrayList<>();
        }
        try {
            JsonReader reader=new JsonReader(new FileReader(path));
            Gson gson = new Gson();
            return gson.fromJson(reader,new TypeToken<ArrayList<Person>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
