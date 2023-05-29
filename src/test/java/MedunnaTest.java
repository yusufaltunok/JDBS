import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;


import static org.junit.Assert.assertEquals;

public class MedunnaTest {
    /*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))

   When
     User sends the query to get the created room
     (Kullanıcı, oluşturulan odayı getirmek için sorgu gönderir)

   Then
     Assert that room is created properly
     (Odanın düzgün kaydedildiğini doğrular)

   And
     User closes the connection
  */

    @Test
    public void medunnaTest() throws SQLException {
        //User connects to the database
        JDBCUtils.connectToMedunnaDataBase();
        JDBCUtils.createStatement();

        // User sends the query to get the created room
        String sql = "SELECT * FROM room WHERE room_number = 326556421";
        ResultSet resultSet = JDBCUtils.executeQuery(sql);

        //Assert that room is created properly
        resultSet.next();//Burada tek satır çağırdığımız için tek bir next() methodu yeterlidir. Çoklu satırlarda loop kullanmak gerekir.
        assertEquals("123.00",  resultSet.getString("price"));
        assertEquals("Database Test İçin Oluşturuldu", resultSet.getString("description"));
        assertEquals("mark_twain",  resultSet.getString("created_by"));


    // benim oluşturduğum oda:
    //  51664	66	"TWIN"	false  999.00  "Test Odası"  "2023-05-18 20:09:27.455989"  "mark_twain"	"mark_twain"  "2023-05-18 20:09:27.455989"
        String sql1 = "SELECT  * FROM room WHERE room_number = 66";
        ResultSet resultSet1 = JDBCUtils.executeQuery(sql1);
        resultSet1.next();
        assertEquals("999.00",resultSet1.getString("price"));
        assertEquals("Test Odası",resultSet1.getString("description"));
        assertEquals("66",resultSet1.getString("room_number"));
        assertEquals("mark_twain",resultSet1.getString(8));



        //User closes the connection
        JDBCUtils.closeConnection();

    }

}
