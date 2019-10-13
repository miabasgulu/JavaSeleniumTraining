package com.calculator.automation;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CalculatorTests{
	public WebDriver driver;

	@BeforeClass
	public void setUp() {
		// we are setting chromdriver.exe file to the system property
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
	}

	@AfterClass
	public void cleanUp() throws Exception {
		if (driver != null)
			;
		driver.quit();
	}

	@BeforeMethod
	public void beforeEachMethod() {
		driver = new ChromeDriver(); // this line of code is opening a new Chrome browser
		driver.manage().window().maximize(); // maximize the browser window
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); // wait until pageload complete or time out
																			// after 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // implicite way
		driver.get("http://mortgagecalculator.net/"); // go to URL
	}

	@AfterMethod
	public void afterEachMethod() throws InterruptedException {
		Thread.sleep(5 * 1000);
		driver.close(); // close the current open browser
		driver.quit(); // kills the driver object
	}

	@Test (enabled = true)
	public void mortgageCalculatorTestsForUsDollar() {
		driver.get("http://www.mortgagecalculator.net/");
		try {
		String websiteTitle = driver.getTitle();
		System.out.println("title: " + websiteTitle);

		// Step 1: locating currency type
		WebElement currencyElement = driver.findElement(By.id("currency"));
		Select currencyDropdown = new Select(currencyElement);
		currencyDropdown.selectByVisibleText("$");

		// Step 2: locating amount text field on the website
		WebElement amountElement = driver.findElement(By.id("amount"));
		Thread.sleep(1 * 1000);
		amountElement.clear(); // clear the text field
		Thread.sleep(1 * 1000);
		amountElement.sendKeys("500000"); // enters the data to the text field

		// Step 3: locating interest rate text field on the website
		WebElement interestRateElement = driver.findElement(By.name("rate"));
		Thread.sleep(1 * 1000);
		interestRateElement.clear();
		Thread.sleep(1 * 1000);
		interestRateElement.sendKeys("4.3");

		// Step 4: locating term year text field on the website
		WebElement termYearElement = driver.findElement(By.cssSelector("#amortizationYears"));
		termYearElement.clear();
		termYearElement.sendKeys("29");

		// Step 5: locating term month text field on the website
		WebElement termMonthElement = driver.findElement(By.xpath("//*[@id=\'amortizationMonths\']"));
		termMonthElement.clear();
		termMonthElement.sendKeys("6");

		// Step 6: locating start month dropdown element
		WebElement startMonthDropDownElement = driver.findElement(By.id("startMonth"));
		Select monthlyDropDownElement = new Select(startMonthDropDownElement);
		monthlyDropDownElement.selectByVisibleText("7");

		// Step 7: locating start year dropdown element
		WebElement startYearDropDownElement = driver.findElement(By.id("startYear"));
		Select yearlyDropDownElement = new Select(startYearDropDownElement);
		yearlyDropDownElement.selectByValue("2020");

		// Step 8: locating interest term year field on the website
		WebElement interestTermYearElement = driver.findElement(By.id("interestTermYears"));
		interestTermYearElement.clear();
		interestTermYearElement.sendKeys("10");

		// Step 9: locating interest term month field on the website
		WebElement interestTermMonthElement = driver.findElement(By.id("interestTermMonths"));
		interestTermMonthElement.clear();
		interestTermMonthElement.sendKeys("8");

		// Step 10: locating payment option dropdown element
		WebElement paymentOptionDropDownElement = driver.findElement(By.name("paymentMode"));
		Select paymentDropDown = new Select(paymentOptionDropDownElement);
		paymentDropDown.selectByVisibleText("Monthly");

		// Step 11: locating interest type dropdown element
		WebElement interestTypeDropDownElement = driver.findElement(By.id("interestType"));
		Select interestTypeDropDown = new Select(interestTypeDropDownElement);
		interestTypeDropDown.selectByVisibleText("Fixed");

		// Step 12: locating calculating button
		WebElement buttonElement = driver.findElement(By.id("button"));
		buttonElement.click(); // clicking on a button
		Thread.sleep(6 * 1000);

		// Step 13: locating the result and verify
		WebElement summeryMonthlyElement = driver.findElement(By.id("summaryMonthly"));
		String actualResult = summeryMonthlyElement.getAttribute("value");
		System.out.println("Actual result for monthly payment: " + actualResult);

		assertEquals(actualResult, "$2,494.98");
		}catch(Exception e) {
			System.out.println("error: " + e.getStackTrace());
		}

	}

	@Test (enabled = false)
	public void mortgageCalculatorTestsForEuro() throws InterruptedException {

		String websiteTitle = driver.getTitle();
		System.out.println("title: " + websiteTitle);

		// Step 1: locating currency type
		WebElement currencyElement = driver.findElement(By.id("currency"));
		Select currencyDropdown = new Select(currencyElement);
		currencyDropdown.selectByVisibleText("€");

		// Step 2: locating amount text field on the website
		WebElement amountElement = driver.findElement(By.id("amount"));
		Thread.sleep(1 * 1000);
		amountElement.clear(); // clear the text field
		Thread.sleep(1 * 1000);
		amountElement.sendKeys("500000"); // enters the data to the text field

		// Step 3: locating interest rate text field on the website
		WebElement interestRateElement = driver.findElement(By.name("rate"));
		Thread.sleep(1 * 1000);
		interestRateElement.clear();
		Thread.sleep(1 * 1000);
		interestRateElement.sendKeys("4.3");

		// Step 4: locating term year text field on the website
		WebElement termYearElement = driver.findElement(By.cssSelector("#amortizationYears"));
		termYearElement.clear();
		termYearElement.sendKeys("29");

		// Step 5: locating term month text field on the website
		WebElement termMonthElement = driver.findElement(By.xpath("//*[@id=\'amortizationMonths\']"));
		termMonthElement.clear();
		termMonthElement.sendKeys("6");

		// Step 6: locating start month dropdown element
		WebElement startMonthDropDownElement = driver.findElement(By.id("startMonth"));
		Select monthlyDropDownElement = new Select(startMonthDropDownElement);
		monthlyDropDownElement.selectByVisibleText("7");

		// Step 7: locating start year dropdown element
		WebElement startYearDropDownElement = driver.findElement(By.id("startYear"));
		Select yearlyDropDownElement = new Select(startYearDropDownElement);
		yearlyDropDownElement.selectByValue("2020");

		// Step 8: locating interest term year field on the website
		WebElement interestTermYearElement = driver.findElement(By.id("interestTermYears"));
		interestTermYearElement.clear();
		interestTermYearElement.sendKeys("10");

		// Step 9: locating interest term month field on the website
		WebElement interestTermMonthElement = driver.findElement(By.id("interestTermMonths"));
		interestTermMonthElement.clear();
		interestTermMonthElement.sendKeys("8");

		// Step 10: locating payment option dropdown element
		WebElement paymentOptionDropDownElement = driver.findElement(By.name("paymentMode"));
		Select paymentDropDown = new Select(paymentOptionDropDownElement);
		paymentDropDown.selectByVisibleText("Monthly");

		// Step 11: locating interest type dropdown element
		WebElement interestTypeDropDownElement = driver.findElement(By.id("interestType"));
		Select interestTypeDropDown = new Select(interestTypeDropDownElement);
		interestTypeDropDown.selectByVisibleText("Fixed");

		// Step 12: locating calculating button
		WebElement buttonElement = driver.findElement(By.id("button"));
		buttonElement.click(); // clicking on a button
		Thread.sleep(6 * 1000);

		// Step 13: locating the result and verify
		WebElement summeryMonthlyElement = driver.findElement(By.id("summaryMonthly"));
		String actualResult = summeryMonthlyElement.getAttribute("value");
		System.out.println("Actual result for monthly payment: " + actualResult);

		assertEquals(actualResult, "€2,494.98");

	}

}
