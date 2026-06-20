package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkmyaccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkregister;
	@FindBy(linkText="Login")
	WebElement lnklogin;
	
	//Action Methods
	
	public void clkmyaccount()
	{
		lnkmyaccount.click();
	}
	
	public void clkregister()
	{
		lnkregister.click();
	}
	public void clklogin()
	{
		lnklogin.click();
	}

}
