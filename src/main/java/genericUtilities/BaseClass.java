package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.AllProductsPage;
import objectRepository.LoginPage;

/**
 * This class consists of basic configuration annotations of TestNG
 * @author Chaitra M
 *
 */
public class BaseClass {
	
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public SeleniumUtility sUtil = new SeleniumUtility();
	
	public WebDriver driver;
	
	/*Used For listeners*/
	public static WebDriver sDriver; //null

	@BeforeSuite(groups = {"Smoke","Regression"})
	public void bsConfig()
	{
		System.out.println("------ Database Connection successful ------");
	}
	
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(alwaysRun = true)
	public void bcConfig(/*String BROWSER*/) throws IOException
	{
		/*For Cross Browser Execution*/
//		if(BROWSER.equalsIgnoreCase("Edge"))
//		{
//			driver = new EdgeDriver();
//		}
//		else if(BROWSER.equalsIgnoreCase("Chrome"))
//		{
//			driver = new ChromeDriver();
//		}
//		else
//		{
//			driver = new EdgeDriver();
//		}
		
		
		
		String URL = pUtil.readDataFromPropertyFile("url");
		
		driver = new FirefoxDriver();
		
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);
		
		driver.get(URL);
		
		System.out.println("------ Browser Launch successful ------");
		
		/*Used for listeners*/
		sDriver = driver;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		System.out.println("------ Login to Application successful ------");
	}
	
	@AfterMethod(alwaysRun = true)
	public void amConfig()
	{
		AllProductsPage app = new AllProductsPage(driver);
		app.logoutOfApp();
		
		System.out.println("------ Logout of Application successful ------");
	}
	
	//@AfterTest
	@AfterClass(alwaysRun = true)
	public void acConfig()
	{
		driver.quit();
		System.out.println("------ Browser Closure successful ------");
	}
	
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		System.out.println("------ Database Closure successful ------");
	}
}
