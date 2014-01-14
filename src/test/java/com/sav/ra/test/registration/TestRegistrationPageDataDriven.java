package com.sav.ra.test.registration;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.framework.CaptchaService;
import com.sav.ra.framework.ExcelReader;
import com.sav.ra.framework.WebDriverTest;

import static com.sav.ra.test.locator.RegistrationPageLocator.*;

public class TestRegistrationPageDataDriven extends WebDriverTest {
	
	@DataProvider(name="userDataProvider")
	public Object[][] userDataProvider() {
		return ExcelReader.readExcelData("userRegisrationData.xlsx", "sheet1");
	}
	
	@Test(dataProvider="userDataProvider")
	public void testRegistration(String userName, String email, String password, String phone, String pCode, String valid){
		verifyAndClickElement(driver, REGISTRATION_LINK);
		waitForElementPresent(driver, USER_LOGIN, 5);
		verifyElementPresent(driver, RIDEAUCTION_IMG_LINK);
		verifyElementPresent(driver, USER_EMAIL);
		type(driver, USER_LOGIN, userName);
		type(driver, USER_EMAIL, email);
		type(driver, USER_PASS, password);
		type(driver, USER_PHONE, phone);
		type(driver, PROMO_CODE, pCode);
		type(driver, CAPTCHA_FIELD, CaptchaService.getCaptchaValue());
		verifyAndClickElement(driver, TERMS);
		
		int noInputs = driver.findElements(By.xpath("//input[@type='text']")).size();
		Assert.assertEquals(6, noInputs);
		
		verifyAndClickElement(driver, REGISTER);
		
		if("Y".equalsIgnoreCase(valid)) {
			//assert for a welcome text
		} else {
			isElementPresent(driver, By.id("login_error"));
		}
		
	}
}
