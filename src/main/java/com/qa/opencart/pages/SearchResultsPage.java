package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. Private By Locators
	private By searchProducts = By.xpath("//div[@class='product-thumb']");

	// 2. Public Page Class Constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public int getSearchProductCount() {
		return elementUtil.waitForElementsVisible(searchProducts, TimeUtil.DEFAULT_Long_TIME).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		Log.info("searching for product: " + productName);
		elementUtil.waitForElementVisible(By.linkText(productName), TimeUtil.DEFAULT_Long_TIME).click();
		return new ProductInfoPage(driver);
	}

}
