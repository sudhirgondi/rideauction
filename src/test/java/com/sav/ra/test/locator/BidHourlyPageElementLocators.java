package com.sav.ra.test.locator;

import org.openqa.selenium.By;

public interface BidHourlyPageElementLocators {
	By IN_THE_CITY_RADIO_BTN = By.id("hourlydriver");
	By OUTSIDE_RADIO_BTN = By.id("hourlycircle");
	By NUM_HOURS_DROP_DOWN = By.name("NumberOfHours");
	By ORIG_ADDRESS = By.name("OriginalAddress");
	By DESTINATION_ADDRESS = By.name("DestinationAddress");
	
	// error cases
	By NUM_HOURS_ERROR = By.cssSelector("select.errorform[name='NumberOfHours']");
	By ORIGINATION_ERROR = By.cssSelector("textarea[name='OriginalAddress'].errorform");
	By ORIGINATION_ERROR_MSG = By.xpath("//textarea[@name='OriginalAddress']/following-sibling::*[1]");
	By DESTINATION_ERROR = By.cssSelector("textarea[name='DestinationAddress'].errorform");
	By DESTINATION_ERROR_MSG = By.xpath("//textarea[@name='DestinationAddress']/following-sibling::*[1]");
		
}
