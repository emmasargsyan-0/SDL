import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentDAO implements DAO<Student>{

    private Connection connection;

    public StudentDAO(Connection connection){
        this.connection = connection;
    }

    public Student get(String id){
        return null;
    }

    public boolean add(Student obj){
        try {
            PreparedStatement statement;
            String query = "Insert into Students(name , FacultyId) " +
                    "VALUES (?,?)";
            statement= connection.prepareStatement(query);
            statement.setString(1,obj.getName());
            statement.setString(2,String.valueOf(obj.getFacultyId()));
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean remove(Student obj){
        return true;
    }

    public Map allStudentList(){
        Map students = new HashMap();
        try {
            String query="SELECT Students.[Name], AVG(CAST(Grades.Grade as float)) AS Grade \n" +
                    "FROM Students\n" +
                    "JOIN Grades on Students.Id=Grades.StudentId\n" +
                    "GROUP BY Students.[Name]";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String grade = resultSet.getString("Grade");
                students.put(name, grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

}
