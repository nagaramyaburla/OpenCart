package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. Private By Locators
	private By emailId = By.name("email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2. Public Page Class Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// 3. Public Page Actions/Method
	@Step("getting login page title....")
	public String getLoginPageTitle() {
		String title = elementUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_SHORT_TIME);
		// Log.info("Login page title : " + title);
		Log.info("Login page title : " + title);
		return title;
	}
	
	@Step("getting login page url....")
	public String getLoginPageUrl() {
		String url = elementUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_SHORT_TIME);
		Log.info("Login page url : " + url);
		return url;
	}

	@Step("getting the state of forgot pwd link...")
	public boolean isForgotPwdLinExist() {
		return elementUtil.isElementDisplayed(forgotPwdLink);
	}

	@Step("login with username: {0} and password: {1}")
	public AccountPage doLogin(String username, String pwd) {
		Log.info("User Creds - " + username + " : " + pwd);
		elementUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_Long_TIME).sendKeys(username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountPage(driver);
	}

	@Step("navigating to registration page...")
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_Long_TIME).click();
		return new RegistrationPage(driver);
	}

}
