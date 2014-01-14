package com.sav.ra.test.locator;

import org.openqa.selenium.By;

public interface BidToAirportPageElementLocators {
	By TO_RADIO_BTN = By.id("toairport");
	By TO_AIRPORT_DROP_DOWN = By.id("ToAirport");
	By ORIGINATION_ADDRESS = By.name("OriginationAddress");

	// error cases
	By TO_AIRPORT_ERROR = By.cssSelector("#ToAirport.errorform");
	By ORIGINATION_ERROR = By.cssSelector("textarea[name='OriginationAddress'].errorform");
	By ORIGINATION_ERROR_MSG = By.xpath("//div[@class='errorblock'][text()='Please enter a valid US address']");
		
}
