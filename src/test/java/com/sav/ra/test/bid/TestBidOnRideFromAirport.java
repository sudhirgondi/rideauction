package com.sav.ra.test.bid;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.framework.ExcelReader;
import com.sav.ra.shared.RideauctionSharedTestsAndUtils;

import static com.sav.ra.test.locator.BidFromAirportPageElementLocators.*;
import static com.sav.ra.test.locator.CommonBidPageElementLocators.*;

public class TestBidOnRideFromAirport extends RideauctionSharedTestsAndUtils {

	@DataProvider(name="bidOnRideFromAirportDataProviderPositive")
	public String[][][] bidOnRideFromAirportDataProviderPositive() {
		return ExcelReader.readExcelDataArr("bidOnRideAirportTestData.xlsx", "fromAirportPos");
	}
	
	@DataProvider(name="bidOnRideFromAirportDataProviderNegative")
	public String[][][] bidOnRideFromAirportDataProviderNegative() {
		return ExcelReader.readExcelDataArr("bidOnRideAirportTestData.xlsx", "fromAirportNeg");
	}

	@Test(dataProvider="bidOnRideFromAirportDataProviderPositive")
	public void testBidOnRideFromAirport(String[] dataParams) throws InterruptedException {
		int dataIndex = 0;

		String skipTest = dataParams[dataIndex++];
		if (skipTest.equals("y")) {throw new SkipException("skipping test");}
		
		String numPassengers = ExcelReader.stripDecimal(dataParams[dataIndex++]);

		String daysString = ExcelReader.stripDecimal(dataParams[dataIndex++]);
		String hourString = stripDecimal(dataParams[dataIndex++]);
		
		String minutes = dataParams[dataIndex++];
		String airport = dataParams[dataIndex++];
		String intOrDom = dataParams[dataIndex++];
		String airlineAndFlightNum = dataParams[dataIndex++];
		String curbPicOrGateMeet = dataParams[dataIndex++];
		String destAddressTypeIn = dataParams[dataIndex++];
		String destAddressContains = dataParams[dataIndex++];
		String clickForExtras = dataParams[dataIndex++];
		String extraStops = dataParams[dataIndex++];
		String numberOfStops = dataParams[dataIndex++];
		String carSeat = dataParams[dataIndex++];
		String boosterSeat = dataParams[dataIndex++];
		String dog = dataParams[dataIndex++];
		String carType = dataParams[dataIndex++];
		String promoCode = dataParams[dataIndex++];
		
		setPickupDateAndHour(daysString, hourString); 
		
		verifyAndClickElement(driver, FROM_RADIO_BTN);
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
		Thread.sleep(4000);		
		
		selectDropDown(driver, FROM_AIRPORT_DROP_DOWN, airport);
		
		if (intOrDom.equals("i")) {
			verifyAndClickElement(driver, INTERNATIONAL_RADIO_BTN);
		} 
		
		if (intOrDom.equals("d")) {
			verifyAndClickElement(driver, DOMESTIC_RADIO_BTN);
		}
		
		type(driver, AIRLINE_AND_FLIGHT_NUM_INPUT, airlineAndFlightNum);
		
		if (curbPicOrGateMeet.equals("gm")) {
			verifyAndClickElement(driver, GATE_MEET_RADIO_BTN);
		}
		
		if (curbPicOrGateMeet.equals("cp")){
			verifyAndClickElement(driver, CURBSIDE_PICKUP_RADIO_BTN);
		}
		
		type(driver, DESTINATION_ADDRESS_TEXT_AREA, destAddressTypeIn);
		Thread.sleep(4000);		
		
		String elmXpathString = "//div[@class='pac-item']/span[contains(text(),'"+destAddressContains+"')]";
		verifyElementPresent(driver, By.xpath(elmXpathString));
		verifyAndClickElement(driver, By.xpath(elmXpathString));
		
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
	
	@Test(dataProvider="bidOnRideFromAirportDataProviderNegative")
	public void testBidOnRideFromAirportNegative(String[] dataParams) throws InterruptedException {
		int idataIndex = 0;
		
		String skipTest = dataParams[idataIndex++];
		if (skipTest.equals("y")) {throw new SkipException("skipping test");}
		
		boolean numPassErr = false;
		boolean dateErr = false;
		boolean hourErr = false;
		boolean airportErr = false;
		boolean addressErr = false;
		boolean airlineAndFlightNumErr = false;
		
		String numPassengers = ExcelReader.stripDecimal(dataParams[idataIndex++]);
		
		String daysString = ExcelReader.stripDecimal(dataParams[idataIndex++]);
	
		String hourString = ExcelReader.stripDecimal(dataParams[idataIndex++]);
		
		String minutes = dataParams[idataIndex++];
		String airport = dataParams[idataIndex++];
		
		String intOrDom = dataParams[idataIndex++];
		String airlineAndFlightNum = dataParams[idataIndex++];
		String curbPicOrGateMeet = dataParams[idataIndex++];
		String destAddress = dataParams[idataIndex++];
		String validAddress = dataParams[idataIndex++];
		String clickForExtras = dataParams[idataIndex++];
		String extraStops = dataParams[idataIndex++];
		String numberOfStops = dataParams[idataIndex++];
		String carSeat = dataParams[idataIndex++];
		String boosterSeat = dataParams[idataIndex++];
		String dog = dataParams[idataIndex++];
		String carType = dataParams[idataIndex++];
		String promoCode = dataParams[idataIndex++];
		
		setPickupDateAndHour(daysString, hourString); 

		verifyAndClickElement(driver, FROM_RADIO_BTN);
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
			selectDropDown(driver, FROM_AIRPORT_DROP_DOWN, airport);
		}
		
		if (intOrDom.equals("i")) {
			verifyAndClickElement(driver, INTERNATIONAL_RADIO_BTN);
		} 
		
		if (intOrDom.equals("d")) {
			verifyAndClickElement(driver, DOMESTIC_RADIO_BTN);
		}
		
		if (airlineAndFlightNum.equals("")){
			airlineAndFlightNumErr = true;
		} else {
			type(driver, AIRLINE_AND_FLIGHT_NUM_INPUT, airlineAndFlightNum);
		}
		
		if (curbPicOrGateMeet.equals("gm")) {
			verifyAndClickElement(driver, GATE_MEET_RADIO_BTN);
		}
		
		if (curbPicOrGateMeet.equals("cp")){
			verifyAndClickElement(driver, CURBSIDE_PICKUP_RADIO_BTN);
		}
		
		if (destAddress.equals("")) {
			addressErr = true;
		} else if (validAddress.equals("n")) {
			addressErr = true;
			type(driver, DESTINATION_ADDRESS_TEXT_AREA, destAddress);
		} else {
			type(driver, DESTINATION_ADDRESS_TEXT_AREA, destAddress);			
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
		Thread.sleep(2000);
		
		if (numPassErr) {verifyElementPresent(driver, PSNGR_ERROR);}
		if (dateErr) {verifyElementPresent(driver, DATE_PICKER_ERROR);}
		if (hourErr) {verifyElementPresent(driver, HOUR_ERROR);}
		if (airportErr) {verifyElementPresent(driver, FROM_AIRPORT_ERROR);}
		if (addressErr) {verifyElementPresent(driver, DESTINATION_ERROR);}
		if (addressErr) {verifyElementPresent(driver, DESTINATION_ERROR_MSG);}
		if (airlineAndFlightNumErr) {verifyElementPresent(driver, AIRLINE_AND_FLIGHT_NUM_ERROR);}

		Thread.sleep(2000);
		verifyAndClickElement(driver, MODAL_CLOSE);
		Thread.sleep(2000);
	}
}
