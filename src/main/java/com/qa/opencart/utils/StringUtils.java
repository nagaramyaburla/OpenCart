package com.qa.opencart.utils;

public class StringUtils {
	
	public static String getRandomEmailId() {
		String emailId = "testautomation" + System.currentTimeMillis() + "@gmail.com";
		return emailId;
	}

}
