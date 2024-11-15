package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class AddProductToCartUsingDDT {

	public static void main(String[] args) throws InterruptedException, IOException {

		//Read the common Data from property file
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		//Read The Test Data From Excel File
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Sheet sh = wb.getSheet("Products");
		Row rw = sh.getRow(1);
		Cell cl = rw.getCell(2);
		String PRODUCTNAME = cl.getStringCellValue();
		
		// Step 1: Launch the browser
		WebDriver driver = new EdgeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Step 2: Load the URL
		driver.get(URL);

		// Step 3: Login to application
		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		driver.findElement(By.id("login-button")).click();

		// Step 4: Click on a product
		Thread.sleep(1000);
		System.out.println(PRODUCTNAME);
		
		/*The below line is an example for dynamic xpath*/
		driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();
		String productTitle = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).getText();

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
