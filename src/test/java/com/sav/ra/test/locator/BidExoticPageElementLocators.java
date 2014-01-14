package com.sav.ra.test.locator;

import org.openqa.selenium.By;

public interface BidExoticPageElementLocators {
	By NUM_PASSENGERS_DROP_DOWN = By.id("NoOfPass");
	By SELECT_A_CAR_DROP_DOWN = By.name("selectcar");
	By ORIGINATION_ADDRESS = By.name("OriginationAddress");
	By DESCRIBE_YOUR_REQUEST = By.name("RequestBody");
	By E_MAIL = By.name("Mail");
	By PHONE = By.name("Phone");
//	By YOUR_BUDGET = By.name("budget");
	By YOUR_BUDGET = By.cssSelector("input[name='budget']");
	By EXOTIC_SUBMIT_BTN = By.id("orderexoticsubmit");
	
	By EXOTIC_INQUIRY_CONFIRMATION_HEADING = By.xpath("//h1[text()='Inquiry Confirmation']");
	By CONTACT_LINK = By.linkText("partners@rideauction.com");
	By INQUIRY_NUMBER = By.xpath("//p[contains(text(), 'Your Inquiry number is RA-')]");
	By CONFIRMATION_CLOSE_BTN = By.id("exoticclose");
	
	
	// error cases
	By NUM_PASSENGERS_DROP_DOWN_ERR = By.cssSelector("#NoOfPass.errorform"); 
	By ORIGINATION_ERROR = By.cssSelector("textarea[name='OriginationAddress'].errorform");
	By ORIGINATION_ERROR_MSG = By.xpath("//textarea[@name='OriginationAddress']/following-sibling::*[1]");
	By REQUEST_DESCRIPTION_ERROR = By.cssSelector("textarea[name='RequestBody'].errorform");
	By E_MAIL_ERROR = By.cssSelector("input[name='Mail'].errorform");
	By PHONE_ERROR = By.cssSelector("input[name='Phone'].errorform");
	By YOUR_BUDGET_ERROR = By.cssSelector("input[name='budget'].errorform");
		
}
