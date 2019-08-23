import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static DbManager ssm = new SqlServerManager();
    public static void main( String args[]) throws SQLException {
        ssm.connect();
        Scanner scan = new Scanner(System.in);
        Connection connection = ssm.getConnection();
        StudentDAO sd= new StudentDAO(connection);
        FacultyDAO fd = new FacultyDAO(connection);
        Printer pr = new Printer();
        CommandParser commandParser = new CommandParser(sd, fd, pr);
        while(true){
            try {
                if (commandParser.readCommand(scan) == false)
                    break;
            }catch (RuntimeException e){
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
