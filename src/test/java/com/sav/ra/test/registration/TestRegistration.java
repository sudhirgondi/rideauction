package com.sav.ra.test.registration;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sav.ra.framework.CaptchaService;
import com.sav.ra.framework.WebDriverTest;

import static com.sav.ra.test.locator.RegistrationPageLocator.*;

public class TestRegistration extends WebDriverTest {
	@Test
	public void testRegistration(){
		verifyAndClickElement(driver, REGISTRATION_LINK);
		waitForElementPresent(driver, USER_LOGIN, 5);
		verifyElementPresent(driver, RIDEAUCTION_IMG_LINK);
		verifyElementPresent(driver, USER_EMAIL);
		type(driver, USER_LOGIN, "test2@test.com");
		type(driver, USER_EMAIL, "test2@test.com");
		type(driver, USER_PASS, "testing");
		type(driver, USER_PHONE, "555-555-5555");
		type(driver, PROMO_CODE, "555-555-5555");
		type(driver, CAPTCHA_FIELD, CaptchaService.getCaptchaValue());
		verifyAndClickElement(driver, TERMS);
		verifyAndClickElement(driver, REGISTER);
		
		int noInputs = driver.findElements(By.xpath("//input[@type='text']")).size();
		Assert.assertEquals(6, noInputs);
	}
}
