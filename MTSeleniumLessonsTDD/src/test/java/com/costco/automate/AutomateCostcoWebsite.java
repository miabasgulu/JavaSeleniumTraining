package com.costco.automate;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class AutomateCostcoWebsite {
	public WebDriver driver;
	@BeforeMethod
	public void beforeEachTest() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterEachTest() throws Exception {

		Thread.sleep(3000);
		driver.close();
		driver.quit();

	}

	@Test(enabled = false)
	public void costcoWebsiteTest1() throws Exception {

		// Step 1: Go to costco.com home page
		driver.get("https://www.costco.com/");

		// Step 2: Verify page title
		String getTitle = driver.getTitle();
		System.out.println("Title: " + getTitle);

		// Step 3: Find and locate Search field enter "Xbox"
		WebElement searchBoxElement = driver.findElement(By.id("search-field"));
		Thread.sleep(3000);
		searchBoxElement.sendKeys("Xbox");

		// Step 4: Select 2nd option from the drop-down
		WebElement selectSeconItemElem = driver.findElement(By.cssSelector(
				"#formcatsearch > div.inner-addon.right-addon.flex-child > span > div > div > h6:nth-child(8)"));
		selectSeconItemElem.click();

		// Step 5: Locate the "item number" and print on the console
		WebElement itemNumber = driver.findElement(By.id("product-body-item-number"));
		String gettext = itemNumber.getText();
		System.out.println("Item number: " + gettext);

		// Select drop-down = new Select(selectDropdownElem);
		// dropdown.selectByVisibleText("Xbox");

	}

	@Test(enabled = true)
	public void costcoWebsiteTest2() throws InterruptedException, Exception {

		// Step 1: Go to costco.com home page
		driver.get("https://www.costco.com/");

		// Step 2: Hover-over "Find a Warehouse" drop-down menu on the top of the
		// web-site
		WebElement warehouseLocDropdownElem = driver.findElement(By.id("warehouse-locations-dropdown"));
		warehouseLocDropdownElem.click();

		// Step 3: Locate and enter zip code field "20151"
		Thread.sleep(1000);
		WebElement sendZipcode = driver.findElement(By.id("warehouse-search-field-desktop"));
		sendZipcode.sendKeys("20151");

		// Step 4: Locate and check the box for "Gas Station" & "Food Court"
		WebElement checkGasstationElem = driver.findElement(By.cssSelector(
				"#warehouse_locator_search-desktop > div.row > div:nth-child(1) > div:nth-child(1) > label"));
		checkGasstationElem.click();
		WebElement checkFoodcourtElem = driver.findElement(By.cssSelector(
				"#warehouse_locator_search-desktop > div.row > div:nth-child(1) > div:nth-child(3) > label"));
		checkFoodcourtElem.click();

		// Step 5: Locate and click on "Find a Warehouse" button
		WebElement clickFindAWarehouseBtn = driver
				.findElement(By.cssSelector("#warehouse_locator_search-desktop > input.btn.btn-primary"));
		clickFindAWarehouseBtn.click();

		// Step 6: Once there is result, take screenshot of the result page including
		// the map
		Thread.sleep(5000);
		driver.findElement(
				By.cssSelector("tr.warehouse:nth-child(7) > td:nth-child(2) > div:nth-child(1) > div:nth-child(1)"));
		String filePath = "C://Screenshot/map.png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(srcFile, new File(filePath));

		// Step 7: Print the screenshot location and file name on the console
		System.out.println("Screenshot Path: " + filePath);

		// Step 8: Locate the "Sterling" store and print the address and phone number on
		// the console
		WebElement sterlingDivElem = driver.findElement(
				By.cssSelector("tr.warehouse:nth-child(7) > td:nth-child(2) > div:nth-child(1) > div:nth-child(1)"));
		System.out.println("_______________________________________");
		System.out.println("Sterling text: ");
		System.out.println(sterlingDivElem.getText());

		// Step 9: Choose the "Sterling" Store by clicking on the "Sterling" link next
		// to the mileage info
		WebElement clickSterlingPartiallink = driver.findElement(By.partialLinkText("Sterling"));
		clickSterlingPartiallink.click();

		// Step 10: Locate and print the working hours section into console
		WebElement hoursDiv = driver.findElement(By.className("core"));
		System.out.println("________________________________________");
		System.out.println("Work hours:");
		System.out.println(hoursDiv.getText());

	}

}
