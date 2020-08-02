import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.hamcrest.Matchers.*;

@Listeners({TestAllureListener.class})
public class Test_getRequest {
    final static String host = "https://reqres.in/api/";

    @Test(priority = 8,description = "GET request for test API,check code response ")
    public void checkStatusResponse() throws IOException {
        ValidatableResponse response = RestAssured.given()
                .queryParam("page", "2")
                .log()
                .all()
                .when()
                .get(host + "users/")
                .then()
                .log()
                .body()
                .statusCode(200);
        Assert.assertEquals(response.toString().contains("data"),true); 
        Allure.addAttachment("Response code: ", String.valueOf(response.extract().statusCode()));
    }
      @Test(priority = 9,description = "GET request for test API,check body response ")
      public void  checkBodyResponse() {
          ValidatableResponse response = RestAssured.given()
                  .queryParam("page", "2")
                  .log()
                  .all()
                  .when()
                  .get(host + "users/")
                  .then()
                  .log()
                  .body()
                  .statusCode(200);
          Assert.assertEquals(response.toString().contains("data"),true);
          Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
      }
      @Test(priority = 10,description = "GET request for test API,check id items in response")
      public void  checkBodyIdItemsIdResponse() {
        ValidatableResponse response = RestAssured.given()
                .queryParam("page", "2")
                .log()
                .all()
                .when()
                .get(host + "users/")
                .then()
                .assertThat()
                .log()
                .status()
                .body("data.id", hasItems(7,8,9,10,11,12))
                .statusCode(200);
          Allure.addAttachment("Expected result: ", String.valueOf("7,8,9,10,11,12"));
          Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
    }
    @Test(priority = 11,description = "GET request for test API,check first name items in response")
    public void  checkBodyFirstNameItemsResponse() {
        ValidatableResponse response = RestAssured.given()
                .queryParam("page", "2")
                .log()
                .all()
                .when()
                .get(host + "users/")
                .then()
                .assertThat()
                .log()
                .status()
                .body("data.first_name", hasItems("Michael","Lindsay","Tobias","Byron","George","Rachel"))
                .statusCode(200);
        Allure.addAttachment("Expected result: ", String.valueOf("Michael,Lindsay,Tobias,Byron,George,Rachel"));
        Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
    }
    @Test(priority = 12,description = "GET request for test API,check last name items in response")
    public void  checkBodyLastNameItemsResponse() {
        ValidatableResponse response = RestAssured.given()
                .queryParam("page", "2")
                .log()
                .all()
                .when()
                .get(host + "users/")
                .then()
                .assertThat()
                .log()
                .status()
                .body("data.last_name", hasItems("Lawson","Ferguson","Funke","Fields","Edwards","Howell"))
                .statusCode(200);
        Allure.addAttachment("Expected result: ", String.valueOf("Lawson,Ferguson,Funke,Fields,Edwards,Howell"));
        Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
    }

    @Test(priority = 13,description = "GET request for test API,check email items in response")
    public void  checkBodyEmailItemsResponse() {
        ValidatableResponse response = RestAssured.given()
                .queryParam("page", "2")
                .log()
                .all()
                .when()
                .get(host + "users/")
                .then()
                .assertThat()
                .log()
                .status()
                .body("data.email", hasItems("michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"))
                .statusCode(200);
        Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
    }
    @Test(priority = 14,description = "GET request for test API,check avatar items in response")
    public void  checkBodyAvatarItemsResponse() {
        ValidatableResponse response = RestAssured.given()
                .queryParam("page", "2")
                .log()
                .all()
                .when()
                .get(host + "users/")
                .then()
                .assertThat()
                .log()
                .status()
                .body("data.avatar", hasItems("https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg",
                         "https://s3.amazonaws.com/uifaces/faces/twitter/araa3185/128.jpg",
                         "https://s3.amazonaws.com/uifaces/faces/twitter/vivekprvr/128.jpg",
                         "https://s3.amazonaws.com/uifaces/faces/twitter/russoedu/128.jpg",
                         "https://s3.amazonaws.com/uifaces/faces/twitter/mrmoiree/128.jpg",
                         "https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg"))
                .statusCode(200);
        Allure.addAttachment("Response body: ", String.valueOf(response.extract().body().asString()));
    }
}

