import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class AddressBookRESTAssureTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4000;
    }

    public Response getPersonList() {
        Response response = RestAssured.get("/AddressBook/list");
        return response;
    }

    @Test
    public void onCallingList_ReturnPersonList() {
        Response response = getPersonList();
        System.out.println("At First: " + response.asString());
        response.then().body("id", Matchers.hasItems(1, 2));
        response.then().body("State", Matchers.hasItems("TS"));
    }

    @Test
    public void givenPersonDetails_OnPost_ShouldReturnAddedPersonDetails() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"Name\":\"Jai\",\"Phone\":\"9876543210\",\"State\":\"AP\"}")
                .when()
                .post("/AddressBook/create");
        String responseString = response.asString();
        JsonObject jsonObject = new Gson().fromJson(responseString, JsonObject.class);
        int id = jsonObject.get("id").getAsInt();
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("Name", Matchers.is("Jai"));
        System.out.println(responseString);
    }

    @Test
    public void givenPersonDetails_OnUpdate_ShouldReturnUpdatedEmployee() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"Name\":\"Jai\",\"Phone\":\"9876543210\",\"State\":\"AP\"}")
                .when()
                .put("/AddressBook/update/3");
        String responseString = response.asString();
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("Name", Matchers.is("Jai"));
    }

    @Test
    public void givenPersonDetails_OnDelete_ShouldReturnSuccessStatus() {
        Response response = RestAssured.delete("/AddressBook/update/3");
        String responseString = response.asString();
        int statusCode = response.getStatusCode();
        MatcherAssert.assertThat(statusCode, CoreMatchers.is(200));
        response = getPersonList();
        System.out.println("AT END:" + response.asString());
        response.then().body("Name", Matchers.not("3"));
    }
}
