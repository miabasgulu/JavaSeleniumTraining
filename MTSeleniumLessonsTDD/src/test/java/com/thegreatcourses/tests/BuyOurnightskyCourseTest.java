package com.thegreatcourses.tests;

import org.testng.annotations.Test;

import com.my_global_library.Base;
import com.thegreatcourses.pages.CategoryCiencePage;
import com.thegreatcourses.pages.ChekoutProgressPage;
import com.thegreatcourses.pages.ChooseProductFormatPage;
import com.thegreatcourses.pages.HomePage;
import com.thegreatcourses.pages.ProceedToCheckoutPage;

public class BuyOurnightskyCourseTest extends Base{
	
	HomePage myHomePage = new HomePage();
	CategoryCiencePage sceiceCateg = new CategoryCiencePage();
	ChooseProductFormatPage chooseProdForPage = new ChooseProductFormatPage();
	ProceedToCheckoutPage proseedPage = new ProceedToCheckoutPage();
	ChekoutProgressPage chekoutpage = new ChekoutProgressPage();
	
	@Test
	public void Test1() {
		System.out.println("Go to 'thegreatecourses' website");
		myHomePage.goto_ThegreatCourses_Website();
		
		System.out.println("Click on Science category");
		myHomePage.click_ScienceCategory();
		
		System.out.println("Wait until Category Science page load complete");
		sceiceCateg.wait_untilPageloadComplite();
		
		System.out.println("Select 'Our Night Sky' course");
		sceiceCateg.select_A_Corse("Our Night Sky");
		
		System.out.println("Wait until Product Format Page load complete");
		chooseProdForPage.wait_untilPageloadComplete();
		
		System.out.println("Select video download radio button");
		chooseProdForPage.select_videoDownloadRadioBtn();
		
		System.out.println("Click add to cart button");
		chooseProdForPage.click_addToCart();
		
		System.out.println("Wait until Proseed Chekout Page load complete");
		proseedPage.wait_untilPageLoadComplete();
		
		System.out.println("Click to proseed chek out button");
		proseedPage.click_toProceedCheckoutBtn();
		
		System.out.println("Enter new email address");
		String uniqueTime = myLibrary.getCurrentTime();
		String email = "test" + uniqueTime + "@gmail.com";
		chekoutpage.enter_newEmailAddress(email);

	}
	
	//@Test
	public void Test2() {
		System.out.println("Go to 'thegreatecourses' website");
		myHomePage.goto_ThegreatCourses_Website();
		System.out.println("Click on Science category");
		myHomePage.click_ScienceCategory();
		System.out.println("Wait until Category Science page load complete");
		sceiceCateg.wait_untilPageloadComplite();
		System.out.println("Select 'The Aging Brain' course");
		sceiceCateg.select_A_Corse("The Aging Brain");
	}

}
