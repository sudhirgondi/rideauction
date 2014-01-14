package demowebdriveradvance;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDemoDragAndDrop  {
	
	@Test
	public void demoDragAndDrop() throws Exception{
		WebDriver driver=new FirefoxDriver();
		

		driver.get("http://yuilibrary.com/yui/docs/dd/constrained-drag.html");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement destElement=driver.findElement(By.id("dd-demo-1"));

		WebElement srcElement=driver.findElement(By.id("dd-demo-3"));
		Thread.sleep(5000);
		
		//drag and drop
		Assert.assertNotNull(destElement);
		Actions action=new Actions(driver);
		action.dragAndDrop(srcElement, destElement).perform();
		
		Thread.sleep(5000);
		driver.close();
		driver.quit();
		
		
		
	}

}
