package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	//AAA - Arrange Act Assert
	//TC - only one hard assertion or can have multiple soft assertions

	WebDriver driver;
	protected Properties properties;
	DriverFactory driverFactory;
	
	protected LoginPage loginPage;
	protected AccountPage accountPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;
	
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional String browserName) {
		driverFactory = new DriverFactory();
		properties = driverFactory.initProperties();
		if (browserName!=null) {
			properties.setProperty("browser", browserName);
		}
		driver = driverFactory.initDriver(properties);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
