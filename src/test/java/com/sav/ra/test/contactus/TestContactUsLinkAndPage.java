package com.sav.ra.test.contactus;

import org.testng.annotations.Test;
import static com.sav.ra.test.locator.ContactUsPageLocator.*;

import com.sav.ra.framework.WebDriverTest;

public class TestContactUsLinkAndPage extends WebDriverTest{
	@Test
	public void testContactUsLinkAndPage(){
		verifyAndClickElement(driver, CONTACT_US_LINK);
		waitForElementPresent(driver, CONTACT_US_TEXT, 5);
		type(driver, YOUR_NMAE, "Sudhir");
		type(driver, YOUR_EMAIL, "sudhir@sudhir.com");
		type(driver, SUBJECT, "make me mula");
		type(driver, MESSAGE, "make me mula, more nad more mula...");
		verifyAndClickElement(driver, SEND_BTN);
	}
}
