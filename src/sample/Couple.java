package sample;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Couple {
    private final String discipline;
    private final Person lecturer;
    private final LocalDate date;
    private final Integer lessonNumber;
    private final ArrayList<Group> groups = new ArrayList();

    public Couple(JSONObject couple) throws ParseException {
        this.discipline = couple.get("discipline").toString();
        this.date = LocalDate.parse(couple.get("date").toString(), ConnectionToAPI.DATE_FORMAT);
        this.lessonNumber = Integer.parseInt(couple.get("lessonNumberStart").toString());
        //Исправить
        addGroup((couple.get("subGroup") == null ? couple.get("group").toString() : (couple.get("subGroup").toString())));

        this.lecturer = Person.tryToGetPerson(couple.get("lecturer").toString());
    }

    public String getDiscipline() {
        return discipline;
    }

    public Person getLecturer() {
        return lecturer;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public ArrayList<Group> getGroups() {
        return (ArrayList<Group>) groups.clone();
    }

    public void addGroup(String groupLabel) {
        Group group = Group.tryToGetGroup(groupLabel);
        groups.add(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple couple = (Couple) o;
        return discipline.equals(couple.discipline) && lessonNumber.equals(couple.lessonNumber) && lecturer.equals(couple.lecturer) && date.equals(couple.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discipline, lessonNumber, lecturer, date);
    }

    @Override
    public String toString() {
        return "Couple{" +
                "discipline='" + discipline + '\'' +
                ", lessonNumber=" + lessonNumber +
                ", groups=" + groups +
                ", lecturer='" + lecturer + '\'' +
                ", date=" + date +
                '}';
    }

}
