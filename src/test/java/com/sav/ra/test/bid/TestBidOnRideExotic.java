package com.sav.ra.test.bid;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.framework.ExcelReader;
import com.sav.ra.shared.RideauctionSharedTestsAndUtils;

import static com.sav.ra.test.locator.BidExoticPageElementLocators.*;
import static com.sav.ra.test.locator.CommonBidPageElementLocators.*;
 
public class TestBidOnRideExotic extends RideauctionSharedTestsAndUtils{
	@DataProvider(name="bidOnRideExoticDataProviderPositive")
	public String[][][] bidOnRideExoticDataProviderPositive() {
		return ExcelReader.readExcelDataArr("bidOnRideExoticTestData.xlsx", "exoticPos");
	}

	@DataProvider(name="bidOnRideExoticDataProviderNegative")
	public String[][][] bidOnRideExoticDataProviderNegative() {
		return ExcelReader.readExcelDataArr("bidOnRideExoticTestData.xlsx", "exoticNeg");
	}
	
	@Test(dataProvider="bidOnRideExoticDataProviderPositive")
	public void testBidOnRideExoticPositive(String[] dataParams) throws InterruptedException {
		int dataIndex = 0;
		
		String skipTest = dataParams[dataIndex++];
		if (skipTest.equals("y")) {throw new SkipException("skipping test");}
		
		String numPassengers = stripDecimal(dataParams[dataIndex++]);
		String selectCar = stripDecimal(dataParams[dataIndex++]);
		
		String daysString = stripDecimal(dataParams[dataIndex++]);
		
		String hourString = stripDecimal(dataParams[dataIndex++]);
		
		String minutes = dataParams[dataIndex++];
		String origAddressTypeIn = dataParams[dataIndex++];
		String origAddressContains = dataParams[dataIndex++];
		String requestDescription = dataParams[dataIndex++];
		String email = dataParams[dataIndex++];
		String phone = stripDecimal(dataParams[dataIndex++]);
		String budget = stripDecimal(dataParams[dataIndex++]);
		
		setPickupDateAndHour(daysString, hourString); 

		verifyAndClickElement(driver, EXOTIC_BID_ON_RIDE_BTN);
		waitForElementPresent(driver, HEADING, 5);
		selectDropDown(driver, NUM_PASSENGERS_DROP_DOWN, numPassengers);
		selectDropDown(driver, SELECT_A_CAR_DROP_DOWN, selectCar);
		 
		verifyAndClickElement(driver, PICKUP_DATE);
		
		for(int j=0; j < nextCounter; j++) {
			waitForElementPresent(driver, DATE_PICKER_NEXT_BTN, 3);
			verifyAndClickElement(driver, DATE_PICKER_NEXT_BTN);
		}

		waitForElementPresent(driver, By.linkText(String.valueOf(pickupDate)), 5);
		verifyAndClickElement(driver, By.linkText(String.valueOf(pickupDate)));
		
		selectDropDown(driver, HOUR, hourArr[hour-1]);
		selectDropDown(driver, MINUTE, minutes);
		
		type(driver, ORIGINATION_ADDRESS, origAddressTypeIn);
		Thread.sleep(4000);		
		String elmXpathString = "//div[@class='pac-item']/span[contains(text(),'"+origAddressContains+"')]";
		verifyElementPresent(driver, By.xpath(elmXpathString));
		verifyAndClickElement(driver, By.xpath(elmXpathString));
		
		type(driver, DESCRIBE_YOUR_REQUEST, requestDescription);
		type(driver, E_MAIL, email);
		type(driver, PHONE, phone);
		verifyAndClickElement(driver, YOUR_BUDGET);
		type(driver, YOUR_BUDGET, budget);
		
		verifyAndClickElement(driver, EXOTIC_SUBMIT_BTN);
		
		waitForElementPresent(driver, EXOTIC_INQUIRY_CONFIRMATION_HEADING, 10);
		waitForElementPresent(driver, INQUIRY_NUMBER, 10);
		waitForElementPresent(driver, CONTACT_LINK, 10);
		verifyAndClickElement(driver, CONFIRMATION_CLOSE_BTN);
		waitForElementPresent(driver, HOME_PAGE_LOGO_IMG, 10);
	}
	
