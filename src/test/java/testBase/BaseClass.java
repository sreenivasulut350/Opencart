package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger log;
	public Properties p;
	@BeforeClass(groups= {"Regression","Sanity","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(@Optional("Windows") String os, @Optional("chrome") String br) throws IOException
	{
		//Loading config.properties file		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		
		// To load the log in log file
		log = LogManager.getLogger(this.getClass());
		
		//executing the scripts via selenium grid
		
		if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			//os selection
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}else if (os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No Matching os");
				return;
			}
			
			//Browser selection
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox": cap.setBrowserName("firefox"); break;
			default: System.out.println("No browser find"); return;
			}
			
			driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), cap);
		}
		
		if (p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		default : System.out.println("Invalid browser");return;
		}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appurl")); //reading url from properties file
		driver.manage().window().maximize();
		 
		
	}
	
	@AfterClass(groups= {"Regression","Sanity","Master","Datadriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()	
	{		
		return( RandomStringUtils.secure().nextAlphabetic(8));		
	}
	
	public String randomNumber()	
	{		
		return( RandomStringUtils.secure().nextNumeric(10));		
	}
		
	public String randomAlpaNumber()	
	{		
		return( RandomStringUtils.secure().nextAlphanumeric(8));		
	}
	
	public String captureScreenshot(String tname)
	{
		 String timestap= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		 File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		 
		 String targetFilepath =System.getProperty("user.dir") + "\\screenshots\\"+tname+"_"+timestap+".png";
		 File targetFile =new File(targetFilepath);
		 sourceFile.renameTo(targetFile);
		 return targetFilepath;
	}

}
