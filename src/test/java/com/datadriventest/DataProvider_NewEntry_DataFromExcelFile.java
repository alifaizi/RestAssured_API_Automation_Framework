package com.datadriventest;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataProvider_NewEntry_DataFromExcelFile {

	@Test(dataProvider="myDataProvider") // the parameter in the braces comes from data provider annotation at the end of page
	public void postNewEntry(String nName, String nJob) {

		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification httpRequest = RestAssured.given();

		// Here we create data which we can send along with the POST request
		JSONObject requestParameter = new JSONObject();
		
		requestParameter.put("name", nName); 
		requestParameter.put("job", nJob); 

		// Add the header stating the request body is JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the JSON to the body of request
		httpRequest.body(requestParameter.toJSONString());

		// Post Request
		Response resp = httpRequest.request(Method.POST, "/api/users?page=6");

		// Validation Starts form here

		// Capture Response body to perform the validation
		String responseBody = resp.getBody().asString();
		
		System.out.println("Response body is: " + responseBody); // Printing the body payload

		Assert.assertEquals(responseBody.contains(nName), true);
		Assert.assertEquals(responseBody.contains(nJob), true);

		// Capture the status code to perform the validation
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, 201);

	}

	
	@DataProvider(name="myDataProvider")
	public String[][] getData() throws IOException

	{
		// Read data from excel
		String path = System.getProperty("user.dir") + "/src/test/java/com/datadriventest/TestDataForAPIDataDriven.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String newData[][] = new String [rownum][colcount];
		
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				newData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return (newData);

	}

}
