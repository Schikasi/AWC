package sample;

import org.json.simple.JSONObject;

public class Group {
    Integer id;
    String label;
    String faculty;

    public Group(Integer id, String label, String faculty) {
        this.id = id;
        this.label = label;
        this.faculty = faculty;
    }

    public Group(JSONObject group) {
        this.id = Integer.parseInt(group.get("id").toString());
        this.label = group.get("label").toString();
        this.faculty = group.get("description").toString();
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
