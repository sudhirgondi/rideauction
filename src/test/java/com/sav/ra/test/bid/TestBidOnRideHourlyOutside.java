package com.sav.ra.test.bid;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.framework.ExcelReader;
import com.sav.ra.shared.RideauctionSharedTestsAndUtils;

import static com.sav.ra.test.locator.BidHourlyPageElementLocators.*;
import static com.sav.ra.test.locator.CommonBidPageElementLocators.*;

public class TestBidOnRideHourlyOutside extends RideauctionSharedTestsAndUtils{

	@DataProvider(name="bidOnRideHourlyOutsideDataProviderPositive")
	public String[][][] bidOnRideHourlyOutsideDataProviderPositive() {
		return ExcelReader.readExcelDataArr("bidOnRideHourlyTestData.xlsx", "outsidePos");
	}

	@DataProvider(name="bidOnRideHourlyOutsideDataProviderNegative")
	public String[][][] bidOnRideHourlyOutsideDataProviderNegative() {
		return ExcelReader.readExcelDataArr("bidOnRideHourlyTestData.xlsx", "outsideNeg");
	}
	
	@Test(dataProvider="bidOnRideHourlyOutsideDataProviderPositive")
	public void testBidOnRideHourlyInTheCityPositive(String[] dataParams) throws InterruptedException {
		int dataIndex = 0;
		
		String skipTest = dataParams[dataIndex++];
		if (skipTest.equals("y")) {throw new SkipException("skipping test");}
		
		String numPassengers = stripDecimal(dataParams[dataIndex++]);
		String daysString = stripDecimal(dataParams[dataIndex++]);
		String hourString = stripDecimal(dataParams[dataIndex++]);
		String minutes = dataParams[dataIndex++];
		String numHours = stripDecimal(dataParams[dataIndex++]);
		String origAddressTypeIn = dataParams[dataIndex++];
		String origAddressContains = dataParams[dataIndex++];
		String destAddressTypeIn = dataParams[dataIndex++];
		String destAddressContains = dataParams[dataIndex++];
		String clickForExtras = dataParams[dataIndex++];
		String carSeat = dataParams[dataIndex++];
		String boosterSeat = dataParams[dataIndex++];
		String dog = dataParams[dataIndex++];
		String carType = dataParams[dataIndex++];
		String promoCode = dataParams[dataIndex++];

		setPickupDateAndHour(daysString, hourString); 

		verifyAndClickElement(driver, OUTSIDE_RADIO_BTN);
		verifyAndClickElement(driver, HOURLY_BID_ON_RIDE_BTN);
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
		
		selectDropDown(driver, NUM_HOURS_DROP_DOWN, numHours);
		type(driver, ORIG_ADDRESS, origAddressTypeIn);
		Thread.sleep(4000);		
		String elmXpathString = "//div[@class='pac-item']/span[contains(text(),'"+origAddressContains+"')]";
		verifyElementPresent(driver, By.xpath(elmXpathString));
		verifyAndClickElement(driver, By.xpath(elmXpathString));
		
		type(driver, DESTINATION_ADDRESS, destAddressTypeIn);
		Thread.sleep(4000);		
		String elmXpathString2 = "//div[@class='pac-item']/span[contains(text(),'"+destAddressContains+"')]";
		verifyElementPresent(driver, By.xpath(elmXpathString2));
		verifyAndClickElement(driver, By.xpath(elmXpathString2));
		
		if(clickForExtras.equals("y")) {
			verifyAndClickElement(driver, EXTRAS_LINK);
			
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
	
	@Test(dataProvider="bidOnRideHourlyOutsideDataProviderNegative")
	public void testBidOnRideHourlyOutsideNegative(String[] dataParams) throws InterruptedException {
		int dataIndex = 0;

		String skipTest = dataParams[dataIndex++];
		if (skipTest.equals("y")) {throw new SkipException("skipping test");}
		
		boolean numPassErr = false;
		boolean dateErr = false;
		boolean hourErr = false;
		boolean numHoursErr = false;
		boolean origAddressErr = false;
		boolean destAddressErr = false;
		
		String numPassengers = stripDecimal(dataParams[dataIndex++]);
		String daysString = stripDecimal(dataParams[dataIndex++]);
		String hourString = stripDecimal(dataParams[dataIndex++]);
		String minutes = dataParams[dataIndex++];
		String numHours = stripDecimal(dataParams[dataIndex++]);
		String origAddress = dataParams[dataIndex++];
		String origValidAddress = dataParams[dataIndex++];
		String destAddress = dataParams[dataIndex++];
		String destValidAddress = dataParams[dataIndex++];
		String clickForExtras = dataParams[dataIndex++];
		String carSeat = dataParams[dataIndex++];
		String boosterSeat = dataParams[dataIndex++];
		String dog = dataParams[dataIndex++];
		String carType = dataParams[dataIndex++];
		String promoCode = dataParams[dataIndex++];
		
		setPickupDateAndHour(daysString, hourString); 

		verifyAndClickElement(driver, OUTSIDE_RADIO_BTN);
		verifyAndClickElement(driver, HOURLY_BID_ON_RIDE_BTN);
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
		
		if (numHours.equals("")) {
			numHoursErr = true;
		} else {
			selectDropDown(driver, NUM_HOURS_DROP_DOWN, numHours);
		}
		
		if (origAddress.equals("")) {
			origAddressErr = true;
		} else if (origValidAddress.equals("n")) {
			origAddressErr = true;
			type(driver, ORIG_ADDRESS, origAddress);
		} else {
			type(driver, ORIG_ADDRESS, origAddress);			
		}
		
		if (destAddress.equals("")) {
			destAddressErr = true;
		} else if (destValidAddress.equals("n")) {
			destAddressErr = true;
			type(driver, DESTINATION_ADDRESS, destAddress);
		} else {
			type(driver, DESTINATION_ADDRESS, destAddress);			
		}
		
		if(clickForExtras.equals("y")) {
			verifyAndClickElement(driver, EXTRAS_LINK);
			
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
		if (numHoursErr) {verifyElementPresent(driver, NUM_HOURS_ERROR);}
		if (origAddressErr) {verifyElementPresent(driver, ORIGINATION_ERROR);}
		if (origAddressErr) {verifyElementPresent(driver, ORIGINATION_ERROR_MSG);}
		if (destAddressErr) {verifyElementPresent(driver, DESTINATION_ERROR);}
		if (destAddressErr) {verifyElementPresent(driver, DESTINATION_ERROR_MSG);}
		
		Thread.sleep(2000);
		verifyAndClickElement(driver, MODAL_CLOSE);
		Thread.sleep(2000);
	}
}
