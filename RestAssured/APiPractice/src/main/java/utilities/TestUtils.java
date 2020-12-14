package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import reporting.TestLogger;

public class TestUtils {

	
	public static String getResponseBodyAsString(Response response){
		TestLogger.log("Converting Response to String");
		String strResponse = response.getBody().asString();
		TestLogger.log("String Response: "+ strResponse);
		return strResponse;
	}
	
	public static JsonPath getJSONResponseFromString(String response){
		TestLogger.log("Parsing String Response to JSON");
		JsonPath jsonResponse = new JsonPath(response);
		TestLogger.log("JSON Response: "+jsonResponse);
		return jsonResponse;
	}
	

	public static XmlPath getXMLResponseFromString(String response){
		TestLogger.log("Parsing String Response to XML");
		XmlPath xmlResponse = new XmlPath(response);
		TestLogger.log("XML Response: "+xmlResponse);
		return xmlResponse;
	}
	
	public static int getStatusCode(Response response){
		TestLogger.log("Getting Status Code");
		int statusCode = response.getStatusCode();
		TestLogger.log("Status Code: "+statusCode);
		return statusCode;
	}
	
	public static String getStatusMessage(Response response){
		TestLogger.log("Getting Response Message");
		String responseMessage = response.getStatusLine();
		TestLogger.log("Response Message: "+responseMessage);
		return responseMessage;
	}

	public static String getResponseBody(Response response){
		ResponseBody body = response.getBody();

		// By using the ResponseBody.asString() method, we can convert the  body
		// into the string representation.
		String responseBody = body.asString();
		TestLogger.log("Response Body is: " + responseBody);
		return new String();

	}
	
	
	
	
	
	
	
}
