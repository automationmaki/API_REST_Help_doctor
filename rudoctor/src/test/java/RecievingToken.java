import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class RecievingToken {
    @BeforeTest
    public void setUp(){
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }
    @Test(description = "Sent all parameters")
    public void sentAllParameters() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email","ivan@mail.ru");
        requestBody.put("password", "VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw==");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/auth/login");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
//        Assert.assertEquals(response.body().asString(), "{\"status\":\"Пользователь с таким почтовым адресом не зарегистрирован\"}");
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
    @Test(description = "The user is not exist")
    public void sentNotExistingUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", RandomStringUtils.randomAlphabetic(5) + "@mail.ru");
        requestBody.put("password", "VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw==");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/auth/login");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 403);
        Assert.assertEquals(response.body().asString(), "{\"status\":\"Пользователь с таким почтовым адресом не зарегистрирован\"}");
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
}
