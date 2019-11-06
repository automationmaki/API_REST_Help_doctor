import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ListOfSubjectRussianFederation {
    @Test(description = "Sent all parametres")
    public void sentAllParametres() {
        JSONObject requestBody = new JSONObject();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get("http://helpdoctor.tmweb.ru/public/api/profile/regions");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
    @Test(description = "Sent POST request")
    public void sentPostRequest() {
        JSONObject requestBody = new JSONObject();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/profile/regions");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 405);
        System.out.println("The status code recieved: " + statusCode);
    }

}

