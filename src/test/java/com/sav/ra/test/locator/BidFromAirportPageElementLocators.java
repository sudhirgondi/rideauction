package com.sav.ra.test.locator;

import org.openqa.selenium.By;

public interface BidFromAirportPageElementLocators {
	
	By FROM_RADIO_BTN = By.id("fromairport");
	
	By FROM_AIRPORT_DROP_DOWN = By.id("FromAirport");
	By INTERNATIONAL_RADIO_BTN = By.cssSelector("input[value='International']");
	By DOMESTIC_RADIO_BTN = By.cssSelector("input[value='Domestic']");
	By AIRLINE_AND_FLIGHT_NUM_INPUT = By.cssSelector("input[name='Airline']");
	By CURBSIDE_PICKUP_RADIO_BTN = By.cssSelector("input[value='Curbside Pickup']");
	By GATE_MEET_RADIO_BTN = By.cssSelector("input[value='Gate Meet']");
	By DESTINATION_ADDRESS_TEXT_AREA = By.name("DestinationAddress");
	
	//error cases
	By FROM_AIRPORT_ERROR = By.cssSelector("#FromAirport.errorform");
	By AIRLINE_AND_FLIGHT_NUM_ERROR = By.cssSelector("input[name='Airline'].errorform");
	By DESTINATION_ERROR = By.cssSelector("textarea[name='DestinationAddress'].errorform");
	By DESTINATION_ERROR_MSG = By.xpath("//div[@class='errorblock'][text()='Please enter a valid US address']");
}
