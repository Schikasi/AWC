package sample;

import org.json.simple.JSONObject;

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
