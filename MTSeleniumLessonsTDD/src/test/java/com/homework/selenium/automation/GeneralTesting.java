package com.homework.selenium.automation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GeneralTesting {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", "src/test/resources/browser_drivers/geckodriver.exe");  
	DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
	driver = new InternetExplorerDriver();
    
    //baseUrl = "https://www.katalon.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSearchRose() throws Exception {
    driver.get("https://www.google.com/");
    driver.findElement(By.id("lst-ib")).click();
    //driver.findElement(By.xpath("//div[@id='sbse0']/div/span")).click();
    driver.findElement(By.id("lst-ib")).clear();
    driver.findElement(By.id("lst-ib")).sendKeys("rose");
    driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
    Thread.sleep(5 * 1000);
    
    driver.findElement(By.id("uid_dimg_6")).click();
    try {
      assertEquals(driver.findElement(By.linkText("unsplash.com")).getText(), "unsplash.com");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals(driver.findElement(By.linkText("unsplash.com")).getText(), "unsplash.com");
    driver.findElement(By.xpath("//div[@id='irc_cc']/div[2]/div[3]/div/div/table/tbody/tr/td/a/span[2]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

public boolean isAcceptNextAlert() {
	return acceptNextAlert;
}

public void setAcceptNextAlert(boolean acceptNextAlert) {
	this.acceptNextAlert = acceptNextAlert;
}
}
