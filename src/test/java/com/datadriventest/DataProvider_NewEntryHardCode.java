package com.datadriventest;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataProvider_NewEntryHardCode {
	
	@Test
	public void postNewEntry() {
		
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification httpRequest = RestAssured.given();
		
		
		// Here we wreate data which we can send along with the POST request
		JSONObject requestParameter = new JSONObject();
		
		requestParameter.put("name", "Faizi");
		requestParameter.put("job", "Engineer");
		
		// Add the header stating the request body is JSON
		httpRequest.header("Content-Type", "application/json");
		
		// Add the JSON to the body of request
		httpRequest.body(requestParameter.toJSONString());
		
		// Post Request
		Response resp = httpRequest.request(Method.POST, "/api/users?page=6");
		
		// Validation Starts form here
		
		// Capture Response body to perform the validation 
		String responseBody = resp.getBody().asString();
		
		Assert.assertEquals(responseBody.contains("Faizi"), true);
		Assert.assertEquals(responseBody.contains("Engineer"), true);
		
		// Capture the status code to perform the validation 
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		
		
		
		
	}

}
