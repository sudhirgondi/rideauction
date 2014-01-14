package com.sav.ra.test.locator;

import org.openqa.selenium.By;

public interface BidPointToPointPageElementLocators {
	By ORIGINATION_ADDRESS = By.name("OriginationAddress");
	By DESTINATION_ADDRESS = By.name("DestinationAddress");
	
	// error cases
	By ORIGINATION_ERROR = By.cssSelector("textarea[name='OriginationAddress'].errorform");
	By ORIGINATION_ERROR_MSG = By.xpath("//textarea[@name='OriginationAddress']/following-sibling::*[1]");
	By DESTINATION_ERROR = By.cssSelector("textarea[name='DestinationAddress'].errorform");
	By DESTINATION_ERROR_MSG = By.xpath("//textarea[@name='DestinationAddress']/following-sibling::*[1]");
		
}
