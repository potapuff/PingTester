package tss.sumdu.test.utils;

public class TestHelper {

    public static String jsonGenerator(String name, String code, String message){
        return String.format("{ " +
                "url: '%s'," +
                "code: %s," +
                "message: %s" +
                "}", name, code, message);
    }
}
