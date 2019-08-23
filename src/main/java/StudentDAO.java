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

    public ResultSet allStudentList(){
        ResultSet resultSet = null;
        try {
            String query="SELECT Students.[Name], AVG(CAST(Grades.Grade as float)) AS Grade \n" +
                    "FROM Students\n" +
                    "JOIN Grades on Students.Id=Grades.StudentId\n" +
                    "GROUP BY Students.[Name]";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet allStudents(){
        ResultSet resultSet = null;
        try {
            String query = "SELECT Students.[Id], Students.[Name], Faculties.[Name] as Faculty_Name\n" +
                    "FROM Students\n" +
                    "JOIN Faculties on Students.FacultyId=Faculties.Id\n" +
                    "GROUP BY Students.[Id], Students.[Name], Faculties.[Name]\n" +
                    "Order By Students.[Name]";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int id = resultSet.getInt("Id");
                String facultyName = resultSet.getString("Faculty_Name");
                System.out.println("Name - " + name + ", Lastname - " + id +
                        ", Specialization - " + facultyName );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet studentsByFaculty(int facultyId){
        ResultSet resultSet = null;
        try {
            String query = "SELECT Students.[Id], Students.[Name]\n" +
                    "FROM Students\n" +
                    "JOIN Faculties on Students.FacultyId = Faculties.Id\n" +
                    "WHERE Faculties.Id = ?\n" +
                    "GROUP BY Students.[Id], Students.[Name]";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, facultyId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int id = resultSet.getInt("Id");
                System.out.println("Name - " + name + ", Lastname - " + id );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
