import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class ListOfSpecialization {
    @BeforeTest
    public void setUp(){
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }
    @Test(description = "Sent all parametres")
    public void sentAllParametres() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get("http://helpdoctor.tmweb.ru/public/api/profile/specializations");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
    @Test(description = "Sent POST request")
    public void sentPostRequest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/profile/specializations");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 405);
        System.out.println("The status code recieved: " + statusCode);
    }
}
