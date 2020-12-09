package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule {
    private final ArrayList<Couple> couples = new ArrayList<Couple>();
    private final LocalDate start;
    private final LocalDate end;

    public Schedule(JSONArray jArr, LocalDate start, LocalDate end) throws ParseException {
        this.start = start;
        this.end = end;
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

    //Тестировал класс
    public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException, ParseException {
        System.out.println("Введите аудиторию:");
        String aud = new Scanner(System.in).next();
        LocalDate start = LocalDate.of(2020, 9, 1);
        LocalDate end = LocalDate.of(2020, 9, 14);
        Integer idAud = new Auditorium((JSONObject) EntityCreator.getJsonArray(EntityCreator.RequestObject.AUDITORIUM, aud).get(0)).getId();
        JSONArray jArr = EntityCreator.getJsonArray(EntityCreator.RequestObject.AUDITORIUM, idAud.toString(), start, end);
        Schedule sch = new Schedule(jArr, start,end);
        System.out.println(sch.couples.size() / 50.0 * 100 + "%");
    }

    public int getCountCouples() {
        return couples.size();
    }
}
