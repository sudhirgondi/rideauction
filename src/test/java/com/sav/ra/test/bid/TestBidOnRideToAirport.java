package com.sav.ra.test.bid;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.framework.ExcelReader;
import com.sav.ra.shared.RideauctionSharedTestsAndUtils;

import static com.sav.ra.test.locator.BidToAirportPageElementLocators.*;
import static com.sav.ra.test.locator.CommonBidPageElementLocators.*;

public class TestBidOnRideToAirport extends RideauctionSharedTestsAndUtils {
	
	@DataProvider(name="bidOnRideToAirportDataProviderPositive")
	public String[][][] bidOnRideToAirportDataProviderPositive() {
		return ExcelReader.readExcelDataArr("bidOnRideAirportTestData.xlsx", "toAirportPos");
	}

	@DataProvider(name="bidOnRideToAirportDataProviderNegative")
	public String[][][] bidOnRideToAirportDataProviderNegative() {
		return ExcelReader.readExcelDataArr("bidOnRideAirportTestData.xlsx", "toAirportNeg");
	}
	
	@Test(dataProvider="bidOnRideToAirportDataProviderPositive")
	public void testBidOnRideToAirportPositive(String[] dataParams) throws InterruptedException {
		int dataIndex = 0;
		
		String skipTest = dataParams[dataIndex++];
		if (skipTest.equals("y")) {throw new SkipException("skipping test");}
		
		String numPassengers = stripDecimal(dataParams[dataIndex++]);

		String daysString = stripDecimal(dataParams[dataIndex++]);
		
		String hourString = stripDecimal(dataParams[dataIndex++]);
		
		String minutes = dataParams[dataIndex++];
		String airport = dataParams[dataIndex++];
		String origAddressTypeIn = dataParams[dataIndex++];
		String origAddressContains = dataParams[dataIndex++];
		String clickForExtras = dataParams[dataIndex++];
		String extraStops = dataParams[dataIndex++];
		String numberOfStops = dataParams[dataIndex++];
		String carSeat = dataParams[dataIndex++];
		String boosterSeat = dataParams[dataIndex++];
		String dog = dataParams[dataIndex++];
		String carType = dataParams[dataIndex++];
		String promoCode = dataParams[dataIndex++];
		
		setPickupDateAndHour(daysString, hourString); 

		verifyAndClickElement(driver, TO_RADIO_BTN);
		verifyAndClickElement(driver, AIRPORT_BID_ON_RIDE_BTN);
		
		waitForElementPresent(driver, HEADING, 5);
		selectDropDown(driver, PSNGR_SELECT_BOX, numPassengers);
		verifyAndClickElement(driver, PICKUP_DATE);
		
		for(int j=0; j < nextCounter; j++) {
			waitForElementPresent(driver, DATE_PICKER_NEXT_BTN, 3);
			verifyAndClickElement(driver, DATE_PICKER_NEXT_BTN);
		}

		waitForElementPresent(driver, By.linkText(String.valueOf(pickupDate)), 5);
		verifyAndClickElement(driver, By.linkText(String.valueOf(pickupDate)));
		
		selectDropDown(driver, HOUR, hourArr[hour-1]);
		selectDropDown(driver, MINUTE, minutes);
		
		selectDropDown(driver, TO_AIRPORT_DROP_DOWN, airport);
		type(driver, ORIGINATION_ADDRESS, origAddressTypeIn);
		Thread.sleep(4000);		
		String elmXpathString = "//div[@class='pac-item']/span[contains(text(),'"+origAddressContains+"')]";
		if ((elmXpathString != null) && !(elmXpathString.isEmpty())) {
			verifyElementPresent(driver, By.xpath(elmXpathString));
			verifyAndClickElement(driver, By.xpath(elmXpathString));
		}
		
		if(clickForExtras.equals("y")) {
			verifyAndClickElement(driver, EXTRAS_LINK);
			
			if (extraStops.equals("y")) {
				waitForElementPresent(driver, EXTRA_STOPS_TEXT, 1);
				verifyAndClickElement(driver, EXTRA_STOPS_CHECK_BOX);
				waitForElementPresent(driver, NUM_STOPS_DROP_DOWN, 3);
				selectDropDown(driver, NUM_STOPS_DROP_DOWN, numberOfStops);
			}
			
			if (carSeat.equals("y")) {
				verifyAndClickElement(driver, CAR_SEAT_CHECK_BOX);
			}
			
			if (boosterSeat.equals("y")) {
				verifyAndClickElement(driver, BOOSTER_SEAT_CHECK_BOX);
			}
			
			if (dog.equals("y")) {
				verifyAndClickElement(driver, I_HAVE_A_DOG_CHECK_BOX);
			}
		}
		 
		selectDropDown(driver, CAR_TYPR_DROP_DOWN, carType);
		type(driver, PROMO_CODE, promoCode);
		verifyAndClickElement(driver, PRICE_CHECK_BTN);
		
		waitForElementPresent(driver, AVERAGE_PRICE_TXT, 10);
		waitForElementPresent(driver, DOLLAR_SIGN, 10);
		waitForElementPresent(driver, TERMS_LINK, 10);
		
		if (dataParams.length > dataIndex) { //proceed to bid/buy if data is provided
			bidOrBuyPositive(dataParams, dataIndex);     
		}     
	}
	
