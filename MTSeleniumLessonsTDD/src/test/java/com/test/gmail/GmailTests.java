package com.test.gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.my_global_library.Base;

public class GmailTests extends Base {

	@Test(priority = 0, enabled = true)
	public void sendingEmail() {
		// lunching website
		driver.get("https://accounts.google.com");
		
		// enter email address
		myLibrary.enterTextField(By.id("identifierId"), "baku.2012.2015@gmail.com");
		
		// click next button
		myLibrary.clickBtn(By.xpath("//span[contains(text(),'Next')]"));
		
		// enter password
		myLibrary.enterTextField(By.name("password"), "BaKu.6952");
		myLibrary.customWait(1);
		
		// click next button
		myLibrary.clickBtn(By.cssSelector("#passwordNext > content > span"));
		
		// choose the gmail from given options
		myLibrary.clickBtn(By.xpath("//a[@class='WaidBe']"));

		/*
		 * int size = driver.findElements(By.tagName("iframe")).size();
		 * System.out.println(size);
		 */

		// click compose button
		myLibrary.clickBtn(By.xpath("//div[text()='COMPOSE']"));
		
		// enter email address in "to" text box
		myLibrary.enterTextField(By.name("to"), "mahammad.abasquliyev@gmail.com");
		
		// enter text to subject box
		myLibrary.enterTextField(By.name("subjectbox"), "Test");

		// enter text to the message body
		myLibrary.enterTextField(By.xpath("//div[@aria-label='Message Body']"), "This is for testing purpose");
		myLibrary.customWait(2);
		
		// click send button
		myLibrary.clickBtn(By.xpath("//div[@aria-label='Send ‪(Ctrl-Enter)‬']"));

		// get and print confirmation text
		WebElement sentEmail = driver.findElement(By.
		cssSelector("body > div:nth-child(17) > div.nH > div > div.nH.w-asV.aiw > div:nth-child(5) > div.no > div > div:nth-child(3) > div > div > div.vh")); 
		String sentEmailText = sentEmail.getText();
		System.out.println("Email verification: " + sentEmailText);
		
		// Sign out from page 
		myLibrary.clickBtn(By.xpath("//span[@class='gb_8a gbii']"));
		myLibrary.clickBtn(By.id("gb_71"));
	}

	@Test(priority = 1, enabled = true)
	public void receivingEmail() {

		// lunching website
		driver.get("https://accounts.google.com");
		
		// enter email address
		myLibrary.enterTextField(By.id("identifierId"), "baku.2012.2015@gmail.com");
		
		// click next button
		myLibrary.clickBtn(By.xpath("//span[contains(text(),'Next')]"));
		
		// enter password
		myLibrary.enterTextField(By.name("password"), "BaKu.6952");
		myLibrary.customWait(1);
		
		// click next button
		myLibrary.clickBtn(By.cssSelector("#passwordNext > content > span"));
		
		// choose the gmail from given options
		myLibrary.clickBtn(By.xpath("//a[@class='WaidBe']"));
		myLibrary.customWait(1);
		
		// click inbox button
		myLibrary.clickBtn(By.xpath("//a[@target='_top' and @class='J-Ke n0'and@tabindex='0']"));
		myLibrary.customWait(2);
		
		//myLibrary.clickButton(By.xpath("//div[@id=':6n'and@class='yW']"));

		// Sign out from page 
		myLibrary.clickBtn(By.xpath("//span[@class='gb_8a gbii']"));
		myLibrary.clickBtn(By.id("gb_71"));
	}

}
