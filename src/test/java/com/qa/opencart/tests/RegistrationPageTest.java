package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] { { "gaurav", "gupta", "9876543212", "gg@123", "yes" },
				{ "shilpa", "mamiidipelli", "9876543662", "shilpa@123", "no" },
				{ "om", "gautam", "9876587653", "om@123", "yes" }

		};
	}

	@DataProvider
	public Object[][] getUserRegTestDataFromExcel() {
		return ExcelUtil.getExcelData(AppConstants.REGISTER_SHEET_NAME);
	}

	// We can either use dataprovider name or method name
	@DataProvider(name = "csvregdata")
	public Object[][] getUseRegTestDataFromCSV() {
		return CSVUtil.getCSVData(AppConstants.REGISTER_SHEET_NAME);
	}

	// @Test(dataProvider = "getUseRegTestDataFromCSV")
	@Test(dataProvider = "csvregdata")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(registrationPage.userRegistration(firstName, lastName, StringUtils.getRandomEmailId(),
				telephone, password, subscribe));
	}

}
