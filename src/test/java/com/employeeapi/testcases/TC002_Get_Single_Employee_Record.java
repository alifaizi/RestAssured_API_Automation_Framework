package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import come.employee.base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_Employee_Record extends Base{
	
	@BeforeClass
	void getSinglEmployeeDetail() {
		
		logger.info("*****TC002_Get_Single_Employee_Record Started*****");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empID);
		
		hardWait(15000);
	}
	
	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@Test
	void checkResponseTime() {
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime < 2000);
	}
	
	@Test
	void checkStatusLine() {
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@AfterClass
	void endOfGetSinglEmployeeDetail() {
		
		logger.info("*****TC002_Get_Single_Employee_Record Ended*****");
	}
	

}
