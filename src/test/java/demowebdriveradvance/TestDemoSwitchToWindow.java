package demowebdriveradvance;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.sav.ra.framework.WebDriverTest;

public class TestDemoSwitchToWindow extends WebDriverTest {

	@Test
	public void testTwiiterkLink() throws Exception{
		
		verifyAndClickElement(driver, By.linkText("reply"));
		
		Set<String> windowNamesSet = driver.getWindowHandles();
		
		String[] windowNames = windowNamesSet.toArray(new String[windowNamesSet.size()]);
		
		driver.switchTo().window(windowNames[1]);
		
		waitForElementPresent(driver, By.id("status"), 5);
		
		getElement(driver,By.id("status")).clear();
		type(driver, By.id("status"), "Hello Twitter");
	
		Thread.sleep(20000);
	}
}
