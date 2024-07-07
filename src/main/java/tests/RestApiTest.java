package tests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.GetRequestPage;
import pageobjects.PostRequestPage;
import utility.*;
import utility.UserJsonReader;
import utility.DTO.Post;
import utility.DTO.User;

import java.util.List;

import static utility.HttpStatus.*;

public class RestApiTest {

    @Test
    public void testCaseOne() {
        GetRequestPage getRequestPage = new GetRequestPage();

        // Use UserJsonReader to read test user data from a JSON file
        User expectedUser = new UserJsonReader("testUserData.json").readUserFromJsonFile();

        HttpResponse<JsonNode> response = getRequestPage.getAllPosts();


        int statusCode = response.getStatus();
        Assert.assertEquals(statusCode, OK.getCode(), "Status code isn't 200");

        List<Post> posts = getRequestPage.getAllPostsAsList();
        List<Post> sortedPosts = ListUtil.sortById(posts);

        Assert.assertEquals(posts, sortedPosts, "Posts are not sorted in ascending order by id");

        int postIdToRetrieve  = 99;
        response = getRequestPage.getPostByID(postIdToRetrieve);
        Assert.assertEquals(response.getStatus(), OK.getCode(), "Status code should be 200");

        Post post = GsonUtility.mapJsonResponseToObject(response.getBody(), Post.class);

        Assert.assertEquals(post.getUserId(), 10, "UserID should be 10");
        Assert.assertEquals(post.getId(), postIdToRetrieve, "UserID should be 10");
        Assert.assertFalse(post.getTitle().isEmpty(), "Title should not be empty");
        Assert.assertFalse(post.getBody().isEmpty(), "Body should not be empty");

        int errorId = 150;
        response = getRequestPage.getPostByID(errorId);
        Assert.assertEquals(response.getStatus(), NOT_FOUND.getCode(), "Response body isn't empty.");

        PostRequestPage postRequestPage = new PostRequestPage();

        int userId = 1;
        String title = RandomStringGenerator.generateRandomString(8);
        String body = RandomStringGenerator.generateRandomString(30);

        response = postRequestPage.createPost(userId, title, body);

        Assert.assertEquals(response.getStatus(), CREATED.getCode(), "Status code should be 201");

        Post createdPost = GsonUtility.mapJsonResponseToObject(response.getBody(), Post.class);

        Assert.assertEquals(createdPost.getUserId(), userId, "UserID should be 10");
        Assert.assertEquals(createdPost.getTitle(), title, "UserID should be 10");
        Assert.assertEquals(createdPost.getBody(), body, "Body should match");
        Assert.assertTrue(createdPost.getId() > 0, "ID should be present");

        response = getRequestPage.getUsers();
        statusCode = response.getStatus();
        Assert.assertEquals(statusCode, OK.getCode(), "Status code isn't 200");

        // Verify that response body is a JSON array
        JsonNode responseBody = response.getBody();
        Assert.assertTrue(responseBody.isArray(), "Response body should be an array");

        // Find the user with id=5 in the response
        JsonNode userWithId5 = JsonUtils.findUserById(responseBody.getArray(), 5);

        assert userWithId5 != null;
        User user = JsonToUserDtoConverter.convertJsonToUser(userWithId5.getObject());

        Assert.assertNotNull(user, "User should not be null");

        // Assert specific user details for user with ID 5
        Assert.assertTrue(expectedUser.equals(user), "User data should match");

        HttpResponse<JsonNode> new_response = getRequestPage.getUserById(userId);
        Assert.assertEquals(new_response.getStatus(), OK.getCode(), "Status code should be 200");

        JsonNode getUserById = new_response.getBody();

        // Verify user information for user with ID 5
        Assert.assertEquals(getUserById.getObject().toString(), userWithId5.toString(), "User data should match");
    }
}
