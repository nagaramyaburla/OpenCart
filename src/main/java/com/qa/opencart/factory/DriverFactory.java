package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {

	WebDriver driver;
	Properties properties;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

	public static String highlight;

	public Properties initProperties() {
		FileInputStream ip = null;
		properties = new Properties();

		// envName=qa,stage,prod,uat,dev
		// mvn clean install -Denv="qa"
		// FileInputStream will create a connection with config.properties file

		String envName = System.getProperty("env");
		Log.info("Running tests on Env: " + envName);
		try {
			if (envName == null) {
				Log.info("No env is given... Hence running it on QA env...");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				default:
					Log.error("Please pass the right env name.." + envName);
					throw new FrameworkException(AppError.ENV_NOT_FOUND + " : " + envName);
				}
			}
			properties.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;
	}

	public WebDriver initDriver(Properties properties) {
		// String browserName = System.getProperty("browser");
		// If we want to pass browser value through maven command, we will use
		// System.getProperty{"browser")
		String browserName = properties.getProperty("browser");
		// System.out.println("Browser name is : " + browserName);
		Log.info("Browser name is : " + browserName);
		highlight = properties.getProperty("highlight");
		optionsManager = new OptionsManager(properties);
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			// threadLocalDriver.set(driver);
			threadLocalDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			// threadLocalDriver.set(driver);
			threadLocalDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "./src/test/resources/Drivers/msedgedriver.exe");
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			// threadLocalDriver.set(driver);
			threadLocalDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		default:
			Log.error("Please pass the right browser... " + browserName);
			// break; [Instead of just break, here we are throwing browser exception]
			throw new BrowserException("NO BROWSER FOUND... " + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(properties.getProperty("url"));

		return getDriver();
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
}
