package com.thegreate.courses;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.my_global_library.Base;

public class BuyACourse extends Base {

	@Test
	public void buyScienceCourse() {

		// step 1: Go to web-site
		driver.get("https://www.thegreatcourses.com/");
		// locating science element
		myLibrary.clickBtn(By.xpath("//img[@class='desktop-img'and@alt='Science']"));
		// synchronize the page using fluent wait
		WebElement coursesElem = myLibrary.fluentWait(By.xpath("//p[@class='amount']"));
		assertNotNull(coursesElem);
		// locating our sky night element
		myLibrary.clickBtn(By.xpath("//img[@alt='Our Night Sky']"));
		// Synchronize the page using fluent wait
		WebElement coursNumber = myLibrary.fluentWait(By.xpath("//div[@class='right-part hide-below-768']"));
		assertNotNull(coursNumber);
		// click download video button
		myLibrary.clickBtn(By.xpath("//label[@for='15']"));
		// wait for 1 second
		myLibrary.customWait(1);
		// locate and click "add to chart" button
		myLibrary.clickBtn(By.id("add-to-cart-btn"));
		// synchronize page using fluent wait
		WebElement viewChartElem = myLibrary.fluentWait(By.xpath("//button[@class='button btn-cart']"));
		assertNotNull(viewChartElem);
		// click proceed to check out button
		myLibrary.clickBtn(By.xpath("//button[@title='Proceed To Checkout']"));
		// locating and enter unique email each time
		String uniqueTime = myLibrary.getCurrentTime();
		String email = "test" + uniqueTime + "@gmail.com";
		WebElement emailBox = driver.findElement(By.id("login-email"));
		emailBox.sendKeys(email);
		// locating and clicking "No, I'm a new customer" radio button
		myLibrary.clickBtn(By.xpath("//label[@for='no_have_pass']"));
		// locating and clicking "continue" button
		myLibrary.clickBtn(By.id("checkout-sigin"));
		// locating and entering first name
		myLibrary.enterTextField(By.id("billing:firstname"), "Mike");
		// locating and entering last name
		myLibrary.enterTextField(By.id("billing:lastname"), "Ismayil");
		// locating and entering company name
		myLibrary.enterTextField(By.id("billing:company"), "BaKu");
		//
		myLibrary.enterTextField(By.id("billing:street1"), "Persimmon dr");
		//
		myLibrary.enterTextField(By.id("billing:city"), "Fairfax");
		//
		myLibrary.selectDropDown(By.id("billing:region_id"), "Virginia");
		//
		myLibrary.enterTextField(By.id("billing:postcode"), "22031");
		//
		myLibrary.enterTextField(By.id("billing:customer_password"), "06952");
		//
		myLibrary.enterTextField(By.id("billing:confirm_password"), "06952");
		//
		myLibrary.clickBtn(By.cssSelector("#billing-buttons-container > button > span > span"));
		// switch to i-frame
		driver.switchTo().frame("vantiv-payframe");
		//
		myLibrary.enterTextField(By.id("accountNumber"), "4900000000000086");
		//
		myLibrary.selectDropDown(By.id("expMonth"), "May");
		//
		myLibrary.selectDropDown(By.id("expYear"), "2022");
		// switch default i-frame
		driver.switchTo().defaultContent();
		//
		myLibrary.clickBtn(By.cssSelector("#payment-buttons-container > button > span > span"));

		myLibrary.customWait(5);

	}
}
