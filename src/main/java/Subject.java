public class Subject {
    private int id;
    private int facultyId;
    private String name;

    public Subject(int id, int facultyId, String name) {
        this.id = id;
        this.facultyId = facultyId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public String getName() {
        return name;
    }
}
