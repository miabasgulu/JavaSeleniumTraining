package com.thegreatcourses.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;

import com.my_global_library.Base;

public class HomePage extends Base{
	
	public HomePage goto_ThegreatCourses_Website() {
		driver.get("https://www.thegreatcourses.com/");
		String webSiteTitle = driver.getTitle();
		System.out.println("Website Titele: '" + webSiteTitle + "'");
		assertEquals(webSiteTitle, "Online Courses & Lectures for Home Study and Lifelong Learning");
		return this;
	}
	
	public HomePage click_ScienceCategory() {
		myLibrary.clickBtn(By.xpath("//img[@class='desktop-img'and@alt='Science']"));
		return this;
	}

}
