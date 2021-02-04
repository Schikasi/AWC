package sample;

import javafx.util.StringConverter;
import org.json.simple.JSONObject;

import java.io.IOException;

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
                    return new Auditorium(Integer.parseInt(s.substring(start--, end++)), s.substring(0, start-1), s.substring(end));
            }
            return null;

        }
    }

    private final Integer id;
    private final String label;
    private final String type;


    public static Auditorium tryToGetAuditorium(String label){
        if (label == null)
            throw new NullPointerException("");
        try {
            ConnectionToAPI ctapi = new ConnectionToAPI();
            ctapi.createResponse(ConnectionToAPI.TypeResponse.SEARCH, ConnectionToAPI.ObjectResponse.AUDITORIUM, label).makeRequest();
            for (var obj :
                    ctapi.getJsonArray())
                if (((JSONObject) obj).get("label").toString().equals(label))
                    return new Auditorium((JSONObject) ctapi.getJsonArray().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Auditorium(label);
    }

    private Auditorium(String label) {
        this.label = label;
        this.id = -1;
        this.type = null;
    }

    protected Auditorium(Integer id, String label, String type) {
        this.label = label;
        this.id = -1;
        this.type = null;
    }

    protected Auditorium(JSONObject auditorium) {
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
