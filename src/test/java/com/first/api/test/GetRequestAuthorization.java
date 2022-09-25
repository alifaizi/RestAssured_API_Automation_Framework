package com.first.api.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestAuthorization {
	
	
	@Test
	public void AuthorizationTtest() {
		
		// Specify base URI
		RestAssured.baseURI = "https://tek-insurance-api.azurewebsites.net"; // Demo Web site for API testing 
		
		// Basic Authentication
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("supervisor");
		authScheme.setPassword("tek_supervisor");
		RestAssured.authentication = authScheme;
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response Object
		Response response = httpRequest.request(Method.GET,"/api/token");
		
		// Print response body in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		
		// Status code validation 
		int ststusCode = response.getStatusCode();
		System.out.println("Status code is: " + ststusCode);
		Assert.assertEquals(ststusCode, 200);
	}

}
