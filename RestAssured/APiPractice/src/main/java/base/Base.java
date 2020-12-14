package base;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import reporting.NavCore;
import reporting.TestLogger;
import utilities.TestUtils;

import static io.restassured.RestAssured.*;

public class Base extends NavCore {

    @Test
    public void get_Pokemon_API_Call_As_A_String() {

        String response = given().when().get("pokemon/charizard").then().log().all().toString();

        System.out.println(response);

    }

    @Test
    public void get_Pokemon_API_Call_As_A_Response(){

        Response response = given().when().get("pokemon/dialga").then().log().all().extract().response();

        TestLogger.log(response.getBody().prettyPrint());

    }

    @Test
    public void get_Pokemon_API_As_A_Validatable_Response(){

        ValidatableResponse response = given().when().get("pokemon/ditto").then().log().all();

        TestLogger.log(response.extract().body().toString());
    }

    @Test
    public void validate_Pokemon_API_Status_Code_As_A_Validatable_Response(){

        ValidatableResponse response = given().when().get("pokemon/palkia").then();

       response.statusCode(200);

    }

    @Test
    public void validate_Pokemon_API_Status_Code_As_A_Response(){
        Response response = given().when().get("pokemon/charmander").then().extract().response();

        int statusCode= response.getStatusCode();

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void validate_Pokemon_API_Status_Code_As_A_String(){
        String response = given().when().get("pokemon/squirtle").then().assertThat().statusCode(200).toString();
    }

}
