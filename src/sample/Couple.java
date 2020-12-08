package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Couple {
    String discipline;
    DayOfWeek dayOfWeek;
    Integer lessonNumber;
    ArrayList<String> groups = new ArrayList<String>(1);
    String lecturer;
    LocalDate date;

    public Couple(JSONObject jOb) throws ParseException {
        this.discipline = jOb.get("discipline").toString();
        this.date = LocalDate.parse(jOb.get("date").toString(),EntityCreator.DATE_FORMAT);
        this.dayOfWeek = this.date.getDayOfWeek();
        this.lessonNumber = Integer.parseInt(jOb.get("lessonNumberStart").toString());
        this.groups.add(( jOb.get("subGroup")==null?jOb.get("group").toString():(jOb.get("subGroup").toString())));
        this.lecturer = jOb.get("lecturer").toString();
    }

    public String getDiscipline() {
        return discipline;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void addGroup(String group){
        groups.add(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple couple = (Couple) o;
        return discipline.equals(couple.discipline) && dayOfWeek == couple.dayOfWeek && lessonNumber.equals(couple.lessonNumber) && lecturer.equals(couple.lecturer) && date.equals(couple.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discipline, dayOfWeek, lessonNumber, lecturer, date);
    }

    @Override
    public String toString() {
        return "Couple{" +
                "discipline='" + discipline + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", lessonNumber=" + lessonNumber +
                ", groups=" + groups +
                ", lecturer='" + lecturer + '\'' +
                ", date=" + date +
                '}';
    }
}
