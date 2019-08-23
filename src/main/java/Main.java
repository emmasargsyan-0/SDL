import java.sql.Connection;

public class Main {

    static DbManager ssm = new SqlServerManager();
    public static void main( String args[])
    {
        ssm.connect();
        Connection connection = ssm.getConnection();
        StudentDAO sd = new StudentDAO(connection);
        sd.add(new Student("Valod",2));
    }
}
