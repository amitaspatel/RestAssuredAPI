package payload;

import reporting.TestLogger;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Converter {

    public static String generatePayLoadString() {
        TestLogger.log("Generating Payload...stand by");
        String filePath = "/Users/navid/IdeaProjects/RestAPI/APiPractice/src/main/java/payload/PayLoadSample.json";
        //String filePath = System.getProperty("user.dir")+"src/main/java/payload"+filename;
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            return null;
        }
    }

    public static String payload1() {

        String payload = "{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";

        return payload;
    }
}

