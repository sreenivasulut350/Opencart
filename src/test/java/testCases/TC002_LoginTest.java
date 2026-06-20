package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginHomepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		log.info("****Started execution of TC002_LoginTest***");
		try {
		HomePage hp = new HomePage(driver);
		hp.clkmyaccount();
		hp.clklogin();
		LoginHomepage log = new LoginHomepage(driver);
		log.txtemail(p.getProperty("username"));
		log.txtpassword(p.getProperty("password"));
		log.clklogin();
		
		MyAccountPage map = new MyAccountPage(driver);		
		
		Assert.assertTrue(map.isMyAccountPageExist());
		}catch(Exception e)
		{
			Assert.fail();
		}
		log.info("****Ended execution of TC002_LoginTest***");
	}
}
