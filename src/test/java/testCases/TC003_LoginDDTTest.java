package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginHomepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid---login success--test passed--logout
  Data is valid --login unsuccessful--test failed
  
  Data is invalid--login success--test failed--logout
  Data is invalid--login unsuccessful--test passed
 
 */
public class TC003_LoginDDTTest extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		log.info("****Started execution of TC003_LoginDDTTest***");
		
		try {
		//Home page
		HomePage hp = new HomePage(driver);
		hp.clkmyaccount();
		hp.clklogin();
		
		//login page
		LoginHomepage log = new LoginHomepage(driver);
		log.txtemail(email);
		log.txtpassword(pwd);
		log.clklogin();
		
		//MyAccount page
		MyAccountPage map = new MyAccountPage(driver);		
		 boolean targetpage=map.isMyAccountPageExist();
		 
		 if (exp.equalsIgnoreCase("Valid"))
		 {
			 if(targetpage==true)
			 {
				 map.clklogout();
				 Assert.assertTrue(true);
			 }else
			 {
				 Assert.assertTrue(false);
			 }
		 }
		 if(exp.equalsIgnoreCase("Invalid"))
		 {
			 if(targetpage==true)
			 {
				 map.clklogout();
				 Assert.assertTrue(false);
			 }else
			 {
				 Assert.assertTrue(true);
			 } 
		 }
		}catch(Exception e)
		{
			Assert.fail();
		}
		
		log.info("****Ended execution of TC003_LoginDDTTest***");
	}
}
