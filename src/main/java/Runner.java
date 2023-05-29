
public class Runner {

    public static void main(String[] args) {

        //DatBase'e bağlan
        JDBCUtils.connectToDataBase();// JDBCUtils class'ındaki connectToDataBase() metodu ile dataBase'e bağlandık

        //Statement oluştur
        JDBCUtils.createStatement(); // JDBCUtils class'ındaki createStatement() metodu ile

        //Query çalıştır
        String sql = "CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR (20), worker_salary INT);";
        JDBCUtils.execute(sql);

//        JDBCUtils.executeQuery(sql);
//
//        JDBCUtils.executeUpdate(sql);
//
//        JDBCUtils.connectionClose();
//
//        JDBCUtils.statementClose();

    }
}