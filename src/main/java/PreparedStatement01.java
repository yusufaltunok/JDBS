import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws SQLException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "YNse&6677");
        Statement statement = connection.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //PreparedStatement oluşturmak için:

        //1. Adım: PreparedStatement query'sini oluştur (parametrelendirme yapılacak yerlere ? koy )
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";// ? ==> parametrelendirme yapıyor
                                                                                       // değişik parametreler girebileceğimiz verilerin yerine ? koyuyoruz
        //2. Adım:PreparedStatement objesi oluştur
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);

        //3. Adım: preparedStatement objesi ile setInt gibi methodlarla parametrelendirmelerin (soru işaretleri) yerlerine koymak istediğimiz değerleri yerleştiriyoruz.
        preparedStatement.setInt(1, 9999);
        preparedStatement.setString(2, "IBM");

        //4. Adım: Query'yi çalıştır
        int guncellenenSatirSayisi = preparedStatement.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //Güncelleme sonrası yeni table'ı okuyalım:
        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet = statement.executeQuery(sql2);
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1) + "--" + resultSet.getObject(2) + "--" + resultSet.getObject(3));
        }


        //2. Örnek: Prepared statement kullanarak company adı casper olan number_of_employees değerini 10000 olarak güncelleyin.

        System.out.println("==============");
        preparedStatement.setInt(1, 10000);
        preparedStatement.setString(2, "CASPER");
        int updateSayisi = preparedStatement.executeUpdate();
        System.out.println("updateSayisi = " + updateSayisi);

       //Güncelleme sonrası yeni table'ı okuyalım:
        ResultSet resultSet2 = statement.executeQuery(sql2);
        while (resultSet2.next()) {
            System.out.println(resultSet2.getObject(1) + "--" + resultSet2.getObject(2) + "--" + resultSet2.getObject(3));
        }


        //3. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 12000 olarak güncelleyin.

        preparedStatement.setInt(1,12000);
        preparedStatement.setString(2,"GOOGLE");
        int updateSatirSayisi =preparedStatement.executeUpdate();
        System.out.println("updateSatirSayisi = " + updateSatirSayisi);

        ResultSet resultSet1 = statement.executeQuery(sql2);
        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"--" +resultSet1.getString(2)+"--" +resultSet1.getObject(3));
        }


        connection.close();
        statement.close();
    }


}
