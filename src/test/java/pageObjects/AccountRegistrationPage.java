package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	
	//Constructor
	public AccountRegistrationPage(WebDriver driver)
 	{
	 super(driver);
 	}
 
 //Locators
 @FindBy(xpath="//input[@id='input-firstname']")
 WebElement txtfirstname;
 @FindBy(xpath="//input[@id='input-lastname']")
 WebElement txtlastname;
 @FindBy(xpath="//input[@id='input-email']")
 WebElement txtemail;
 @FindBy(xpath="//input[@id='input-telephone']")
 WebElement txttelephone;
 @FindBy(xpath="//input[@id='input-password']")
 WebElement txtpassword;
 @FindBy(xpath="//input[@id='input-confirm']")
 WebElement txtconfpassword;
 @FindBy(xpath="//input[@name='agree']")
 WebElement chkagree;
 @FindBy(xpath="//input[@value='Continue']")
 WebElement btnContinue;
 @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
 WebElement msgsuccess;
 
 //Action methods
 
 public void txtfirstname(String firstname)
 {
	 txtfirstname.sendKeys(firstname);
 }
 
 public void txtlastname(String lastname)
 {
	 txtlastname.sendKeys(lastname);
 }
 
 public void txtemail(String email)
 {
	 txtemail.sendKeys(email);
 }
 public void txttelephone(String phonenum)
 {
	 txttelephone.sendKeys(phonenum);
 }
 
 public void txtpassword(String pwd)
 {
	 txtpassword.sendKeys(pwd);
 }
 public void txtconfpassword(String confpwd)
 {
	 txtconfpassword.sendKeys(confpwd);
 }
 
 public void chkagree()
 {
	 chkagree.click();
 } 
 public void btnContinue()
 {
	 btnContinue.click();
 } 
 public String msgsuccess()
 {
	 try
	 {
		 return(msgsuccess.getText());
	 }catch(Exception e)
	 {
		 return(e.getMessage());
	 }
 }
 
 
}
