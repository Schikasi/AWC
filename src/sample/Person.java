package sample;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Person {

    private final String name;
    private final String faculty;
    private final Integer id;

    public String getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public static Person tryToGetPerson(String name){
        if (name == null)
            throw new NullPointerException("");
        try {
            ConnectionToAPI ctapi = new ConnectionToAPI();
            ctapi.createResponse(ConnectionToAPI.TypeResponse.SEARCH, ConnectionToAPI.ObjectResponse.PERSON, name).makeRequest();
            for (var obj :
                    ctapi.getJsonArray())
                if (((JSONObject) obj).get("label").toString().equals(name))
                    return new Person((JSONObject) ctapi.getJsonArray().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Person(name);
    }

    private Person(JSONObject person) {
        this.id = Integer.parseInt(person.get("id").toString());
        this.name = person.get("label").toString();
        this.faculty = person.get("description").toString();
    }

    private Person(String name) {
        this.name = name;
        this.id = -1;
        this.faculty = null;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", id=" + id +
                '}';
    }
}
