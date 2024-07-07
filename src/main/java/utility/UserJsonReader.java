package utility;

import org.json.JSONObject;
import org.json.JSONTokener;
import utility.DTO.User;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class UserJsonReader {
    private final String fileName;

    public UserJsonReader(String fileName) {
        this.fileName = fileName;
    }

    public User readUserFromJsonFile() {
        try (Reader reader = new FileReader(fileName)) {
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
            return new User(jsonObject);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            return null; // Or throw an exception if appropriate
        }
    }
}
