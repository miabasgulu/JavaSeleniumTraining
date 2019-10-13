package com.thegreatcourses.pages;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.my_global_library.Base;

public class ChooseProductFormatPage extends Base{
	
	public ChooseProductFormatPage wait_untilPageloadComplete() {
		WebElement coursNumber = myLibrary.fluentWait(By.xpath("//div[@class='right-part hide-below-768']"));
		assertNotNull(coursNumber);
		return this;
	}
	
	public ChooseProductFormatPage select_videoDownloadRadioBtn() {
		getRadioBtnFormatOptions().get(0).click();
		return this;
	}
	
	public ChooseProductFormatPage select_audioDownloadRadioBtn() {
		getRadioBtnFormatOptions().get(1).click();
		return this;
	}

	public ChooseProductFormatPage click_addToCart() {
		myLibrary.clickBtn(By.id("add-to-cart-btn"));
		return this;
	}
	
	
	
	
	
	private List<WebElement> getRadioBtnFormatOptions() {
		List<WebElement> radioOptions = new ArrayList<WebElement>();
		WebElement radioBtnGroup = driver.findElement(By.id("media-format-radio"));
		radioOptions = radioBtnGroup.findElements(By.tagName("label"));
		return radioOptions;
	}
	
	
}
