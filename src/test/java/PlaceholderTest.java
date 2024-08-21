import com.org.placeholder.dto.PostBody;
import com.org.placeholder.property.PropertyReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceholderTest
{
    private static final Logger logger = LoggerFactory.getLogger(PlaceholderTest.class);
    private static String BASE_URL;

    @BeforeAll
    public static void setUp()
    {
        Properties properties = PropertyReader.loadProperties("src/main/resources/application.properties");
        BASE_URL = properties.getProperty("base.url");
    }

    @Test
    public void createPostTest()
    {
        PostBody newPost = new PostBody();
        newPost.setUserId(1);
        newPost.setTitle("foo");
        newPost.setBody("bar");

        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(newPost)
                .post(BASE_URL);

        assertEquals(HTTP_CREATED, response.statusCode());
        PostBody responseBody = response.as(PostBody.class);
        assertEquals(newPost.getTitle(), responseBody.getTitle());
        assertEquals(newPost.getBody(), responseBody.getBody());
        assertEquals(newPost.getUserId(), responseBody.getUserId());
    }

    @Test
    public void getPostTest() {
        int postId = 1;

        Response response = given()
                .filter(new AllureRestAssured())
                .get(BASE_URL + "/" + postId);

        assertEquals(HTTP_OK, response.getStatusCode());
        PostBody post = response.as(PostBody.class);
        assertEquals(postId, post.getId());
    }

    @Test
    public void updatePostTest() {
        int postId = 1;
        PostBody updatedPost = new PostBody();
        updatedPost.setUserId(1);
        updatedPost.setTitle("Updated Title");
        updatedPost.setBody("Updated Body");

        Response response = given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .body(updatedPost)
                .put(BASE_URL + "/" + postId);

        assertEquals(HTTP_OK, response.getStatusCode());
        PostBody post = response.as(PostBody.class);
        assertEquals(updatedPost.getTitle(), post.getTitle());
        assertEquals(updatedPost.getBody(), post.getBody());
    }

    @Test
    public void deletePostTest() {
        int postId = 1;

        Response response = given()
                .filter(new AllureRestAssured())
                .delete(BASE_URL + "/" + postId);

        assertEquals(HTTP_OK, response.getStatusCode());
    }

    @Test
    public void calculateTopWordsInPosts() {
        Response response = given()
                .filter(new AllureRestAssured())
                .get(BASE_URL);

        assertEquals(HTTP_OK, response.getStatusCode(), "Failed to fetch posts. HTTP Status Code: " + response.getStatusCode());

        PostBody[] posts = response.getBody().as(PostBody[].class);

        Map<String, Integer> wordCountMap = Arrays.stream(posts)
                .flatMap(post -> Arrays.stream(post.getBody().toLowerCase().split("\\W+")))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toMap(Function.identity(), word -> 1, Integer::sum));

        AtomicInteger counter = new AtomicInteger(1);
        wordCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .forEachOrdered(entry -> logger.info("{}. {} - {}", counter.getAndIncrement(), entry.getKey(), entry.getValue()));
    }
}
