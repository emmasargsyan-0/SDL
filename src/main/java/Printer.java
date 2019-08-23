import java.sql.ResultSet;
import java.sql.SQLException;

public class Printer {

    public void printStudentsByFaculty(ResultSet resultSet){
        try {
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int id = resultSet.getInt("Id");
                System.out.println("Name - " + name + ", Lastname - " + id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
