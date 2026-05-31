package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private Map<String, String> productMap = new LinkedHashMap<String, String>();

	// 1. Private By Locators
	private By productHeader = By.tagName("h1");
	private By images = By.xpath("//ul[@class='thumbnails']//img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	// 2. Public Page Class Constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = elementUtil.doGetElementText(productHeader);
		Log.info(header);
		return header;
	}

	public int getProductImagesCount() {
		int totalImages = elementUtil.waitForElementsVisible(images, TimeUtil.DEFAULT_Long_TIME).size();
		Log.info("Images count for " + getProductHeader() + ": " + totalImages);
		return totalImages;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = elementUtil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String metaDataKey = text.split(":")[0].trim();
			String metaDataValue = text.split(":")[1].trim();
			productMap.put(metaDataKey, metaDataValue);
		}
	}

	private void getProductPriceData() {
		List<WebElement> priceDataList = elementUtil.getElements(productPriceData);
		String price = priceDataList.get(0).getText();
		productMap.put("Product Price", price);
		String metaPriceKey = priceDataList.get(1).getText().split(":")[0].trim();
		String metaPriceValue = priceDataList.get(1).getText().split(":")[1].trim();
		productMap.put(metaPriceKey, metaPriceValue);
	}

	public Map<String, String> getProductDetailsMap() {
		productMap.put("Header", getProductHeader());
		productMap.put("Product Images", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		Log.info("product details : " + productMap);
		return productMap;

	}

}
