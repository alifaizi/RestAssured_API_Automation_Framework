package com.first.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetReqquestExtractValuesOfEachNodeInJson {

	@Test
	void validateValuesOfEachNodeInJsonBody() {
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in"; // Demo Web site for API testing

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/api/users/3");

		// Validation part starts from here:
		
		// Verify all nodes in the JSON body one by one
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("email"));
		System.out.println(jsonpath.get("first_name"));
		System.out.println(jsonpath.get("last_name"));
		System.out.println(jsonpath.get("avatar"));
		
		Assert.assertEquals(jsonpath.get("email"), "emma.wong@reqres.in");
		

	}

}
