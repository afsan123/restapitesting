package pageobjects;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import utility.ConfigReader;
import utility.HttpUtility;

public class PostRequestPage {
    private final String baseUrl = ConfigReader.getBaseUrl();

    public HttpResponse<JsonNode> createPost(int userId, String title, String body) {
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("userId", userId);
            requestBody.put("title", title);
            requestBody.put("body", body);

            return HttpUtility.sendPostRequest(baseUrl + "/posts", requestBody);
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }
}
