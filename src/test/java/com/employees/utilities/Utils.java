package com.employees.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
	
	public static String empName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return (generatedString);
	}
	
	public static String empSal() {
		String generatedString = RandomStringUtils.randomNumeric(6);
		return (generatedString);
	}
	
	public static String empAge() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return (generatedString);
	}

}
