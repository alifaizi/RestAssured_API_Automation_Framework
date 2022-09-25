package com.first.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetReqquestAllHeadersValidation {

	@Test
	void validateJasonResponseBody() {
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in"; // Demo Web site for API testing

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/api/users/2");

		// Validation part starts from here:

		// Print response in console window
		String responseBody = response.getBody().asPrettyString(); // Returns response body in pretty format Json string 
		System.out.println("Response body is: " + responseBody);
		Assert.assertEquals(responseBody.contains("janet.weaver@reqres.in"), true);

	}

}
