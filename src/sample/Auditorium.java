package sample;

import javafx.util.StringConverter;
import org.json.simple.JSONObject;

public class Auditorium {
    public static class strConvAud extends StringConverter<Auditorium> {

        @Override
        public String toString(Auditorium auditorium) {
            if (auditorium != null)
                return auditorium.getLabel() + " (" + auditorium.getId() + ")\n" + auditorium.getType();
            else
                return "";
        }

        @Override
        public Auditorium fromString(String s) {
            if (s != null) {
                int start = s.indexOf('(') + 1;
                int end = s.indexOf(')');
                if (start < end)
                    return new Auditorium(Integer.parseInt(s.substring(start--, end++)), s.substring(0, start), s.substring(end));
            }
            return null;

        }
    }

    private final Integer id;
    private final String label;
    private final String type;

    public Auditorium(Integer id, String label, String type) {
        this.id = id;
        this.label = label;
        this.type = type;
    }

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
