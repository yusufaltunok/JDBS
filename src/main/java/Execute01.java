import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// pom = page object model
// dependency nereden = mavenrepository den alınır(normalde şirketten alınır çümkü
// site public tir virüs falan bulaştırabilir.)
// Çalıştığınız şirket size verir.
// mavenrepository sitesinden her türlü dependency'ler alınabilir
// database çok sıkıntılı tehlikeli bir yer oldugu iin şifresi verilir ama çok kısıtlı
// bir yetki ile verilir şirket tarafından

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Adım: Driver'a kaydol ==> JDBC 4 sonrası gerek yoktur.
       // Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e bağlan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","YNse&6677");

        //3. Adım: Statement oluştur
        Statement statement = connection.createStatement();

        //4. Adım: Query çalıştır
        /*
            execute() methodu DDL(create, drop, alter table) ve DQL(select) ile kullanılır
        1) Eğer execute() methodu DDL (create, drop, alter table) ile kullanılırsa her zaman 'false' döner.Çünkü DDL ile data çağırılmaz
        2) Eğer execute() methodu DQL (select) ile kullanılırsa data dönerse 'true', data dönmezse 'false' verir.
         */
        // execute() methodu parametre olarak girilen String SQL komutunu bağlı olduğu database üzerinde uygular.


        // 1. Örnek: "workers" adında bir tale oluşturup worker_id, worker_name, worker_salary sütunlarını ekleyin
        //CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR (20), worker_salary INT);
        String sql1 = "CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR (20), worker_salary INT)";// tablo oluşturduk
        boolean r1 = statement.execute(sql1); // yukarıdaki String sql1 i method parantezi içine yazmak istemediğimiz için sql1 i oluşturduk
        System.out.println("r1 = " + r1); // false

        //2. Örnek: workers table'ına worker_address sütunu ekleyiniz
        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(100)"; // yukarıda oluşturduğumuz tabloya sütun ekledik
        boolean r2 = statement.execute(sql2);
        System.out.println("r2 = " + r2);

        //3.Örnek: workers table'ını silin.
        String sql3 = "DROP TABLE workers";
        boolean r3 = statement.execute(sql3);
        System.out.println("r3 = " + r3);

        //5. Adım: Bağlantıyı kapat
        connection.close();
        statement.close();




    }
}
/*
BIRINCI ADIM: Driver’a kaydol.
Class.forName("org.postgresql.Driver"); //Bu kisim intellij’de external libraries altinda org kismindan Driver yolunu gosteriyor. JDBC sonrasi buna artik gerek kalmiyor
forName kismi kizarinca uzerine geldik bize bir exception firlatmamiz istediginden uyarinin en ust kisminda add exception’a tiklayinca kizariklik gitti ve main method yanina exception firlatilmis oldu.
IKINCI ADIM: Database’e baglan. DriverManager class’ina giderek getConnection() ile baglaniriz. Biz bunu 3 parametre ile url, user ve password ile calisturacagiz. getConnection kismi kizaracak ve biz buraya gelip exeption atilmasini saglariz.
DriverManager.getConnection("", "",""); Biz parantez icine “” atinca sirasiyla… user ve password yazilarini gorduk yani bu kisimlara ornegin user, passwordumuzu girecegiz. Biz parametreleri koymazsak, bu kisim calismaz.
getConnection() static’tir. Cunku class ismi ile cagiriyoruz.
Connection connection = DriverManager.getConnection(jdbc:postgresql://localhost:5432/postgres", "postgres","1234") //Bu kodumuzu Connection connection’a assign ettik.
("jdbc:postgresql://localhost:5432/postgres",
"postgres","1234"); bu kismi DriverManager.getConnection parantezi icine aldik jdbc:postgresql:// kismi sabittir. Geri kalan kisim pgAdmin’de PostGreSQL 15 kisminda properties ve sonrasi connection kismindan check edip yazacagimiz bilgiler. Localhost kismi connection kisminda Host name/ address’de yazili. 5432 ise connection’da Port bolumunde var. birinci postgres Maintaince database ve ikinci postgres username kisminda yazili. Password olarak pgAdmin’I acarken kullandigimiz password’u girecegiz. Yani parantez icinde en son kisma gelecek.
Postgres bilgisini pgAdmin PostGreSQL 15 kismina gelip sagi tiklayip properties kismina gelince Acilan pencerede Connection’a geldik ve sirasiyla localhost, 5432 postgres ve username postgres goruldu. Username’e postgresi kopyalayip yapistirdik. Son kisimda bizim pgAdmin’deki paswordumuzu yaziyoruz.
UCUNCU ADIM:
Statement olustur. Statement bizim icin b rara adimdir.
Statement statement = connection.createStatement(); //Burada sadece bir statement olusturuyoruz. Bir islem yapmiyoruz. Islemi dorduncu adimda yapacagiz.
DORDUNCU ADIM: Query calistir.
Burada islemlerimizi yapiyoruz.
statement.execute(“”);
pgAdmin’e giderek databases altinda postgres uzerine gelip yeni bir query actik.
--1.Ornek: Workers adinda bir table olustur ve "worker_id, worker_name, worker_salary"
--sutunlarini ekleyin
CREATE TABLE workers (
worker_id VARCHAR(20),
worker_name VARCHAR(20),
worker_salary INT
)
Biz bu kodu pgAdmin yerine intellij’de yapacagiz. End to end test olarak bilinen test, UI, API ve database testlerini yapmamiz halinde soz konusu olur. Bu testleri intellij’de yapacagiz.

 */