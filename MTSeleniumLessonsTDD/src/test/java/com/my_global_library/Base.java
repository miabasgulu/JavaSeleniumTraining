package com.my_global_library;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;

public class Base {

	final static Logger logger = Logger.getLogger(Base.class);
	public static WebDriver driver;
	public static MyGlobalSeleniumLibrary myLibrary;
	private static JavaPropertiesManager property;
	private static String browser;

	@BeforeClass
	public void beforeAllTests() {
		logger.info("Reading property file...");
		property = new JavaPropertiesManager("src/test/resources/config.properties");
		browser = property.readProperty("browserType");
	}

	@AfterClass
	public void afterAllTests() {
		logger.info("After all tests completed...");
	}

	@BeforeMethod
	public void beforeEachTest() {
		logger.info("Before each test started...");
		myLibrary = new MyGlobalSeleniumLibrary(driver);
		driver = myLibrary.startLocalBrowser(browser);
	}

	@AfterMethod
	public void afterEachtest(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			myLibrary.captureScreenshot(result.getTestName(), "target/images/screenshots");
		}
		logger.info("After each test completed...");
		logger.info("Waiting for 5 second...");
		myLibrary.customWait(5);
		if (driver != null) {
			driver.quit();
		}

	}
}
