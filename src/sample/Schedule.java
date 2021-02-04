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
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Schedule(JSONArray jArr, LocalDate startDate, LocalDate endDate) throws ParseException {
        this.startDate = startDate;
        this.endDate = endDate;
        Couple tmp;
        for (Object jOb : jArr) {
            tmp = new Couple((JSONObject) jOb);
            if (!couples.isEmpty() && tmp.equals(couples.get(couples.size() - 1))) {
                couples.get(couples.size() - 1).addGroup(tmp.getGroups().get(0).getLabel());
            } else {
                couples.add(tmp);
            }
        }
    }

    public int getCountCouples() {
        return couples.size();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


    //Тестировал класс
    public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException, ParseException {
        System.out.println("Введите аудиторию:");
        String aud = new Scanner(System.in).next();
        LocalDate start = LocalDate.of(2020, 9, 1);
        LocalDate end = LocalDate.of(2020, 9, 14);
        ConnectionToAPI cta = new ConnectionToAPI();
        cta.createResponse(ConnectionToAPI.TypeResponse.SEARCH, ConnectionToAPI.ObjectResponse.AUDITORIUM, aud);
        cta.makeRequest();
        Integer idAud = new Auditorium((JSONObject) cta.getJsonArray().get(0)).getId();
        cta.createResponse(ConnectionToAPI.TypeResponse.SCHEDULE, ConnectionToAPI.ObjectResponse.AUDITORIUM, idAud.toString(), start, end);
        cta.makeRequest();
        JSONArray jArr = cta.getJsonArray();
        Schedule sch = new Schedule(jArr, start, end);
        ScheduleAnalyzer scheduleAnalyzer = new ScheduleAnalyzer(sch);
        System.out.println(scheduleAnalyzer.getWorkloadPercent());
    }
}
