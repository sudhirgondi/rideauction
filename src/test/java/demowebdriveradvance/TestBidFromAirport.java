package demowebdriveradvance;
import org.junit.Ignore;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.testng.*;
import org.testng.annotations.*;

import com.sav.ra.framework.PropertyManager;
import com.sav.ra.framework.WebDriverManager;
import com.thoughtworks.selenium.*;

import static org.testng.Assert.*;

public class TestBidFromAirport {
	static Selenium selenium;

	@BeforeClass
	public void runBeforeClass() {
		//selenium = new DefaultSelenium("localhost", 4444, "*firefox",
				//"http://www.ridenegotiator.com");
		selenium = new WebDriverBackedSelenium(WebDriverManager.getDriver(), PropertyManager.getProperty("appurl"));		
	}

	@BeforeMethod
	public void runBeforeEachTest() {
//		selenium.start(); // launches the browser
		selenium.open("/"); // uses the URL specified above in DefaultSelenium
							// constructor and goes to the root (home page in
							// this case)
		selenium.waitForPageToLoad("50000"); // wits for 5 seconds to load the
											// page. if the page gets loads in
											// less than 5 seconds it moves to
											// the next step without waiting
											// full 5 seconds
	}

	@AfterMethod
	public void runAfterMethod() {
		selenium.close();
		selenium.stop();
	}
	
	@Ignore
	public void testBidonRideFromAirport() throws InterruptedException {
		Thread.sleep(10000);
		selenium.click("CSS=#fromairport");
		selenium.click("CSS=#airport input[value='Bid on Ride']");
		Thread.sleep(5000);
		assertTrue(selenium.isTextPresent("Get A Ride At Your Price"));
		selenium.select("CSS=#passengers", "4"); //can also use value as  "value=4"
		selenium.click("CSS=#datepicker");
		selenium.click("//a[text()='16']");
		selenium.select("CSS=#hour", "2 AM");
		selenium.select("CSS=#minute", ":15");
		selenium.type("CSS=input[name='Airline']", "Virgin");
		selenium.select("CSS=#FromAirport", "SFO-San Francisco International");
		selenium.type("CSS=textarea[name='DestinationAddress']", "12 Geary Street, San Francisco, CA US");
		selenium.click("CSS=#pricecheck");
		Thread.sleep(10000);
		assertTrue(selenium.isTextPresent("Average Price"));
	}
}
