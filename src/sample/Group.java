package sample;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Group {
    private final Integer id;
    private final String label;
    private final String faculty;

    public static Group tryToGetGroup(String label){
        if (label == null)
            throw new NullPointerException("");
        try {
            ConnectionToAPI ctapi = new ConnectionToAPI();
            ctapi.createResponse(ConnectionToAPI.TypeResponse.SEARCH, ConnectionToAPI.ObjectResponse.GROUP, label).makeRequest();
            for (var obj :
                    ctapi.getJsonArray())
                if (((JSONObject) obj).get("label").toString().equals(label))
                    return new Group((JSONObject) ctapi.getJsonArray().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Group(label);
    }

    private Group(JSONObject group) {
        this.id = Integer.parseInt(group.get("id").toString());
        this.label = group.get("label").toString();
        this.faculty = group.get("description").toString();
    }

    private Group(String label) {
        this.label = label;
        this.id = -1;
        this.faculty = null;
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
