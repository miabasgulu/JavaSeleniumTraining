package com.calculator.automation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserCommands {

	public static WebDriver driver;

	@BeforeMethod
	public void beforeEachTest() {
		// we are setting chromdriver.exe file to the system property
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		// Open a new Chrome browser
		driver = new ChromeDriver();
		// maximize the browser window
		driver.manage().window().maximize();
		// wait until pageload complete or time out
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// implicite way
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@AfterMethod
	public void afterEveryTest() throws Exception {
		Thread.sleep(3 * 1000);
		driver.close();
		driver.quit();
	}
	

	@Test(enabled = false)
	public void browserCommandsTests() throws Exception {

		// go to the website
		driver.get("http://.www.costco.com/");
		String websiteTitle = driver.getTitle();
		System.out.println("Title is: " + websiteTitle);
		Thread.sleep(1 * 1000);

		// navigate another website
		driver.navigate().to("http://walmart.com/");
		websiteTitle = driver.getTitle();
		System.out.println("Title is: " + websiteTitle);
		Thread.sleep(1 * 1000);

		// go back to the previous page
		driver.navigate().back();
		Thread.sleep(1 * 1000);

		// go forward to the page
		driver.navigate().forward();

		WebElement searchElement = driver.findElement(By.id("global-search-input"));
		searchElement.sendKeys("laptop");
		// option 1
		// searchElement.sendKeys(Keys.ENTER);

		// option 2
		WebElement clickSearchBtn = driver.findElement(By.name("elc-icon-search-nav"));
		clickSearchBtn.click();
		Thread.sleep(1 * 1000);
		
		// do refresh website page
		driver.navigate().refresh();

		String currentUrlString = driver.getCurrentUrl();
		System.out.println("Current URL is: '" + currentUrlString + "'");
		System.out.println("Current URL is: \"" + currentUrlString + "\"");

	}

	@Test(enabled = false)
	public void findAllLinks() throws Exception {
		driver.get("http://costco.com/");
		List<WebElement> totalLinks = driver.findElements(By.tagName("a"));
		int totalLinkNumber = totalLinks.size();
		Thread.sleep(1 * 1000);
		System.out.println("Total Link #: " + totalLinkNumber);
		
		int counter = 1;
		for(WebElement temp: totalLinks) {
			String singleLinkText = temp.getText();
			System.out.println(counter + ") '" + singleLinkText +  "'");
			counter++;
		}
			
	}
	
	@Test(enabled = false)
	public void testing_checkbox_radiobutton_elements() throws Exception {

		driver.get("http://www.coolfields.co.uk/2011/04/accessible-forms-checkboxes-and-radio-buttons/");
		// Scenario 1: // User wants to check the box and box is unchecked
		// handleCheckBoxRadioBtn(By.id("pizza1"), true);

		// Scenario 2: User wants to check the box and box is already
		// handleCheckBoxRadioBtn(By.id("pizza1"), true);

		// Scenario 3: User does not want to check the box and box is unchecked
		// handleCheckBoxRadioBtn(By.id("pizza1"), false);

		// Scenario 4: User does not want to check the box and box is already
		// checked
		handleCheckBoxRadioBtn(By.id("pizza1"), false);

		Thread.sleep(2 * 1000);

	} // ending method

	/**** THis is helper method *****/

	public static void handleCheckBoxRadioBtn(By by, boolean isUserWantsToCheckTheBox) {

		WebElement CheckBoxElem = driver.findElement(by);
		boolean isCheckBoxSelected = CheckBoxElem.isSelected();
		// Logic 1: // User wants to check the box and box is unchecked
		if (isUserWantsToCheckTheBox == true) {

			if (isCheckBoxSelected == false) {
				CheckBoxElem.click();
				System.out.println("Scenario 1 is executed ...");
			} else // Logic 2: User wants to check the box and box is already
					// checked
			{
				// do nothing
				System.out.println("Scenario 2 is executed ...");
			}
		} else // Logic 3: // User does not want to check the box and box is
		{
			// unchecked
			if (isCheckBoxSelected == false) {
				// do nothing
				System.out.println("Scenario 3 is executed ...");
			} else // Logic 4: User does not want to check the box and box is
					// already checked
			{
				CheckBoxElem.click();
				System.out.println("Scenario 4 is executed ...");
			}
		}
	}

	
	
	@Test(enabled = true)
	public void testing1() throws Exception {
		driver.get("https://www.usps.com/");

		WebElement quickToolsElem = driver.findElement(By.linkText("Quick Tools"));
		Actions action = new Actions(driver);
		action.moveToElement(quickToolsElem).build().perform();
		Thread.sleep(2 * 1000);
		
		String cssSelectorFindLocation = ".qt-nav > div:nth-child(3) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1) > p:nth-child(2)";
		WebElement findLocation = driver.findElement(By.cssSelector(cssSelectorFindLocation));
		findLocation.click();
		Thread.sleep(5 * 1000);
			
	}
	
	
} 

// ending class

/*
 * WebElement anchCheckBoxElem = driver.findElement(By.id("pizza2"));
 * anchCheckBoxElem.click(); Thread.sleep(2 * 1000);
 * 
 * WebElement extraCheckBoxElem = driver.findElement(By.id("pizza3"));
 * extraCheckBoxElem.click(); Thread.sleep(2 * 1000);
 */
