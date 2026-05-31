package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accountPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
	}

	@Test
	public void accountPageTitleTest() {
		String actualTitle = accountPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accountPageURLTest() {
		String actualURL = accountPage.getAccountPageUrl();
		Assert.assertTrue(actualURL.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountPage.isLogoutLinkExist());

	}

	@Test
	public void isMyAccountLinkExistTest() {
		Assert.assertTrue(accountPage.myAccountLinkExist());

	}

	@Test
	public void accountsPageHeadersTest() {
		List<String> accountHeadersList = accountPage.getAccountPageHeadersList();
		System.out.println(accountHeadersList);
	}
	
	@Test
	public void searchTest() {
		accountPage.doSearch("macbook");
	}

}
