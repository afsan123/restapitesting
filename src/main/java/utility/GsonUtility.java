package utility;

import com.google.gson.Gson;
import com.mashape.unirest.http.JsonNode;

public class GsonUtility {
    private static final Gson gson = new Gson();

    public static <T> T mapJsonResponseToObject(JsonNode responseBody, Class<T> clazz) {
        return gson.fromJson(responseBody.toString(), clazz);
    }
}
