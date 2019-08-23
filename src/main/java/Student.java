import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private int facultyId;
    private ArrayList<Mark> marks = new ArrayList<Mark>();

    public void setName(String name) {
        this.name = name;
    }

    public Student(int id, String name, int facultyId, ArrayList<Mark> marks){
        this.id = id;
        this.name = name;
        this.facultyId=facultyId;
        this.marks=marks;
    }
    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Student(String name, int facId){
        this.facultyId = facId;
        this.name = name;
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


}
