package com.sav.ra.test.locator;

import org.openqa.selenium.By;

public interface ContactUsPageLocator {

	By CONTACT_US_LINK = By.xpath("//a[text()='Contact Us']");
	By CONTACT_US_TEXT = By.xpath("//h2[text()='Contact Us']");
	By YOUR_NMAE = By.id("your-name");
	By YOUR_EMAIL = By.id("your-email");
	By SUBJECT = By.id("your-subject");
	By MESSAGE = By.id("your-message");
	By SEND_BTN = By.cssSelector("input[value='Send']");
	
}
