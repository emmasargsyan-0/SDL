import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private String surname;
    private int facultyId;
    private ArrayList<Mark> marks = new ArrayList<Mark>();

    public void setName(String name) {
        this.name = name;
    }

    public Student(int id, String name, String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
