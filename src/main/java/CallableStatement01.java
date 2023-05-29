
import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "YNse&6677");
        Statement statement = connection.createStatement();

        //  1. Örnek: Selamlama yapan bir function'ı Callable Statement ile çağırınız

        // Callable Statement Adımları:

        //1. Adım: Function kodunu yazınız
        String sql = "CREATE OR REPLACE FUNCTION selamlama(x TEXT) RETURNS TEXT AS " +
                "$$ BEGIN RETURN 'Merhaba ' || x || ', nasılsın?'; " +
                "END; $$ LANGUAGE plpgsql;";


        //2. Adım: Function kodunu çalıştırınız
        statement.execute(sql);//Function oluşturan query'yi çalıştırdık

//        String sql2 = "SELECT selamlama('Ali')"; ==> Burası Callable Statement kullanmadan function çağırma işlemi

//        ResultSet resultSet = statement.executeQuery(sql2);//Function'ı paramtere ile çağırdık. Bize bir table döndü
//        resultSet.next();
//        System.out.println(resultSet.getObject(1));


        //3. Adım: Function'ı çağır
        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}");//? --> Parametrelendirme
                                                                              //select yerine call
        //4. Adım: Return için registerOutParameter() methodunu, parametreler için setInt(), setString() ... methodlarını kullanınız
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, "Ayşe");

        //5. Adım: execute() methodu ile callableStatement'ı çalıştır
        callableStatement.execute();

        //6. Adım: Sonucu görmek için callableStatement'tan data türünü çağır
        //callableStatement'ta data resultSet içine alınmaz. Direkt callableStatement'tan alınır.
        System.out.println(callableStatement.getString(1));


        //2. Örnek: İki sayıyı toplayan bir function yazınız ve Callable Statement ile çağırınız.

        //1. Adım: Function kodunu yazınız
        String sql2 = "CREATE OR REPLACE FUNCTION toplama(x int, y int) \n" +
                "RETURNS NUMERIC AS $$ BEGIN RETURN x+y; END; $$ LANGUAGE plpgsql;";

        //2. Adım: Function kodunu çalıştırınız
        statement.execute(sql2);

        //3. Adım: Function'ı çağır
        CallableStatement callableStatement2 = connection.prepareCall("{? = call toplama(?, ?)}");


        //4. Adım: Return için registerOutParameter() methodunu, parametreler için setInt(), setString() ... methodlarını kullanınız
        callableStatement2.registerOutParameter(1, Types.NUMERIC);
        callableStatement2.setInt(2, 6);
        callableStatement2.setInt(3, 4);

        //5. Adım: execute() methodu ile callableStatement'ı çalıştır
        callableStatement2.execute();

        //6. Adım: Sonucu görmek için callableStatement'tan data türünü çağır
        System.out.println(callableStatement2.getBigDecimal(1));


        connection.close();
        statement.close();
    }
}
