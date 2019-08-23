public class Main {

    static DbManager ssm = new SqlServerManager();
    public static void main( String args[])
    {
        ssm.connect();
    }
}
