package demowebdriveradvance;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.sav.ra.framework.WebDriverTest;

public class TestDemoSwitchFrame extends WebDriverTest {
	
	
	@Test
	public void testVideoLink()throws Exception{
		//if frame name available
		
		/* 1st option*/
		//driver.switchTo().frame("name_of_frame");
		/* 1st option END*/
		
		


		
		//driver.switchTo().frame(elm);
		/* 2nd option */
		//driver.switchTo().frame(1); //figure out frame index index by trial and error method
		//verifyAndClickElement(driver, By.id("follow-button"));
		
		
		/* 2nd option end */
		//3rd option
		WebElement elm=driver.findElement(By.id("twitter-widget-0"));
		
		driver.switchTo().frame(elm);
		
		verifyAndClickElement(driver, By.id("follow-button"));
		
		driver.switchTo().defaultContent(); //to switch back to parent(frame/window)
		
		//driver.switchTo().frame(driver.findElement(By.id("locator")));
		Thread.sleep(10000);
	}
	

}
