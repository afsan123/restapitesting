package utility;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class HttpUtility {
    public static HttpResponse<JsonNode> sendGetRequest(String url) throws UnirestException {
        return Unirest.get(url).asJson();
    }

    public static HttpResponse<JsonNode> sendPostRequest(String url, JSONObject requestBody) throws UnirestException {
        return Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .asJson();
    }
}
