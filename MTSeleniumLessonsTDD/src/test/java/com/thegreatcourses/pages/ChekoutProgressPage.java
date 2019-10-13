package com.thegreatcourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.my_global_library.Base;

public class ChekoutProgressPage extends Base{
	
	public ChekoutProgressPage enter_newEmailAddress(String emailString) {
		WebElement emailBox = driver.findElement(By.id("login-email"));
		emailBox.sendKeys(emailString);
		return this;
	}

}
