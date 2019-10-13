package com.homework.selenium.automation;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeworkWeek4Second {

	static WebDriver driver;

	@BeforeMethod
	public void beforeAllMethod() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://mortgagecalculator.org");
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterAllMethod() throws Exception {
		Thread.sleep(3 * 1000);
		driver.close();
		driver.quit();

	}

	@Test
	public void automateMortgageCalculate() throws Exception {
		element(By.id("homeval"), "250000");
		element(By.id("downpayment"), "20000");
		selectBtn(By.xpath("//*[@id=\"calc\"]/form/section/section[2]/div/div/div[1]/div/div/div[3]/div[1]/div[1]/div[2]/span/label[1]/input"));
		element(By.id("intrstsrate"), "4");
		element(By.id("loanterm"), "30");
		dropDown(By.name("param[start_month]"), "May");
		element(By.id("start_year"), "2020");
		element(By.id("pptytax"), "2500");
		element(By.id("pmi"), "0.5");
		element(By.id("hoi"), "1000");
		//element(By.id("hoa"), "100");
		selectBtn(By.xpath("//*[@id=\"calc\"]/form/section/section[2]/div/div/div[1]/div/div/div[3]/div[1]/div[1]/div[12]/input"));
		Thread.sleep(5 * 1000);
		
		WebElement selectClassElement = driver.findElement(By.className("repayment-block"));
		List<WebElement> selectRowClass = selectClassElement.findElements(By.className("rw-box"));
		WebElement rowClassElement = selectRowClass.get(0);
		WebElement leftCell = rowClassElement.findElement(By.className("left-cell"));
		WebElement monthlyPayment = leftCell.findElement(By.tagName("h3"));
		String actualMonthlyPay = monthlyPayment.getText();
		System.out.println("Monthly payment: " + actualMonthlyPay);
		
	}
	public static void element(By by, String enterValue) {
		WebElement boxElement = driver.findElement(by);
		boxElement.clear();
		boxElement.sendKeys(enterValue);
	}
		public static void selectBtn(By by) {
		WebElement selectBtnElement = driver.findElement(by);
		selectBtnElement.click();
	}
		public static void dropDown(By by, String valueOption) {
		WebElement dropDawnElement = driver.findElement(by);
		Select startDate = new Select(dropDawnElement);
		startDate.selectByVisibleText(valueOption);
		
		}

	
}
