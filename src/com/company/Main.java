package com.company;

import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args)
    {
        task1();
        task2();
    }

    public static void task1()
    {
        JsonObject obj = convertUsersToJsonObject();
        writeJsonObjectToFile(obj);
        JsonObject jsonObject = readJsonObjectFromFile();
        ArrayList<User> users = convertJsonObjectToUsers(jsonObject);
        System.out.println(users);

    }

    public static JsonObject convertUsersToJsonObject()
    {
        Gson gson = new Gson();
        User user1 = new User(18,"Karen","Melikyan");
        User user2 = new User(34,"Mikayel","Smbatyan");
        User user3 = new User(5,"Hayk","Hovsepyan");
        User user4 = new User(1,"Katyush","Ksyush");
        User user5 = new User(2016,"Iisus","Khristos");
        JsonObject obj = new JsonObject();
        obj.addProperty("user1", gson.toJson(user1));
        obj.addProperty("user2", gson.toJson(user2));
        obj.addProperty("user3", gson.toJson(user3));
        obj.addProperty("user4", gson.toJson(user4));
        obj.addProperty("user5", gson.toJson(user5));
        return obj;
    }

    public static void writeJsonObjectToFile(JsonObject obj)
    {
        try {

            FileWriter file = new FileWriter("test2.json");
            file.write(obj.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonObject readJsonObjectFromFile()
    {
        JsonParser parser = new JsonParser();
        try {
            Object obj = parser.parse(new FileReader("test2.json"));
            return (JsonObject) obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<User> convertJsonObjectToUsers(JsonObject jsonObject)
    {
        ArrayList<User> users = new ArrayList<>();
        Gson gson = new Gson();
        User user1 = gson.fromJson(jsonObject.get("user1").getAsString(), User.class);
        User user2 = gson.fromJson(jsonObject.get("user2").getAsString(), User.class);
        User user3 = gson.fromJson(jsonObject.get("user3").getAsString(), User.class);
        User user4 = gson.fromJson(jsonObject.get("user4").getAsString(), User.class);
        User user5 = gson.fromJson(jsonObject.get("user5").getAsString(), User.class);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        return users;
    }

    public static void task2()
    {
        Gson gson = new Gson();
        ArrayList<Page> likedPages = new ArrayList<>();
        String sURL = "https://graph.facebook.com/v2.7/424674097697358/likes?access_token=EAACEdEose0cBAIBwdeVCBjxtu1gmlzhgCAoJyBkKoxIxJSXuJhcjpcUAzcbpy2FeCxwuhWk5Wpk3k8s8FitYJlRTGyVwlSCG3jCipoixDUdXeaNZBIBV2mHbIa3ySfoUrRf1DyptjXX097zZB1oJLRXfRAy67UlSVTDn9UmQZDZD";
        JsonArray msg = (JsonArray) readJsonObjectFromURL(sURL).get("data");
        for(JsonElement obj : msg)
        {
            Page newPage = gson.fromJson(obj.toString(), Page.class);
            likedPages.add(newPage);
        }
        Collections.sort(likedPages);
        System.out.println(likedPages);
    }

    public static JsonObject readJsonObjectFromURL(String sURL)
    {
        try {
            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            JsonParser parser = new JsonParser(); //from gson
            JsonObject root = (JsonObject) parser.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
            return root;
        }catch (IOException e){
            System.out.print("Can't connect to server");
            return null;
        }
    }
}
