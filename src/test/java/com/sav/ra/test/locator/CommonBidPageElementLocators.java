package com.sav.ra.test.locator;

import org.openqa.selenium.By;

public interface CommonBidPageElementLocators {
	By HOME_PAGE_LOGO_IMG = By.cssSelector(".logo>img[title='RideAuction']");
	
	By AIRPORT_BID_ON_RIDE_BTN = By.cssSelector("#airport input[value='Bid on Ride']");
	By HOURLY_BID_ON_RIDE_BTN = By.cssSelector("#hourly input[value='Bid on Ride']");
	By POINT_TO_POINT_BID_ON_RIDE_BTN = By.cssSelector("#transfers input[value='Bid on Ride']");
	By EXOTIC_BID_ON_RIDE_BTN = By.cssSelector("#exotic input[value='Bid on Ride']");
	
	By HEADING = By.xpath("//h1[text()='Get A Ride At Your Price']");
	
	By PSNGR_SELECT_BOX = By.id("passengers");
	By PICKUP_DATE = By.id("datepicker");
	By DATE_PICKER_NEXT_BTN = By.cssSelector(".ui-datepicker-next");
	By HOUR = By.id("hour");
	By MINUTE = By.id("minute");
	
	By EXTRAS_LINK = By.id("extralink");
	By EXTRA_STOPS_TEXT = By.xpath("//*[@id='extrases']/descendant::*[contains(text(), 'Extra Stops?')]");
	By EXTRA_STOPS_CHECK_BOX = By.id("ExtraStop");
	By NUM_STOPS_DROP_DOWN = By.name("NoOfStops");
	By CAR_SEAT_CHECK_BOX = By.xpath("//input[@value='Car Seat']");
	By BOOSTER_SEAT_CHECK_BOX = By.xpath("//input[@value='Booster Seat']");
	By I_HAVE_A_DOG_CHECK_BOX = By.xpath("//input[@value='I have a dog']");
	By CAR_TYPR_DROP_DOWN = By.id("Car");
	By PROMO_CODE = By.cssSelector("input[name='PromoCode']");
	By PRICE_CHECK_BTN = By.id("pricecheck");
	
	By AVERAGE_PRICE_TXT = By.xpath("//*[@id='ajaxresult']/descendant::h2[contains(text(), 'Average Price')]");
	By DOLLAR_SIGN = By.xpath("//*[@id='ajaxresult']/descendant::h2[contains(text(), '$')]");
	By TERMS_LINK = By.linkText("Terms and Conditions and Privacy Policy.");
	
	
	By TERMS_CHECK_BOX = By.id("termscheck");
	By BID_PRICE_FIELD = By.id("bidprice");
	By BID_BTN = By.id("bidsubmit");
	By BID_ERROR = By.cssSelector(".errordiv");
	By BUY_BTN = By.id("garantserv");
	By AVERAGE_PRICE = By.cssSelector(".checkresults>h2");
	By CONFIRMED_RIDE_PRICE = By.cssSelector("#garantpricevalue");
	By MODAL_CLOSE = By.cssSelector("#close");	
	
	//Passenger's Information form
	By PSNGR_INFO_FORM_HRADING1 = By.xpath("//h1[text()="+'"'+"Passenger's Information"+'"'+"]");
	By PSNGR_INFO_FORM_HRADING2 = By.xpath("//h1[text()='Payment Information']");
	By FIRST_AND_LAST_NAME_FIELD = By.id("FN");
	By PSNGR_INFO_FORM_E_MAIL = By.name("Mail");
	By PSNGR_INFO_FORM_PHONE = By.name("Phone");
	By SAME_AS_ABOVE_CHECK_BOX = By.id("sameabrove");
	By BILLING_FIRST_NAME_AND_LAST_NAME_FIELD = By.id("BillingFirstName");
	By CREDIT_CARD_FIELD = By.id("CCNum");
	By MONTH_DROP_DOWN_FIELD = By.id("expDateMonth_inpyt");
	By YEAR_DROP_DOWN_FIELD = By.id("expDateYear_inpyt");
	By SECURITY_CODE_FIELD = By.id("CCThreeDig");
	By PSNGR_INFO_SUBMIT_BTN = By.id("finishorder");
	
	
	// error cases
	By PSNGR_ERROR = By.cssSelector("#passengers.errorform");
	By DATE_PICKER_ERROR = By.cssSelector("#datepicker.errorform");
	By HOUR_ERROR = By.cssSelector("#hour.errorform");

	//Passenger's Information form error cases
	By FIRST_AND_LAST_NAME_FIELD_ERROR = By.cssSelector("#FN.errorform");
	By PSNGR_INFO_FORM_E_MAIL_ERROR = By.cssSelector("input[name='Mail'].errorform"); 
	By PSNGR_INFO_FORM_PHONE_ERROR = By.cssSelector("input[name='Phone'].errorform");
	By SAME_AS_ABOVE_CHECK_BOX_ERROR = By.cssSelector("#sameabrove.errorform");
	By BILLING_FIRST_NAME_AND_LAST_NAME_FIELD_ERROR = By.cssSelector("#BillingFirstName.errorform");
	By CREDIT_CARD_FIELD_ERROR = By.cssSelector("#CCNum.errorform");
	By MONTH_DROP_DOWN_FIELD_ERROR = By.cssSelector("#expDateMonth_inpyt.errorform");
	By YEAR_DROP_DOWN_FIELD_ERROR = By.cssSelector("#expDateYear_inpyt.errorform");
	By SECURITY_CODE_FIELD_ERROR = By.cssSelector("#CCThreeDig.errorform");
	
}
