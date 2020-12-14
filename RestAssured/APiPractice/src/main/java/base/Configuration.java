package base;

import POJOs.DataItem;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.Converter;
import reporting.NavCore;
import utilities.Path;
import utilities.TestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Configuration {

    public static String LIST_END_POINT = "/api/users?page=2";
    public static String SINGLE_USER_END_POINT = "/api/users/2";
    public static String POST_SINGLE_USER = "/api/users";

    @Test
    public void get_A_List_Of_Users_From_List_And_Print_As_String_With_POJO() {
        POJOs.Response as = given().when().get("https://reqres.in/api/users%22").then()
                .extract()
                .as(POJOs.Response.class);

        System.err.println(as.getData().get(1).getName());

    }

    @Test
    public void get_Status_Code_Of_List_Of_Users() {
        Response response = given().when().get(Path.getEndPoint(LIST_END_POINT)).then().extract().response();
        //put into a response variable

        System.out.println(TestUtils.getStatusCode(response));
        //call on Test Utils class to log responses



        int code = response.statusCode();
        //put response status code into a int

        Assert.assertEquals(code, 200);
        //assert and verify

    }

    @Test
    public void verify_Body_Contains_Answer() {
        given().when().get(Path.getEndPoint(LIST_END_POINT)).then().assertThat().statusCode(200).body("data.id[0]", equalTo(7));

    }

    @Test
    public void extract_A_Response() {
        ValidatableResponse response = given().when().get(Path.getEndPoint(LIST_END_POINT)).then();

        String answer = response.extract().path("data.email[0]").toString();
        String answer2 = response.extract().path("page").toString();
        //extract gets you specific parts pf the answer

        System.out.println(answer);
        System.out.println(answer2);

    }

    @Test
    public void create_A_Post_For_Users_Using_HashMap(){
        HashMap<String, String> user = new HashMap<>();

        user.put("name", "Navid");
        user.put("job", "Leader");

        given().contentType("application/json").body(user).when().post("https://reqres.in/api/users").then().statusCode(201).log().all();

    }

    @Test
    public void create_A_Post_and_Extract_Path() {
        HashMap<String, String> roles = new HashMap<>();

        roles.put("name", "Navid");
        roles.put("job", "Captain");

        ValidatableResponse response =  given().contentType("application/json").body(roles).when().post("https://reqres.in/api/users").then();

        String path = response.extract().path("id").toString();

        System.out.println(path);

    }

    @Test
    public void create_A_Post_Using_Payload(){

        given().contentType("application/json").body(Converter.payload1()).when().post("https://reqres.in/api/users").then().log().all().assertThat().statusCode(201);

    }

    @Test
    public void test1(){
        Response results = given().contentType("application/json").when().get("https://reqres.in/api/users?page=2").then().assertThat().statusCode(200).log().all().extract().response();
        //results.getBody().toString();

    }

    @Test
    public void setup(){
        given().contentType(ContentType.JSON).when().get("http://synapzecx.bondqa.com:8081/api/resources").jsonPath().get("token");

    }




}

