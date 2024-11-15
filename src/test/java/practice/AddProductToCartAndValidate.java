package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class AddProductToCartAndValidate {
	
	public static void main(String[] args) throws InterruptedException {
		
		//Step 1: Launch the browser
		WebDriver driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step 2: Load the URL
		driver.get("https://www.saucedemo.com/");
		
		//Step 3: Login to application
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		//Step 4: Click on a product
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']")).click();
		String productTitle = driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']")).getText();
		
		//Step 5: Add Product to cart
		driver.findElement(By.id("add-to-cart")).click();
		
		//Step 6: Navigate to cart and validate the product
		driver.findElement(By.className("shopping_cart_link")).click();
		
		String ProductTitleInCart = driver.findElement(By.className("inventory_item_name")).getText();
		
		if(ProductTitleInCart.equalsIgnoreCase(productTitle))
		{
			System.out.println("product successfully added to cart");
			System.out.println("PASS");
			System.out.println(ProductTitleInCart);
		}
		else
		{
			System.out.println("Product NOt added to cart -> FAIL");
		}
		
		//Step 7: LOgout of Application
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.linkText("Logout")).click();
		
		System.out.println("Logout successful");
		
		
		
		
		
		
		
		
		
		
		
	}

}
