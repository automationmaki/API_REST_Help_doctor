import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Recovery {
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
