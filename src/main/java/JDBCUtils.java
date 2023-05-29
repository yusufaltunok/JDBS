import java.sql.*;


public class JDBCUtils {

    //Bu class'ta tekrarlı kullanılacak methodlar oluşturacağız.
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static int update;

    // Database'e bağlanma methodu --> Connection return yapar
    public static Connection connectToDataBase() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "YNse&6677");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;

    }

    //Medunna database'e bağlanan method
    // (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))
    public static Connection connectToMedunnaDataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }


    // Statement oluşturma methodu --> Statement return yapar
    //  Statement statement = connection.createStatement();--> bunu method ile yapacağız

    public static Statement createStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;

    }

    //execute() methodu ile Query çalıştıran method
    public static boolean execute(String sql) {

        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //executeQuery() methodu ile Query çalıştıran method

        public static ResultSet executeQuery(String sql) {

            try {
               resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return resultSet;
        }

    //executeUpdate() methodu
        public static int executeUpdate(String sql) {

            try {
               update = statement.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return update;
        }

    //Bağlantıyı kapatan method

        public static void closeConnection(){
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }









}