import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerManager implements DbManager {
    private Connection connection;
    @Override
    public void connect() {
        try {

            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://summerschool.westus2.cloudapp.azure.com:1433;database=group1",
                    "group_1",
                    "mWfL4mkR");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Connection getConnection()
    {
        return connection;
    }
}
