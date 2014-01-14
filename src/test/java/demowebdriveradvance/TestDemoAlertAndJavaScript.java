package demowebdriveradvance;

import static com.sav.ra.framework.PropertyManager.getProperty;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.sav.ra.framework.WebDriverTest;


public class TestDemoAlertAndJavaScript extends WebDriverTest {
	@Test
	public void testRegistrationPage1() throws Exception {
			
		verifyAndClickElement(driver, By.linkText("Login"));
		waitForElementPresent(driver, By.id("user_login"), 5);
		
		type(driver, By.id("user_login"), "david");
		String script="alert('Please Enter password')";
		//to execute js code, typecast the driver object in to (JavascriptExecutor) and then invoke the executeScript 
		//method code
		JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript(script);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Please Enter password"); // to read the message of alert/confirm dialog
		Thread.sleep(10000);
		alert.accept(); // equivalent of user clicking OK button on an alert dialog box
		
		script = "window.open('https://twitter.com','twitterwindow')";
		jsExecutor.executeScript(script);
		
		driver.switchTo().window("twitterwindow"); // to switch focus to new window
		Thread.sleep(20000);
		
//		alert.dismiss(); // equivalent of user clicking Cancel button on a confirm dialog box
		
	}
}
