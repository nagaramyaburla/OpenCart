package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsTest extends BaseTest {
	
	@BeforeClass
	public void searchResultsSetup() {
		accountPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2}
		};
	}
	
	@Test(dataProvider = "getProductCountData")
	public void searchResultsTest(String searchKey, int productCount) {
		searchResultsPage = accountPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchProductCount(), productCount);
	}

}
