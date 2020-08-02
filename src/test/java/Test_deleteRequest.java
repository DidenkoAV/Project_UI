import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class Test_deleteRequest {
    final static String host = "https://reqres.in/api/";
    @Test(priority = 19,description = "DELETE request for test API")
    public void CheckResponse() {
        JSONObject deleteBody = new JSONObject();
        deleteBody.put("name", "morpheus");
        deleteBody.put("job", "leader");

        ValidatableResponse response = RestAssured.given()
                .baseUri(host + "users/2")
                .log()
                .all()
                .when()
                .body(deleteBody.toString())
                .delete()
                .then()
                .log()
                .body()
                .statusCode(204);
        Allure.addAttachment("Request body", String.valueOf(deleteBody.toString()));
        Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
        Allure.addAttachment("Response code: ", String.valueOf(response.extract().statusCode()));
    }
}
