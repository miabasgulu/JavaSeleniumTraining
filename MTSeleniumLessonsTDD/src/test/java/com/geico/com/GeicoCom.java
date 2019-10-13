package com.geico.com;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.my_global_library.Base;

public class GeicoCom extends Base {
	
	@Test
	public void geicoComTest() {
		driver.get("https://www.geico.com/");
		driver.findElement(By.xpath("//input[@placeholder='ZIP Code'and@id='zip']")).sendKeys("22031");
		
	}

}
