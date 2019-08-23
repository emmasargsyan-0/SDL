import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Printer {

    public void printStudentsByFaculty(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int id = resultSet.getInt("Id");
                System.out.println("Name - " + name + ", Lastname - " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printTable(ResultSet rs) throws SQLException {
        String word;
        // Prepare metadata object and get the number of columns.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        // Print column names (a header).
        for (int i = 1; i <= columnsNumber; i++) {
            word = rsmd.getColumnName(i);
            if (i > 1){
                System.out.print("|");
            }

            System.out.print(word);
            for (int j = 0; j <5-word.length() ; j++) {
                System.out.print(" ");
            }

        }
        System.out.println("");

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                {
                    System.out.print("|");

                }
                word = rs.getString(i);
                System.out.print(word);
                if(word!=null) {
                    for (int j = 0; j < 5 - word.length(); j++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }
}
