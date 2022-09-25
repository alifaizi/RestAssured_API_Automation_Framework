package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import come.employee.base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_Employee_Record extends Base {

	@BeforeClass
	void deletEmployeeRecord() {

		logger.info("*******************TC005_Delete_Employee_Record Started*******************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		// First get the JSON Path object instance from the Response Interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Capture the ID
		String empID = jsonPathEvaluator.get("[0].id");

		response = httpRequest.request(Method.DELETE, "/delete/" + empID); // Pass ID to delete the record

		hardWait(15000);
	}

	@Test

	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);
	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@AfterClass
	void endOfDeletEmployeeRecord() {
		logger.info("*******************TC005_Delete_Employee_Record Ended*******************");
		
	}

}
