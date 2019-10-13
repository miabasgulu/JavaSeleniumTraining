package com.homework.selenium.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HomeworkForWeek4 {
	
	WebDriver driver;

	@Test
	public void outomateMortgageWebsite() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("http://mortgagecalculator.org/");
		driver.manage().window().maximize();
		String printTitle = driver.getTitle();
		System.out.println("Title is: " + printTitle);
		
		WebElement homeValueElement = driver.findElement(By.id("homeval"));
		homeValueElement.clear();
		homeValueElement.sendKeys("400000");
		
		WebElement downPaymentElement = driver.findElement(By.id("downpayment"));
		downPaymentElement.clear();
		downPaymentElement.sendKeys("20000");
		
		WebElement chooseDollarAmount = driver.findElement(By.cssSelector("#calc > form > section > section.content-area > div > div.content-right.main-block > div > div > div > div.cal-content > div > div.calcu-block > div:nth-child(2) > span > label:nth-child(1) > input[type=\"radio\"]"));
		chooseDollarAmount.click();
		
		WebElement interestRateElement = driver.findElement(By.id("intrstsrate"));
		interestRateElement.sendKeys("4.5");
		
		WebElement loanTermElement = driver.findElement(By.id("loanterm"));
		loanTermElement.clear();
		loanTermElement.sendKeys("25");
		
		WebElement startMonthDropdown = driver.findElement(By.name("param[start_month]"));
		Select startMonthElement = new Select(startMonthDropdown);
		startMonthElement.selectByVisibleText("Apr");
		
		WebElement startYearElement = driver.findElement(By.id("start_year"));
		startYearElement.sendKeys("2020");
		
		WebElement preportyTaxElement = driver.findElement(By.id("pptytax"));
		preportyTaxElement.sendKeys("2000");
		
		WebElement pmiElement = driver.findElement(By.id("pmi"));
		pmiElement.sendKeys("0.4");
		
		WebElement homeInsuranceElement = driver.findElement(By.id("hoi"));
		homeInsuranceElement.sendKeys("700");
		
		WebElement monthlyHoaElement = driver.findElement(By.id("hoa"));
		monthlyHoaElement.sendKeys("150");
		
		WebElement clickCalculateBtn = driver.findElement(By.cssSelector("#calc > form > section > section.content-area > div > div.content-right.main-block > div > div > div > div.cal-content > div > div.calcu-block > div.rw-box.button > input"));
		clickCalculateBtn.click();
		
		//WebElement findClassElem = driver.findElement(By.className("repayment-block"));
		/*List<WebElement> rowClassElem = findClassElem.findElements(By.className("rw-box"));
		WebElement rowClasselem1 = rowClassElem.get(0);
		WebElement leftCellElem = rowClasselem1.findElement(By.className("left-cell"));
*/		//WebElement monthlyPayment = findClassElem.findElement(By.tagName("h3"));
		//String actualMonthlyPayment = monthlyPayment.getText();
		//System.out.println("Monthly payment: " + actualMonthlyPayment);
		
		
		WebElement monthlyPayment = driver.findElement(By.tagName("h3"));
		String actualMonthlyPayment = monthlyPayment.getText();
		System.out.println("Monthly payment: " + actualMonthlyPayment);

		
		Thread.sleep(3 * 1000);
		//driver.close();
		//driver.quit();
		
	}

}
