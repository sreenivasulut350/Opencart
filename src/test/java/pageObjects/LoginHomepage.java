package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginHomepage extends BasePage {
	
	public LoginHomepage(WebDriver driver)
	{
		super(driver);
	}
	
	//Collection of Locators
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtpassword;
	@FindBy(xpath="//input[@value='Login']") WebElement btnlogin;
	
	//methods 
	
	public void txtemail(String email)
	{
		txtemail.sendKeys(email);
	}
	public void txtpassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	public void clklogin()
	{
		btnlogin.click();
	}

}