	@Test(dataProvider="bidOnRideExoticDataProviderNegative")
	public void testBidOnRideExoticNegative(String[] dataParams) throws InterruptedException {
		int dataIndex = 0;
		
		String skipTest = dataParams[dataIndex++];
		if (skipTest.equals("y")) {throw new SkipException("skipping test");}
		
		boolean numPassErr = false;
		boolean dateErr = false;
		boolean hourErr = false;
		boolean origAddressErr = false;
		boolean requestDescriptionErr = false;
		boolean emailErr = false;
		boolean phoneErr = false;
		boolean budgetErr = false;
		
		String numPassengers = stripDecimal(dataParams[dataIndex++]);
		String selectCar = stripDecimal(dataParams[dataIndex++]);
		String daysString = stripDecimal(dataParams[dataIndex++]);
		String hourString = stripDecimal(dataParams[dataIndex++]);
		String minutes = dataParams[dataIndex++];
		String origAddress = dataParams[dataIndex++];
		String origValidAddress = dataParams[dataIndex++];
		String requestDescription = dataParams[dataIndex++];
		String validRequestDescription = dataParams[dataIndex++];
		String email = dataParams[dataIndex++];
		String validEmail = dataParams[dataIndex++];
		String phone = stripDecimal(dataParams[dataIndex++]);
		String validPhone = stripDecimal(dataParams[dataIndex++]);
		String budget = stripDecimal(dataParams[dataIndex++]);
		String validBudget = stripDecimal(dataParams[dataIndex++]);
		
		setPickupDateAndHour(daysString, hourString); 

		verifyAndClickElement(driver, EXOTIC_BID_ON_RIDE_BTN);
		waitForElementPresent(driver, HEADING, 5);
		
		if (numPassengers.equals("")) {
			numPassErr = true;
		} else {
			selectDropDown(driver, NUM_PASSENGERS_DROP_DOWN, numPassengers);
		}
		
		selectDropDown(driver, SELECT_A_CAR_DROP_DOWN, selectCar);
	
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
		
		if (origAddress.equals("")) {
			origAddressErr = true;
		} else if (origValidAddress.equals("n")) {
			origAddressErr = true;
			type(driver, ORIGINATION_ADDRESS, origAddress);
		} else {
			type(driver, ORIGINATION_ADDRESS, origAddress);			
		}
		
		if (requestDescription.equals("")) {
			requestDescriptionErr = true;
		} else if (requestDescription.equals("n")) {
			requestDescriptionErr = true;
			type(driver, DESCRIBE_YOUR_REQUEST, requestDescription);
		} else {
			type(driver, DESCRIBE_YOUR_REQUEST, requestDescription);			
		}
		
		if (email.equals("")) {
			emailErr = true;
		} else if (email.equals("n")) {
			emailErr = true;
			type(driver, E_MAIL, email);
		} else {
			type(driver, E_MAIL, email);			
		}
		
		if (phone.equals("")) {
			phoneErr = true;
		} else if (phone.equals("n")) {
			phoneErr = true;
			type(driver, PHONE, phone);
		} else {
			type(driver, PHONE, phone);			
		}
		
		if (budget.equals("")) {
			budgetErr = true;
		} else if (budget.equals("n")) {
			budgetErr = true;
			verifyAndClickElement(driver,YOUR_BUDGET);
			type(driver, YOUR_BUDGET, budget);
		} else {
			verifyAndClickElement(driver,YOUR_BUDGET);
			type(driver, YOUR_BUDGET, budget);			
		}
		
		verifyAndClickElement(driver, EXOTIC_SUBMIT_BTN);
		
		//Asserts
		if (numPassErr) {verifyElementPresent(driver, NUM_PASSENGERS_DROP_DOWN_ERR);}
		if (dateErr) {verifyElementPresent(driver, DATE_PICKER_ERROR);}
		if (hourErr) {verifyElementPresent(driver, HOUR_ERROR);}
		if (origAddressErr) {verifyElementPresent(driver, ORIGINATION_ERROR);}
		if (origAddressErr) {verifyElementPresent(driver, ORIGINATION_ERROR_MSG);}
		if (requestDescriptionErr) {verifyElementPresent(driver, REQUEST_DESCRIPTION_ERROR);}
		if (emailErr) {verifyElementPresent(driver, E_MAIL_ERROR);}
		if (phoneErr) {verifyElementPresent(driver, PHONE_ERROR);}
		if (budgetErr) {verifyElementPresent(driver, YOUR_BUDGET_ERROR);}
		
		Thread.sleep(2000);
		verifyAndClickElement(driver, MODAL_CLOSE);
		Thread.sleep(2000);
	}
}
