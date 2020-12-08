package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule {
    ArrayList<Couple> couples = new ArrayList<Couple>();

    public Schedule(JSONArray jArr) throws ParseException {
        Couple tmp;
        for (Object jOb : jArr) {
            tmp = new Couple((JSONObject) jOb);
            if (!couples.isEmpty() && tmp.equals(couples.get(couples.size() - 1))) {
                couples.get(couples.size() - 1).addGroup(tmp.getGroups().get(0));
            } else {
                couples.add(tmp);
            }
        }
    }

    public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException, ParseException {
        Schedule schedule = new Schedule(EntityCreator.getJsonArray(EntityCreator.RequestObject.AUDITORIUM, "73", LocalDate.now(), LocalDate.now()));
        for (var couple : schedule.couples) {
            System.out.println(couple);
        }
    }
}
