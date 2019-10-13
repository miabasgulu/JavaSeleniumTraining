package com.my_global_library;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.google.common.base.Function;
import com.google.common.io.Files;

public class MyGlobalSeleniumLibrary {

	final static Logger logger = Logger.getLogger(MyGlobalSeleniumLibrary.class);

	private WebDriver driver;

	/***
	 * This is the Constructor
	 * 
	 * @param _driver
	 */
	public MyGlobalSeleniumLibrary(WebDriver _driver) {
		driver = _driver;
	}

	/***
	 * This method starts Chrome browser
	 * 
	 * @return driver
	 */
	public WebDriver startChromeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize(); // Maximize the browser window
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method starts Firefox browser
	 * 
	 * @return
	 */
	public WebDriver startFirefoxBrowser() {
		try {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize(); // Maximize the browser window
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method starts IE browser
	 * 
	 * @return
	 */
	public WebDriver startIEBrowser() {
		try {
			System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize(); // Maximize the browser window
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method enters text for given WebElement
	 * 
	 * @param by
	 * @param userInputString
	 */
	public void enterTextField(By by, String userInputString) {
		try {
			WebElement element = driver.findElement(by);
			element.sendKeys(userInputString);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method selects drop-down element for given drop-down option value
	 * 
	 * @param by
	 * @param optionValue
	 */
	public void selectDropDown(By by, String optionValue) {
		try {
			WebElement dropdownElem = driver.findElement(by);
			Select dropdown = new Select(dropdownElem);
			dropdown.selectByVisibleText(optionValue);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method clicks given WebElement
	 * 
	 * @param by
	 */
	public void clickBtn(By by) {
		try {
			WebElement btn = driver.findElement(by);
			btn.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void handleCheckBoxRadioBtn(By by, boolean isUserWantsToCheckTheBox) {
		try {
			WebElement CheckBoxElem = driver.findElement(by);
			boolean isCheckBoxSelected = CheckBoxElem.isSelected();
			// Logic 1: // User wants to check the box and box is unchecked
			if (isUserWantsToCheckTheBox == true) {

				if (isCheckBoxSelected == false) {
					CheckBoxElem.click();
					System.out.println("Scenario 1 is executed ...");
				} else // Logic 2: User wants to check the box and box is
						// already
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
				} else // Logic 4: User does not want to check the box and box
						// is
						// already checked
				{
					CheckBoxElem.click();
					System.out.println("Scenario 4 is executed ...");
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method stops the current thread for given second(s)
	 * 
	 * @param inSeconds
	 */
	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method moves mouse to the given element
	 * 
	 * @param inputElement
	 * @return
	 */
	public WebElement moveMouseToElement(WebElement inputElement) {
		WebElement element = null;
		try {
			Actions action = new Actions(driver);
			action.moveToElement(inputElement).build().perform();
			customWait(1);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return element;
	}

	/***
	 * This method highlights Web-site Elements using Javascript
	 * 
	 * @param by
	 * @return WebElement
	 */
	public WebElement highlightElement(By by) {
		WebElement element = null;
		try {
			for (int i = 0; i < 4; i++) {
				element = driver.findElement(by);
				WrapsDriver wrappedElement = (WrapsDriver) element;
				JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
				customWait(0.5);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: red; border: 2px solid yellow;");
				customWait(0.5);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return element;
	}

	/***
	 * This method highlights Web-site Elements using Javascript
	 * 
	 * @param elem
	 * @return WebElement
	 */
	public WebElement highlightElement(WebElement elem) {
		try {
			for (int i = 0; i < 3; i++) {
				WrapsDriver wrappedElement = (WrapsDriver) elem;
				JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
				customWait(0.5);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", elem,
						"color: red; border: 2px solid yellow;");
				customWait(0.5);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", elem, "");
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	/***
	 * This method dynamically waits for an element and handles Selenium element not
	 * found exceptions
	 * 
	 * @param by
	 * @return WebElement
	 */
	public WebElement fluentWait(final By by) {
		WebElement targetElem = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			targetElem = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					// highlightElement(by);
					return driver.findElement(by);
				}
			});
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		highlightElement(targetElem);
		return targetElem;
	}

	public String captureScreenshot(String screenshotFileName, String filePath) {

		String screenshotPath = null;
		String timestamp = getCurrentTime();
		try {
			if (!filePath.isEmpty()) {
				checkDirectory(filePath);
				screenshotPath = filePath + screenshotFileName + timestamp + ".png";
			} else {
				checkDirectory("target/screenshots/");
				screenshotPath = "target/screenshots/" + screenshotFileName + timestamp + ".png";
			}

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File(screenshotPath));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		System.out.println("Screenshot Captured: " + screenshotPath);
		return screenshotPath;
	}

	public String getCurrentTime() {
		String currentTime = null;
		try {
			Date date = new Date();
			String tempDate = new Timestamp(date.getTime()).toString();
			currentTime = tempDate.replace(" ", "_").replace(":", "_").replace(".", "_").replace("-", "_");
			// System.out.println("date string: '" +currentTime +"'");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return currentTime;
	}

	public void checkDirectory(String inputPath) {
		File file = new File(inputPath);
		String abPath = file.getAbsolutePath();
		File file2 = new File(abPath);
		if (!file2.exists()) {
			if (file2.mkdirs()) {
				System.out.println("folders created...");
			} else {
				System.out.println("folders not created...");
			}
		}
	}

	/***
	 * This method clicks on Hidden or Invisible WebElement using JavaScript
	 * 
	 * @param by
	 */
	public void clickHiddenWebElement(By by) {
		try {
			WebElement hiddenElem = driver.findElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("argument[0].click();", hiddenElem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method clicks on Hidden or Invisible WebElement using JavaScript
	 * 
	 * @param by
	 */
	public void clickHiddenWebElement(WebElement hiddenWebElement) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("argument[0].click();", hiddenWebElement);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public WebElement wait_VisibilityOfElementLocated(By by) {
		WebElement dynamicElement = null;
		dynamicElement = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(dynamicElement));
		return dynamicElement;
	}

	public WebElement wait_Presence_OfElementLocated(By by) {
		WebElement dynamicElement = null;
		dynamicElement = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return dynamicElement;
	}

	public void switchToIFrame(By by) {
		WebElement iframe = driver.findElement(by);
		driver.switchTo().frame(iframe);
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	/***
	 * This is the method to scroll the browser to the given webElement and make it
	 * to the center
	 * 
	 * @param element
	 */
	public void scrollToWebElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void scrollToWebElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", driver.findElement(by));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void scrollByOffset(String verticalPixel) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(0," + verticalPixel + ")");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public WebDriver startLocalBrowser(String browser) {
		switch (browser) {
		case "IE":
			driver = startIEBrowser();
			break;

		case "Chrome":
			driver = startChromeBrowser();
			break;

		case "Firefox":
			driver = startFirefoxBrowser();
			break;

		default:
			logger.info("User selected browser: '" + browser + "'" + ", Starting difault browser - IE");
			driver = startIEBrowser();
		}

		return driver;
	}

	/*
	 * public static void main(String[] args) { GlobalSeleniumLibrary gsl = new
	 * GlobalSeleniumLibrary(null); gsl.getCurrentTime();
	 * gsl.checkDirectory("c:/frank/screenshots/");
	 * 
	 * }
	 */

}
