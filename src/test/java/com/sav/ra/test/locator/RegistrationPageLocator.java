package com.sav.ra.test.locator;

import org.openqa.selenium.By;

public interface RegistrationPageLocator {

	 By REGISTRATION_LINK = By.linkText("Registration");
	 By RIDEAUCTION_IMG_LINK = By.linkText("RideAuction");
	 By USER_LOGIN = By.name("user_login");
	 By USER_EMAIL = By.name("user_email");
	 By USER_PASS = By.name("user_pass");
	 By USER_PHONE = By.name("UserPhone");
	 By PROMO_CODE = By.name("promocode");
	 By CAPTCHA_FIELD = By.id("recaptcha_response_field");
	 By TERMS = By.id("terms");
	 By REGISTER = By.id("wp-submit");
	
}
