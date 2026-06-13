package com.qa.opencart.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.logger.Log;

public class ScreenshotUtil {

	private static WebDriver driver = DriverFactory.getDriver();
	
	public static String getScreenshot(String methodName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			Log.error("Unable to capture Screenshot", e);
		}

		return path;
	}
}
