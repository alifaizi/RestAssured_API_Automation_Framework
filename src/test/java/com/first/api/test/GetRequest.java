package com.first.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
	
	@Test 
	void getUserDetails() {
		// Specify base URL
		RestAssured.baseURI = "https://reqres.in"; // Demo Web site for API testing 
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response Object
		Response response = httpRequest.request(Method.GET,"/api/users?page");
		
		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		// Status code validation 
		int ststusCode = response.getStatusCode();
		System.out.println("Status code is: " + ststusCode);
		Assert.assertEquals(ststusCode, 200);
		
		// Status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		
		
		
		
	}

}
