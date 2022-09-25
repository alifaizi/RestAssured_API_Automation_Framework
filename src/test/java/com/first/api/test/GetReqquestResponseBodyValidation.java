package com.first.api.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetReqquestResponseBodyValidation {

	@Test
	void validateAllHeaders() {
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in"; // Demo Web site for API testing

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/api/users?page=2");

		// Validation part starts from here:

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);


		// All Headers Validation
		Headers allHeaders = response.headers(); // This will get all the headers from response
		for (Header header : allHeaders) {

			System.out.println(header.getName() + "----------------->" + header.getValue());

		}

	}

}
