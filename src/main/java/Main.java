import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    static DbManager ssm = new SqlServerManager();
    public static void main( String args[]) throws SQLException {
        ssm.connect();
        Connection connection = ssm.getConnection();
        StudentDAO sd= new StudentDAO(connection);
        Printer pr= new Printer();
        pr.printTable(sd.allStudents());
    }
}
