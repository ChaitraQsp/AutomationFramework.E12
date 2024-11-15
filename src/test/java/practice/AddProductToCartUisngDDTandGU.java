package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.LoginPage;

public class AddProductToCartUisngDDTandGU {

	public static void main(String[] args) throws InterruptedException, IOException {

		//Create Object Of Utility Classes
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		SeleniumUtility sUtil = new SeleniumUtility();
		
		// Read the common Data from property file
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");

		// Read The Test Data From Excel File
		String PRODUCTNAME = eUtil.readDataFromExcel("Products", 1, 2);
		
		// Step 1: Launch the browser
		WebDriver driver = new EdgeDriver();

		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);

		// Step 2: Load the URL
		driver.get(URL);

		// Step 3: Login to application
//		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
//		driver.findElement(By.id("password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("login-button")).click();
		
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameEdt().sendKeys(USERNAME);
		lp.getPasswordEdt().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		
		

		// Step 4: Click on a product
		Thread.sleep(1000);
		System.out.println(PRODUCTNAME);

		/* The below line is an example for dynamic xpath */
		driver.findElement(By.xpath("//div[.='" + PRODUCTNAME + "']")).click();
		String productTitle = driver.findElement(By.xpath("//div[.='" + PRODUCTNAME + "']")).getText();

		// Step 5: Add Product to cart
		driver.findElement(By.id("add-to-cart")).click();

		// Step 6: Navigate to cart and validate the product
		driver.findElement(By.className("shopping_cart_link")).click();

		String ProductTitleInCart = driver.findElement(By.className("inventory_item_name")).getText();

		if (ProductTitleInCart.equalsIgnoreCase(productTitle)) {
			System.out.println("product successfully added to cart");
			System.out.println("PASS");
			System.out.println(ProductTitleInCart);
		} else {
			System.out.println("Product NOt added to cart -> FAIL");
		}

		// Step 7: LOgout of Application
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.linkText("Logout")).click();

		System.out.println("Logout successful");

	}

}
