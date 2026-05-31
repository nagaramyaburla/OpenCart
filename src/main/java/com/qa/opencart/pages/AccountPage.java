package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. Private By Locators
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.xpath("//div/a[text()='My Account']");
	private By headers = By.xpath("//div[@id='content']/h2");
	private By search = By.name("search");
	private By searchIcon = By.xpath("//div[@id='search']/descendant::button[@type='button']");

	// 2. Public Page Class Constructor
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		String title = elementUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_Medium_TIME);
		Log.info("Login page title : " + title);
		return title;
	}

	public String getAccountPageUrl() {
		String url = elementUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, TimeUtil.DEFAULT_Medium_TIME);
		Log.info("Login page url : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return elementUtil.waitForElementVisible(logoutLink, TimeUtil.DEFAULT_Long_TIME).isDisplayed();
	}

	public boolean myAccountLinkExist() {
		return elementUtil.waitForElementVisible(myAccountLink, TimeUtil.DEFAULT_Long_TIME).isDisplayed();
	}

	public List<String> getAccountPageHeadersList() {
		List<WebElement> headersElementList = elementUtil.getElements(headers);
		List<String> headersList = new ArrayList<String>();
		for (WebElement e : headersElementList) {
			String header = e.getText();
			headersList.add(header);
		}
		return headersList;

	}

	public SearchResultsPage doSearch(String searchKey) {
		Log.info("searching for : " + searchKey);
		elementUtil.doSendKeys(search, searchKey);
		elementUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

}
