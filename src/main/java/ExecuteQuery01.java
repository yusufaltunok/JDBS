import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "YNse&6677");
        Statement statement = connection.createStatement();
        // Connection ve Statement olmadan tabloyu çağıramayız.

        System.out.println("\n======= 1. Örnek: ==============\n");
        //1. Örnek:  region id'si 1 olan "country_name" değerlerini çağırın.
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean r1 = statement.execute(sql1); // execute() metodu boolean döndürür
        System.out.println("r1 = " + r1);// true --> DQL ile data çağırdığımız için true verir

        //Datayı çağırıp okumak için "executeQuery" methodunu kullanmalıyız. "execute()" methodu sadece true veya false döner.

        ResultSet resultSet = statement.executeQuery(sql1); // sql1 ile gelen datayı resultSet içine yerleştiriyoruz.Çünkü ResultSet döndürüyor
        // ResultSet bir pointer ile gelir. Önce başlığı gösterir.Sonra next() metoduyla dataların olduğu satırlara geçilir.
        // datayı okuyabilmek için executeQuery() metodunu kullanmalıyız.

        while (resultSet.next()){ // next() metodu boolean dondurur. satır olduğu surece true verir ve donmeye devam eder

            System.out.println(resultSet.getString("country_name"));
        }


        System.out.println("\n======= 2. Örnek: ==============\n");
        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id > 2";
        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()){//ResultSet son satıra gelip false verdikten sonra kapanır. Kapalı ResultSet üzerinde işlem yapılırsa exception atar.
            System.out.println(resultSet2.getString(1)+"-- "+resultSet2.getString(2));
        }


        System.out.println("\n======= 3. Örnek: ==============\n");
        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 ="SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = statement.executeQuery(sql3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getInt(1)+"--"+resultSet3.getString(2)+"--"+resultSet3.getInt(3));
            //System.out.println(resultSet3.getObject("company_id")+"--"+resultSet3.getObject("company")+"--"+resultSet3.getObject("number_of_employees"));
        }

        connection.close();
        statement.close();


    }
}
