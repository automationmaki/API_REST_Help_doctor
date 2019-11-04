import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class ApiRegistration {
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
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(response.body().asString(), "{\"status\":\"OK\"}");
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
    @Test(description = "Content-Type xml")
    public void sentInvalidHeader() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", RandomStringUtils.randomAlphabetic(5) + "@mail.ru");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/xml");
        request.body(requestBody.toString());
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(response.body().asString(), "{\"status\":\"Content-Type not json\"}");
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
    @Test(description = "Parameter mail isn't sent")
    public void notSentParameterMail() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("", RandomStringUtils.randomAlphabetic(5) + "@mail.ru");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(response.body().asString(), "{\"status\":\"json not valid. 'email' field required and STR type\"}");
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
    @Test(description = "The user is already exist")
    public void userIsAlreadyExist() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "33333@mail.ru");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 403);
        Assert.assertEquals(response.body().asString(), "{\"status\":\"Пользователь с таким почтовым адресом уже зарегистрирован\"}");
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }
    @Test(description = "Email isn't valid")
    public void emailIsNotValid() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "33333mail.ru");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("http://helpdoctor.tmweb.ru/public/api/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 500);
        Assert.assertEquals(response.body().asString(), "{\"status\":\"Ошибка при отправке письма сервисом или указан несуществующий e-mail\"}");
        System.out.println("The status code recieved: " + statusCode);
        System.out.println("Response body: " + response.body().asString());
    }





}
