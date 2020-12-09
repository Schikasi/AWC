package sample;

import org.json.simple.JSONObject;

public class Auditorium {
    private final Integer id;
    private final String label;
    private final String type;

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

    public Integer getId() {
        return this.id;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }
}
