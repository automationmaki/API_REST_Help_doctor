import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class ApiRegistration {
    @Test(description = "Sent all parametres")
    public void sentAllParametres() {
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", RandomStringUtils.randomAlphabetic(5) + "@mail.ru");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        System.out.println(request.body(requestBody.toString()));
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
    @Test(description = "Sent all parametres")
    public void sentAll() {
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", RandomStringUtils.randomAlphabetic(5) + "@mail.ru");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        System.out.println(request.body(requestBody.toString()));
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
    @Test(description = "Sent all parametres")
    public void sentAllParametres() {
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", RandomStringUtils.randomAlphabetic(5) + "@mail.ru");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        System.out.println(request.body(requestBody.toString()));
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
