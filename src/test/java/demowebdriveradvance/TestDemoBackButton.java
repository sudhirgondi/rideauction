package demowebdriveradvance;

import static com.sav.ra.framework.PropertyManager.getProperty;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.sav.ra.framework.CaptchaService;
import com.sav.ra.framework.WebDriverTest;


//import com.sq.ra.captcha.CaptchaService;
import static com.sav.ra.test.locator.RegistrationPageLocator.*;


public class TestDemoBackButton extends WebDriverTest{
	
	@Test
	public void testRegistrationVerifyElements ()throws Exception{
		
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
		
		driver.navigate().back();
		driver.navigate().back();
		
		Thread.sleep(20000);
		
		driver.navigate().forward();
		
		Thread.sleep(20000);
		
	}

}
