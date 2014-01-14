package com.sav.ra.shared;

import com.sav.ra.framework.WebDriverTest;
import static org.hamcrest.core.IsNot.*;

import static com.sav.ra.test.locator.CommonBidPageElementLocators.*;

import java.util.Date;
import java.util.regex.*;

import org.junit.Assert;

public class RideauctionSharedTestsAndUtils extends WebDriverTest{
	protected int nextCounter = 0;
	protected int pickupDate = 0;
	protected int hour;
	
	protected static String[] hourArr = {"1 AM","2 AM","3 AM","4 AM","5 AM","6 AM","7 AM","8 AM","9 AM","10 AM","11 AM",
		"12 Noon","1 PM","2 PM","3 PM","4 PM","5 PM","6 PM","7 PM","8 PM","9 PM","10 PM","11 PM","12 Midnight"};
	
	public static String stripDecimal(String dString) {
		if (dString.indexOf(".0")!= -1) {
			return dString.substring(0, dString.indexOf(".0"));
		} else {
			return dString;
		}
	}
	
	public void setPickupDateAndHour(String daysString, String hourString) {
		
		int days; 
		if (daysString.equals("")){
			days = 0;
		} else {
			days = Integer.parseInt(stripDecimal(daysString));
		}
		
		if (hourString.equals("")){
			hour = 0;
		} else {
			hour = Integer.parseInt(stripDecimal(hourString));
		}
		
		int curDate = 0;
		int curHour = 0;
		
		Date dt = new Date();
		curDate = dt.getDate();
		curHour = dt.getHours();
		pickupDate = curDate;
		
		if (hour < 2) hour = 2;
		if (hour > 24) hour = 24;
		
		if (days == 0) { //that is today
			hour += curHour;
			if (hour > 24) {
				hour -= 24;
				pickupDate += 1;
			}
		}
		
		if (days > 0 && days < 27) {
			pickupDate = curDate + days;
			if (pickupDate > 28) {
				pickupDate = curDate;
			}
		}
		
		if (days >= 30) {
			nextCounter = days/30;
		}
	}
	
	public void bidOrBuyPositive(String[] dataParams, int dataIndex) {
		
		String buyOrBid = dataParams[dataIndex++];
		
		if ((buyOrBid != null) && !(buyOrBid.isEmpty())) {
			String cpGreaterThan = dataParams[dataIndex++];
			String bidMethod = dataParams[dataIndex++];
			String piFnAndLn = dataParams[dataIndex++];
			String piEmail = dataParams[dataIndex++];
			String piPhone = stripDecimal(dataParams[dataIndex++]);
			String sameAsAbove = dataParams[dataIndex++];
			String piBillingFnAndLn = dataParams[dataIndex++];
			String piCcNum = stripDecimal(dataParams[dataIndex++]);
			String piCcMonth = dataParams[dataIndex++];
			String piCcYear = stripDecimal(dataParams[dataIndex++]);
			String piCcSecCode = stripDecimal(dataParams[dataIndex++]);

			String regex = "[0-9]+";

			waitForElementPresent(driver, AVERAGE_PRICE_TXT, 5);
			waitForElementPresent(driver, CONFIRMED_RIDE_PRICE, 5);

			String cpBidPrice = driver.findElement(CONFIRMED_RIDE_PRICE)
					.getAttribute("value");

			String avg_price_text = driver.findElement(AVERAGE_PRICE).getText();
			String apBidPrice;
			Matcher matcher = Pattern.compile(regex).matcher(avg_price_text);

			if (matcher.find()) {
				apBidPrice = matcher.group();
			} else {
				apBidPrice = "0";
			}

			Assert.assertThat(Integer.parseInt(cpBidPrice), not(0));
			Assert.assertThat(Integer.parseInt(apBidPrice), not(0));
			
			if ((cpGreaterThan != null) && !(cpGreaterThan.isEmpty())) {
				int cpbp = Integer.parseInt(cpBidPrice);
				int expectedCpbp = Integer.parseInt(stripDecimal(cpGreaterThan));
				System.out.println(cpbp);
				Assert.assertTrue(cpbp > expectedCpbp);
			}

			verifyAndClickElement(driver, TERMS_CHECK_BOX);

			if (buyOrBid.equals("buy")) {
				verifyAndClickElement(driver, BUY_BTN);
			}

			if (buyOrBid.equals("bid")) {

				if (bidMethod.equals("cp")) {
					verifyAndClickElement(driver, BID_PRICE_FIELD);
					type(driver, BID_PRICE_FIELD, cpBidPrice);

				}
				if (bidMethod.equals("ap")) {
					verifyAndClickElement(driver, BID_PRICE_FIELD);
					type(driver, BID_PRICE_FIELD, apBidPrice);
				}
				if (bidMethod.equals("mbp")) {
					type(driver, BID_PRICE_FIELD, "0");
					verifyAndClickElement(driver, BID_BTN);
					waitForElementPresent(driver, BID_ERROR, 2);
					String bid_error = driver.findElement(BID_ERROR).getText();
					matcher = Pattern.compile(regex).matcher(bid_error);
					if (matcher.find()) {
						String mbpBidPrice = matcher.group();
						Assert.assertThat(Integer.parseInt(mbpBidPrice), not(0));
						verifyAndClickElement(driver, BID_PRICE_FIELD);
						type(driver, BID_PRICE_FIELD, mbpBidPrice);
					}
				}
				verifyAndClickElement(driver, BID_BTN);
			}

			waitForElementPresent(driver, PSNGR_INFO_FORM_HRADING1, 5);
			waitForElementPresent(driver, PSNGR_INFO_FORM_HRADING2, 1);
			type(driver, FIRST_AND_LAST_NAME_FIELD, piFnAndLn);
			type(driver, PSNGR_INFO_FORM_E_MAIL, piEmail);
			type(driver, PSNGR_INFO_FORM_PHONE, piPhone);

			if (sameAsAbove.equals("y")) {
				verifyAndClickElement(driver, SAME_AS_ABOVE_CHECK_BOX);
			} else {
				type(driver, BILLING_FIRST_NAME_AND_LAST_NAME_FIELD,
						piBillingFnAndLn);
			}

			type(driver, CREDIT_CARD_FIELD, piCcNum);
			selectDropDown(driver, MONTH_DROP_DOWN_FIELD, piCcMonth);
			selectDropDown(driver, YEAR_DROP_DOWN_FIELD, piCcYear);
			type(driver, SECURITY_CODE_FIELD, piCcSecCode);

			verifyAndClickElement(driver, PSNGR_INFO_SUBMIT_BTN);
		}
	}
}
