package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employees.utilities.Utils;

import come.employee.base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Post_Employee_Record extends Base {

	String empName = Utils.empName();
	String empSalary = Utils.empSal();
	String empAge = Utils.empAge();

	@BeforeClass
	void createEmployee() throws InterruptedException{

		logger.info("******TC003_Post_Employee_Record_Started********");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON.
		// We can add Key-Value pairs using the put method
		// {"name" : "John", "Salary" : "100000", "age" : "36"}

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("Salary", empSalary);
		requestParams.put("Age", empAge);

		// Adding header to specify that the body is in JSON format
		httpRequest.header("Content-Type", "application/json");
		
		// Adding the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/create");

		hardWait(15000);
	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() {

		logger.info("**************Checking Response Time**************");

		long responseTime = response.getTime();
		logger.info("Response Time :: " + responseTime);

		if (responseTime > 2000)
			logger.warn("Response time is greater than 2000");

		Assert.assertTrue(responseTime < 2000);
	}

	@AfterClass
	void endOfPostEmployeeRecord() {

		logger.info("******TC003_Post_Employee_Record_Started********");

	}
	

}
