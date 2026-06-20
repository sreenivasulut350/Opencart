package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Collection of Locators
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgmyaccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnklogout;
	
	// Methods
	
	public boolean isMyAccountPageExist()
	{
		try {
		return msgmyaccount.isDisplayed();
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public void clklogout()
	{
		lnklogout.click();
	}

}
