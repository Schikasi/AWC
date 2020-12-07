package sample;

import org.json.simple.JSONObject;

public class Auditorium {
    Integer id;
    String label;
    String type;

    public Auditorium(JSONObject auditorium) {
        this.id = Integer.parseInt(auditorium.get("id").toString());
        this.label = auditorium.get("label").toString();
        this.type = auditorium.get("description").toString();
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
