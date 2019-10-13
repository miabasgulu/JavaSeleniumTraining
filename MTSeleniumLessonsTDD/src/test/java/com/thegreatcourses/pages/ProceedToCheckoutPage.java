package com.thegreatcourses.pages;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.my_global_library.Base;

public class ProceedToCheckoutPage extends Base{
	
	public ProceedToCheckoutPage wait_untilPageLoadComplete() {
		WebElement viewChartElem = myLibrary.fluentWait(By.xpath("//button[@class='button btn-cart']"));
		assertNotNull(viewChartElem);
		return this;
	}
	
	public ProceedToCheckoutPage click_toProceedCheckoutBtn() {
		myLibrary.clickBtn(By.xpath("//button[@title='Proceed To Checkout']"));
		 return this;
	}

}
