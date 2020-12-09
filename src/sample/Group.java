package sample;

import org.json.simple.JSONObject;

public class Group {
    private final  Integer id;
    private final  String label;
    private final  String faculty;

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
