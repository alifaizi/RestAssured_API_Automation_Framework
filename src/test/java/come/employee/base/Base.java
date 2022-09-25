package come.employee.base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {
	
	public static RequestSpecification httpRequest;
	public static Response response; 
	public String empID = "14";
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		logger=Logger.getLogger("EmployeesRestAPI"); 
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
	public static void hardWait(int TimeUNit) {
		try {
			Thread.sleep(TimeUNit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
