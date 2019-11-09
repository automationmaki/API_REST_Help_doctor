import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class Recovery {
    @BeforeTest
    public void setUp(){
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }

    @Test(description = "Sent all parametres")
    public void sentAllParametres() {
        RestAssured.baseURI ="http://helpdoctor.tmweb.ru/public/api/recovery";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "test@test.ru");
        request.body(requestBody.toJSONString());
        Response response = request.post();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
