package demowebdriveradvance;

import static com.sav.ra.framework.PropertyManager.getProperty;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sav.ra.framework.WebDriverTest;

public class TestDemoStaticAssert extends WebDriverTest {

	@Test
	public void testStaticText() throws Exception {

		verifyAndClickElement(driver, By.xpath("//a[text()='How it works']"));
		// WebElement elm=getElement(driver,By.xpath( "/html/body/div[2]/h1"));
		// assertEquals(elm.getText(), "What is RideAuction.com?");
		//
		// WebElement elm1=getElement(driver,By.xpath(
		// "/html/body/div[2]/h3[1]"));
		// assertEquals(elm.getText(), "How does it work?");
		//
		//
		// WebElement elm2=getElement(driver,By.xpath(
		// "html/body/div[2]/h3[2]"));
		// assertEquals(elm.getText(), "How does it work?");
		//
		String pgSource = driver.getPageSource(); // returns entire html code of
													// the page so that you
													// don't have to go back and
													// forth between browser and
													// Selenium to check for
													// several static text
													// strings
		Assert.assertTrue(pgSource.contains("What is RideAuction.com?"));
		Assert.assertTrue(pgSource.contains("COMPLETELY FREE OF CHARGE!"));
		Assert.assertTrue(pgSource.contains("Place Your Bid"));
	}
}
