package pageobjects;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import utility.ConfigReader;
import utility.DTO.Post;
import utility.HttpUtility;

import java.util.ArrayList;
import java.util.List;

public class GetRequestPage {
    private final String baseUrl = ConfigReader.getBaseUrl();

    public HttpResponse<JsonNode> getAllPosts() {
        try {
            String url = String.format("%s/posts", baseUrl);
            return HttpUtility.sendGetRequest(url);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public List<Post> getAllPostsAsList() {
        try {
            HttpResponse<JsonNode> response = HttpUtility.sendGetRequest(baseUrl + "/posts");
            JsonNode responseBody = response.getBody();

            List<Post> posts = new ArrayList<>();
            for (int i = 0; i < responseBody.getArray().length(); i++) {
                Post post = new Post();
                post.setId(responseBody.getArray().getJSONObject(i).getInt("id"));
                post.setUserId(responseBody.getArray().getJSONObject(i).getInt("userId"));
                post.setTitle(responseBody.getArray().getJSONObject(i).getString("title"));
                post.setBody(responseBody.getArray().getJSONObject(i).getString("body"));
                posts.add(post);
            }
            return posts;
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<JsonNode> getPostByID(int id) {
        try {
            String url = String.format("%s/posts/%d", baseUrl, id);
            return HttpUtility.sendGetRequest(url);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<JsonNode> getUsers() {
        try {
            String url = String.format("%s/users", baseUrl);
            return HttpUtility.sendGetRequest(url);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<JsonNode> getUserById(int id) {
        try {
            String url = String.format("%s/users/%d", baseUrl, id);
            return HttpUtility.sendGetRequest(url);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
