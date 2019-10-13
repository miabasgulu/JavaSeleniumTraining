import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.my_global_library.Base;

public class MealB extends Base{

	WebDriver driever;
	
	@Test
	public void mealBTest() {
		
		driver.get("https://www.mealb.com");
	}
}
