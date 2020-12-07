package sample;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Person {
    String name;
    String faculty;
    Integer id;

    public Person(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Person(JSONObject jsonObject) {
        this.id = Integer.parseInt(jsonObject.get("id").toString());
        this.name = jsonObject.get("label").toString();
        this.faculty = jsonObject.get("description").toString();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", id=" + id +
                '}';
    }

    public static void main(String[] args) {
    }
}
