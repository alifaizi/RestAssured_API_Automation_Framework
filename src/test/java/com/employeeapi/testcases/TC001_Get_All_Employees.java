package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import come.employee.base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends Base {

	@BeforeClass
	void getAllEmployees() {

		logger.info("**************TC001_Get_All_Employees Started**************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		hardWait(15000);
	}

	@Test
	void checkResponseBody() {

		logger.info("**************Checking Response Body**************");

		String responseBody = response.getBody().asString();
		logger.info("Response Body :: " + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	void checkStatusCode() {

		logger.info("**************Checking Status Code**************");

		int statusCode = response.statusCode();
		logger.info("Status Code :: " + statusCode);
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

	@Test
	void checkStatusLine() {

		logger.info("**************Checking Status Line**************");

		String statusLine = response.getStatusLine();
		logger.info("Status Line :: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {

		logger.info("**************Checking Content Type**************"); // Header

		String contentType = response.header("Content-Type");
		logger.info("Content Type :: " + contentType);
		Assert.assertEquals(contentType, "application/json");
	}

	@Test
	void checkServerType() {

		logger.info("**************Checking Server Type**************"); // Header

		String serverType = response.getHeader("Server");
		logger.info("Server Type :: " + serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6");

	}

	@Test
	void checkContentEncoding() {

		logger.info("**************Checking Content Encoding**************"); // Header

		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding :: " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@Test
	void checkContentLength() {

		logger.info("**************Checking Content Length**************"); // Header

		try {
			String contentLength = response.header("Content-Length");
			logger.info("Content Length :: " + contentLength);

			if (Integer.parseInt(contentLength) < 100)
				logger.warn("Content Length is lees than 100");

			Assert.assertTrue(Integer.parseInt(contentLength) > 100);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Test
	void checkCookies() {

		logger.info("**************Checking Content Length**************");

		String cookie = response.getCookie("PHPSESSID");
		logger.info("Cookie :: " + cookie);
		// Cookie value is completely dynamic and we cannot assert it
	}

	@AfterClass
	void endOfEmployeesTestCase() {

		logger.info("**************TC001_Get_All_Employees Ended**************");
	}
}
