package com.salesforceCucumber.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseMethods {
	protected static WebDriver driver;
	File capturedScreenshot;
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
	//capture screenshot 
		public void captureScreenshot(Scenario scenario) throws IOException {
			TakesScreenshot captureScreen = (TakesScreenshot)driver;
			capturedScreenshot = captureScreen.getScreenshotAs(OutputType.FILE);
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("dd-mmm-yy HH-mm-ss");
			String currentDateTime = sf.format(date);
			
			File destination = new File( "./Screenshots/passedSteps"+scenario.getName()+currentDateTime+".png");
			FileUtils.copyFile(capturedScreenshot,destination);
		}
	public void attachScreenshot(Scenario scenario) throws IOException {	
		captureScreenshot(scenario);
		byte[] screenshot = FileUtils.readFileToByteArray(capturedScreenshot);
		scenario.attach(screenshot,"image/png", "screenshot");
		
	}
}
