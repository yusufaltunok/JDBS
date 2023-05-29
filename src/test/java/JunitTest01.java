import org.junit.Test;

import static org.junit.Assert.*;

public class JunitTest01 {

    @Test // Test Anotation --> main metot kullanmıyoruz void test metodu olşturup başına @Test yazıyoruz
    public void test01() {

        assertEquals(5,"hello".length()); //Bu methodunda parantez içindeki paramtereleri eşitse PASS olur değilse FAIL olur

        assertTrue("hello".contains("e"));//Bu methodunda parantez içi 'true' ise PASS olur değilse FAIL olur

        assertFalse("google.com".contains("x"));// negative test  --> parantez içi durum false donerse test geçer

        assertEquals(65,'A');

    }


}
