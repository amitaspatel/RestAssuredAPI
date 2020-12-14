package base;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import reporting.TestLogger;
import utilities.TestUtils;

public class BaseAssertion {

	public static void verifyTrue(boolean flag){
		Assert.assertTrue(flag);
	}
	
	public static void verifyFalse(boolean flag){
		Assert.assertFalse(flag);
	}

	public static void verifyStatusCode(Response response, int status){
		Assert.assertEquals(TestUtils.getStatusCode(response), status);
	}
	
	public static void verifyStatusMessage(Response response, String status){
		Assert.assertEquals(TestUtils.getStatusMessage(response), status);
	}
	public static void verifyResponseBody(Response response, String responseBody){
		Assert.assertEquals(TestUtils.getStatusMessage(response), responseBody);
	}
	public static void verifyResponseBodyByJsonPath(Response response, String jsonPath, String expectedKeyValue){
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String actualKeyValue  = jsonPathEvaluator.get(jsonPath);

		// Let us print the city variable to see what we got
		TestLogger.log("Actual Key Value received from Response:  " + actualKeyValue);

		// Validate the response
		Assert.assertEquals(actualKeyValue, expectedKeyValue, "Correct value received in the Response");

		TestLogger.log("Response Assertion Successful");

	}
	public static void verifyResponseHeader(Response response, String headerKey, String headerValue){

		TestLogger.log(response.header(headerKey).toString());

		Assert.assertEquals(response.header(headerKey).matches(headerValue), true);

		TestLogger.log("Header "+ headerKey + " = " + headerValue +" available");
	}

}