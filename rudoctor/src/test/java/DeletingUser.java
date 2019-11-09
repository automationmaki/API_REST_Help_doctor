import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class DeletingUser {
    @BeforeTest
    public void setUp(){
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }
    @Test(description = "Sent all parametres")
    public void sentAllParametres() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", RandomStringUtils.randomAlphabetic(5) + "@mail.ru");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.delete("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(response.body().asString(), "{\"status\":\"OK\"}");
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
}
