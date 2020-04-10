import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

class Database {

    private static Database instance = null;
    private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
    private String databaseUser = "musicuser";
    private String databasePassword = "musicpassword";
    private Connection connection;

    private Database() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection(jdbcUrl, databaseUser, databasePassword);
    }

    public Connection getConnection(){
        return connection;
    }

    public static Database getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null){
            instance = new Database();
        }

        return instance;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
