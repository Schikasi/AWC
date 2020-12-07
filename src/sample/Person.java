package sample;

import org.json.simple.JSONObject;

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
}
