package com.first.api.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {

	@Test
	void registerNewUser() {
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in"; // Demo Web site for API testing

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Request payload to be sent
		JSONObject jo = new JSONObject();

		jo.put("name", "Ali");
		jo.put("job", "Student");

		httpRequest.header("content-Type", "application/json");

		httpRequest.body(jo.toJSONString());

		// Response Object
		Response response = httpRequest.request(Method.POST, "/api/users");

		// Validation part starts from here:
		
		// Print response in console window
		String responseBody = response.getBody().asString(); // Attach the above data to the request
		System.out.println("Response body is: " + responseBody);

		// Status code validation
		int ststusCode = response.getStatusCode();
		System.out.println("Status code is: " + ststusCode);
		Assert.assertEquals(ststusCode, 201);

		// Success Code Validation
		String successCode = response.jsonPath().get("SuccessCode");
		
		
		

	}
}
