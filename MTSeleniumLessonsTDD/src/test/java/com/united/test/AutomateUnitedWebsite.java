package com.united.test;

import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.my_global_library.Base;

public class AutomateUnitedWebsite extends Base {

	@Test
	public void unitedTest() throws Exception {
		driver.get("https://www.united.com/ual/en/us/");
		String getUnitedWebsitetitle = driver.getTitle();
		System.out.println("UnitedWebsiteTitle is: " + getUnitedWebsitetitle);
		myLibrary.clickBtn(By.xpath("//li[@aria-controls='bookTravelFlight']"));
	
		myLibrary.clickBtn(By.id("SearchTypeMain_roundTrip"));
		myLibrary.enterTextField(By.id("Origin"), "was");
		myLibrary.clickBtn(By.xpath("//div[@data-code='IAD']"));
		myLibrary.enterTextField(By.id("Destination"), "bak");
		myLibrary.clickBtn(By.xpath("//div[@data-code='GYD']"));
		myLibrary.enterTextField(By.id("DepartDate"), "Aug 15, 2018");
		myLibrary.customWait(2);
		myLibrary.enterTextField(By.id("ReturnDate"), "Aug 20, 2018");
		// myLibrary.clickButton(By.cssSelector("#travelers-selector > span >
		// span.dropdown-trigger-text"));
		myLibrary.clickBtn(By.cssSelector("#travelers-selector > span > span.dropdown-trigger-text"));
		myLibrary.customWait(1);

		clearDefaultSelection();
		selectTravelors("Seniors (65+)", "2");
		selectTravelors("Infants on lap", "2");
		
		// myLibrary.clickButton(By.id("cabinType"));
		myLibrary.selectDropDown(By.id("cabinType"), "Business or First");

		// click search button
		myLibrary.clickBtn(By.id("flightBookingSubmit"));

		// Explicit Wait
		WebElement ticket = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("flight-result-list-revised")));
		myLibrary.highlightElement(ticket);
		assertNotNull(ticket);

	}

	///////////////// HELPER METHOD /////////////////

	// Clear all the default selections

	public static void clearDefaultSelection() {
		WebElement travelorsSelectElem = driver.findElement(By.id("travelers-select"));
		List<WebElement> liElems = travelorsSelectElem.findElements(By.tagName("li"));
		for (WebElement li : liElems) {
			List<WebElement> minusPlusIcons = li.findElements(By.tagName("a"));
			WebElement minusIcon = minusPlusIcons.get(0);

			String minusIconText = minusIcon.getAttribute("class");
			do {
				// myLibrary.highlightElement(minusIcon);
				minusIcon.click();
				minusIconText = minusIcon.getAttribute("class");
			} 
			while (!minusIconText.contains("disabled"));
		}

	}

	// Selecting traveler type

	public static void selectTravelors(String travelorType, String travelorNumber) {

		WebElement travelorsSelectElem = driver.findElement(By.id("travelers-select"));
		List<WebElement> liElems2 = travelorsSelectElem.findElements(By.tagName("li"));
		for (WebElement li : liElems2) {
			List<WebElement> minusPlusIcons = li.findElements(By.tagName("a"));
			WebElement plusIcon = minusPlusIcons.get(1);
			String TravelorsTypeText = li.findElement(By.tagName("label")).getText();
			if (TravelorsTypeText.contains(travelorType)) {
				int travelorNum = Integer.parseInt(travelorNumber);
				for(int i = 1; i <= travelorNum; i++) {
				plusIcon.click();
				}
			}
		}
	}

}