	@Test(dataProvider="bidOnRideToAirportDataProviderNegative")
	public void testBidOnRideToAirportNegative(String[] dataParams) throws InterruptedException {
		
		int i = 0;
		String skipTest = dataParams[i++];
		if (skipTest.equals("y")) {throw new SkipException("skipping test");}
		
		boolean numPassErr = false;
		boolean dateErr = false;
		boolean hourErr = false;
		boolean airportErr = false;
		boolean addressErr = false;
		
		String numPassengers = stripDecimal(dataParams[i++]);
		String daysString = stripDecimal(dataParams[i++]);
		String hourString = stripDecimal(dataParams[i++]);
		
		String minutes = dataParams[i++];
		String airport = dataParams[i++];
		String origAddress = dataParams[i++];
		String validAddress = dataParams[i++];
		String clickForExtras = dataParams[i++];
		String extraStops = dataParams[i++];
		String numberOfStops = dataParams[i++];
		String carSeat = dataParams[i++];
		String boosterSeat = dataParams[i++];
		String dog = dataParams[i++];
		String carType = dataParams[i++];
		String promoCode = dataParams[i++];
		
		setPickupDateAndHour(daysString, hourString); 

		verifyAndClickElement(driver, TO_RADIO_BTN);
		verifyAndClickElement(driver, AIRPORT_BID_ON_RIDE_BTN);
		waitForElementPresent(driver, HEADING, 5);
		
		if (numPassengers.equals("")) {
			numPassErr = true;
		} else {
			selectDropDown(driver, PSNGR_SELECT_BOX, numPassengers);
		}
		if (daysString.equals("")) {
			dateErr = true;
		} else {
			verifyAndClickElement(driver, PICKUP_DATE);
			
			for(int j=0; j < nextCounter; j++) {
				waitForElementPresent(driver, DATE_PICKER_NEXT_BTN, 3);
				verifyAndClickElement(driver, DATE_PICKER_NEXT_BTN);
			}

			waitForElementPresent(driver, By.linkText(String.valueOf(pickupDate)), 5);
			verifyAndClickElement(driver, By.linkText(String.valueOf(pickupDate)));
		}
		
		if (hourString.equals("")) {
			hourErr = true;
		} else {
			selectDropDown(driver, HOUR, hourArr[hour-1]);
		}
		
		selectDropDown(driver, MINUTE, minutes);
		
		if (airport.equals("")) {
			airportErr = true;
		} else {
			selectDropDown(driver, TO_AIRPORT_DROP_DOWN, airport);
		}
		
		if (origAddress.equals("")) {
			addressErr = true;
		} else if (validAddress.equals("n")) {
			addressErr = true;
			type(driver, ORIGINATION_ADDRESS, origAddress);
		} else {
			type(driver, ORIGINATION_ADDRESS, origAddress);			
		}
		
		if(clickForExtras.equals("y")) {
			verifyAndClickElement(driver, EXTRAS_LINK);
			
			if (extraStops.equals("y")) {
				waitForElementPresent(driver, EXTRA_STOPS_TEXT, 1);
				verifyAndClickElement(driver, EXTRA_STOPS_CHECK_BOX);
				waitForElementPresent(driver, NUM_STOPS_DROP_DOWN, 3);
				selectDropDown(driver, NUM_STOPS_DROP_DOWN, numberOfStops);
			}
			
			if (carSeat.equals("y")) {
				verifyAndClickElement(driver, CAR_SEAT_CHECK_BOX);
			}
			
			if (boosterSeat.equals("y")) {
				verifyAndClickElement(driver, BOOSTER_SEAT_CHECK_BOX);
			}
			
			if (dog.equals("y")) {
				verifyAndClickElement(driver, I_HAVE_A_DOG_CHECK_BOX);
			}
		}
		
		selectDropDown(driver, CAR_TYPR_DROP_DOWN, carType);
		type(driver, PROMO_CODE, promoCode);
		verifyAndClickElement(driver, PRICE_CHECK_BTN);
		
		//Asserts
		if (numPassErr) {verifyElementPresent(driver, PSNGR_ERROR);}
		if (dateErr) {verifyElementPresent(driver, DATE_PICKER_ERROR);}
		if (hourErr) {verifyElementPresent(driver, HOUR_ERROR);}
		if (airportErr) {verifyElementPresent(driver, TO_AIRPORT_ERROR);}
		if (addressErr) {verifyElementPresent(driver, ORIGINATION_ERROR);}
		if (addressErr) {verifyElementPresent(driver, ORIGINATION_ERROR_MSG);}
		
		Thread.sleep(2000);
		verifyAndClickElement(driver, MODAL_CLOSE);
		Thread.sleep(2000);
	}
}
