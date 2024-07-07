package utility;

import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {
    public static JsonNode findUserById(JSONArray jsonArray, int userId) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userObject = jsonArray.getJSONObject(i);
            if (userObject.getInt("id") == userId) {
                return new JsonNode(userObject.toString());
            }
        }
        return null; // Return null if user with the specified ID is not found
    }
}
