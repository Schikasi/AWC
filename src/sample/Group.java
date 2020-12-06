package sample;

public class Group {
    Integer id;
    String label;
    String faculty;

    public Group(Integer id, String label, String faculty) {
        this.id = id;
        this.label = label;
        this.faculty = faculty;
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
}
