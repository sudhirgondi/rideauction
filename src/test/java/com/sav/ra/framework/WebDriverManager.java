package com.sav.ra.framework;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.sav.ra.framework.PropertyManager.getProperty;

public class WebDriverManager {

	
	public static WebDriver getDriver() {
		try {

			String browser = PropertyManager.getProperty("browser");
			WebDriver driver = null;
			if ("firefox".equalsIgnoreCase(browser)) {
				driver = new FirefoxDriver();
			} else if ("ie".equalsIgnoreCase(browser)) {
				
				File file = new File(getProperty("InternetExplorerServerPath"));
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

				DesiredCapabilities capabilities = DesiredCapabilities. internetExplorer();
				capabilities.setCapability(" ignoreZoomSetting", true);
				capabilities.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				 driver =  new InternetExplorerDriver( capabilities);
				
			} else if ("chrome".equalsIgnoreCase(browser)) {
				//System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
				driver = new ChromeDriver();
			} else if("android".equalsIgnoreCase(browser)){
				driver = new AndroidDriver();
			}else
			{
				driver = new HtmlUnitDriver();
			}

	
			return driver;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
