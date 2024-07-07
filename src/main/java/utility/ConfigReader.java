package utility;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    private static final String CONFIG_FILE_PATH = "config.json";

    public static String getBaseUrl() {
        try (FileReader fileReader = new FileReader(CONFIG_FILE_PATH)) {
            JsonObject jsonObject = JsonParser.parseReader(fileReader).getAsJsonObject();
            return jsonObject.get("BASE_URL").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
