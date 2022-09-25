package com.first.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetReqquestHeaderValidation {

	@Test
	void validateHeaders() {
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

		// Header Validation (Content-Type)
		String contentTypeHeader = response.header("Content-Type");
		System.out.println("Content type is: " + contentTypeHeader);
		Assert.assertEquals(contentTypeHeader, "application/json; charset=utf-8");

		// Header Validation (Server)
		String serverHeader = response.header("Server");
		System.out.println("Content type is: " + serverHeader);
		Assert.assertEquals(serverHeader, "cloudflare");

		// All Headers Validation
		Headers allHeaders = response.headers(); // This will get all the headers from response
		for (Header header : allHeaders) {

			System.out.println(header.getName() + "----------------->" + header.getValue());

		}

	}

}
