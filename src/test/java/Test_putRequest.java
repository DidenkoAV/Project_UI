import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class Test_putRequest {
    final static String host = "https://reqres.in/api/";

    @Test(priority = 17,description = "PUT request for test API,check status code response")
    public void checkStatusResponse(){
        JSONObject putBody = new JSONObject();
        putBody.put("name", "morpheus");
        putBody.put("job", "zion resident");
        ValidatableResponse response = RestAssured.given()
                .baseUri(host+"users/2")
                .log()
                .all()
                .when()
                .body(putBody.toString())
                .put()
                .then()
                .log()
                .body()
                .statusCode(200);
        Allure.addAttachment("Request body", String.valueOf(putBody.toString()));
        Allure.addAttachment("Response code: ", String.valueOf(response.extract().statusCode()));
    }
    @Test(priority = 18,description = "PUT request for test API,check body response")
    public void checkBodyResponse(){
        JSONObject putBody = new JSONObject();
        putBody.put("name", "morpheus");
        putBody.put("job", "zion resident");
        ValidatableResponse response = RestAssured.given()
                .baseUri(host+"users/2")
                .log()
                .all()
                .when()
                .body(putBody.toString())
                .put()
                .then()
                .log()
                .body()
                .statusCode(200);
        Allure.addAttachment("Request body", String.valueOf(putBody.toString()));
        Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
    }
}
