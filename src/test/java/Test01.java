public class Test01 {
    public static void main(String[] args) {

        String str = "Hello";

        if(str.contains("x")){
            System.out.println("PASS");
        }else {
            System.out.println("FAIL");
        }

        //Otomasyon testi yapabilmemiz için test framework'üne (jUnit, testNG, Cucumber ...) ihtiyacımız vardır.

    }
}