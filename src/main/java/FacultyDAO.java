import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
