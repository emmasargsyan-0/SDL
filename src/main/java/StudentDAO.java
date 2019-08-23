import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO implements DAO<Student>{
    private Connection connection ;
    StudentDAO(Connection connection){
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

}
