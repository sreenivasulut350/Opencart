package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_Creationofuseraccount extends BaseClass {
		
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
		{
		
		log.info("****Started execution of TC001_Creationofuseraccount***");
		log.debug("****Started execution of TC001_Creationofuseraccount***");
		try {
		HomePage hp = new HomePage(driver);
		hp.clkmyaccount();
		hp.clkregister();
		AccountRegistrationPage ar =new AccountRegistrationPage(driver);
		ar.txtfirstname(randomString().toUpperCase());
		ar.txtlastname(randomString().toUpperCase());
		ar.txtemail(randomString()+"@gmail.com");
		ar.txttelephone(randomNumber());
		String pwd=randomAlpaNumber();
		ar.txtpassword(pwd);
		ar.txtconfpassword(pwd);
		ar.chkagree();
		ar.btnContinue();
		log.debug("Current Page Title: " + driver.getTitle());
		log.debug("Current Page Title: " + driver.getCurrentUrl());
		Assert.assertEquals(ar.msgsuccess(), "Your Account Has Been Created!");
		}catch(Exception e)
		{
			Assert.fail();
		}
		log.info("****Ended execution of TC001_Creationofuseraccount***");
      
	
	}
	
	
	
	
	

}
