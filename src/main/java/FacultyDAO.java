import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FacultyDAO implements DAO<Faculty> {

    private Connection connection;

    public FacultyDAO(Connection connection){
        this.connection = connection;
    }


    @Override
    public Faculty get(String id) {
        return null;
    }

    @Override
    public boolean add(Faculty obj) {
        return false;
    }

    @Override
    public boolean remove(Faculty obj) {
        return false;
    }


    public ResultSet faculties(){
        ResultSet resultSet = null;
        try {
            String query="SELECT Faculties.[Id], Faculties.[Name], COUNT(Students.[Id]) as Number \n" +
                    "FROM Students\n" +
                    "JOIN Faculties on Students.FacultyId = Faculties.Id\n" +
                    "GROUP BY Faculties.[Id], Faculties.[Name]\n";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void all(){
        Map< String,Object> all =
                new HashMap< String,Object>();
        all.put("faculties",allFaculties());
        all.put("subjects",allSubjects());
        all.put("students",allStudents());

        File file = new File("rfsd.json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            FileWriter fileWriter = new FileWriter(file, false);
            mapper.writeValue(System.out, all);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object> allFaculties(){
        ArrayList<Object> all = new ArrayList<Object>();
        ResultSet resultSet = null;
        try {
            String query="Select * from Faculties";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                all.add(new Faculty(resultSet.getString("Name"),
                        resultSet.getInt("Id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public ArrayList<Object> allSubjects(){
        ArrayList<Object> allSubjects = new ArrayList<Object>();
        ResultSet resultSet = null;
        try {
            String query="Select SubjectId, FacultyId, [Name] from Subjects " +
                    "join Subject_Faculty on Subjects.Id=Subject_Faculty.SubjectId";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allSubjects.add(new Subject(resultSet.getInt("SubjectId"),
                        resultSet.getInt("FacultyId"), resultSet.getString("Name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSubjects;
    }

    public ArrayList<Object> allStudents(){
        ArrayList<Object> allStudents = new ArrayList<Object>();
        ResultSet resultSet = null;
        try {
            String query="SELECT * FROM Students";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String query1="SELECT SubjectId, Grade FROM Grades Where StudentId=?";
                PreparedStatement statement1 = connection.prepareStatement(query1);
                statement1.setInt(1, id);
                ResultSet tmp = statement1.executeQuery();

                ArrayList<Mark> marks = new ArrayList<Mark>();
                while (tmp.next()) {
                    marks.add(new Mark(tmp.getInt("SubjectId"),
                            tmp.getInt("Grade")));
                }
                allStudents.add(new Student(id,resultSet.getString("Name"),
                        resultSet.getInt("FacultyId"), marks));
                marks=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

}
