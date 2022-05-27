package com.salesforceCucumber.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseMethods {
	protected static WebDriver driver;
	public static void launchAndOpenBrowser(String browser) {

		switch(browser.toLowerCase()) {

		case "chrome":		
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox": 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println("Invalid browser");
		
		}
		System.out.println("Launched browser"+browser);
//		report.logTestInfo(browser+"browser launched");
		driver.manage().window().maximize();	
	}
}
