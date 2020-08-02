import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_postRequest {
    final static String host = "https://reqres.in/api/";

    @Test(priority = 15,description = "POST request for test API,check status code response")
    public void checkStatusResponse(){
        JSONObject postBody = new JSONObject();
        postBody.put("name", "morpheus");
        postBody.put("job", "leader");

        ValidatableResponse response = RestAssured.given()
                .baseUri(host+"users/")
                .log()
                .all()
                .when()
                .body(postBody.toString())
                .post()
                .then()
                .log()
                .body()
                .statusCode(201);
        Assert.assertEquals(response.toString().contains("id"),true);
        Allure.addAttachment("Request body", String.valueOf(postBody.toString()));
        Allure.addAttachment("Response code: ", String.valueOf(response.extract().statusCode()));

    }
    @Test(priority = 16,description = "POST Request for test API,check response body")
    public void checkBodyResponse(){
        JSONObject postBody = new JSONObject();
        postBody.put("name", "morpheus");
        postBody.put("job", "leader");

        ValidatableResponse response = RestAssured.given()
                .baseUri(host+"users/")
                .log()
                .all()
                .when()
                .body(postBody.toString())
                .post()
                .then()
                .log()
                .body()
                .statusCode(201);
        Assert.assertEquals(response.toString().contains("id"),true);
        Allure.addAttachment("Request body", String.valueOf(postBody.toString()));
        Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
    }
}
