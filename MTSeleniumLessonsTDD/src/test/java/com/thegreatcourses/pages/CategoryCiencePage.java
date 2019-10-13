package com.thegreatcourses.pages;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.my_global_library.Base;

public class CategoryCiencePage extends Base {
	
	public CategoryCiencePage wait_untilPageloadComplite() {
		WebElement coursesElem = myLibrary.fluentWait(By.xpath("//p[@class='amount']"));
		assertNotNull(coursesElem);
		return this;
	}
	
	public CategoryCiencePage select_A_Corse(String courseNamePartialLink) {
		driver.findElement(By.partialLinkText(courseNamePartialLink)).click();
		return this;
	}

}
