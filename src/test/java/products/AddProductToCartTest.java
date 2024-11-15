package products;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.AllProductsPage;
import objectRepository.CartPage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;


@Listeners(genericUtilities.ListenersImplementation.class)
/*Test Class*/
public class AddProductToCartTest extends BaseClass{

	@Test(groups = "Smoke") /*Test method or Test script*/
	public void tc_001_AddSingleProductToCartTest() throws IOException , InterruptedException
	{
	
		// Read The Test Data From Excel File
		String PRODUCTNAME = eUtil.readDataFromExcel("Products", 1, 2);
		System.out.println(PRODUCTNAME);

		// Step 4: Click on a product
		Thread.sleep(1000);
		AllProductsPage app = new AllProductsPage(driver);
		String productTitle = app.clickOnProductName(driver, PRODUCTNAME);

		// Step 5: Add Product to cart
		ProductPage pp = new ProductPage(driver);
		pp.clickOnAddToCartButton();
		

		// Step 6: Navigate to cart 
		pp.clickOnCartButton();

		//Step 7: Capture product details in Cart
		CartPage cp = new CartPage(driver);
		String ProductTitleInCart = cp.getProductTitle();
		
		//Step 8: Validate for product  name
		Assert.assertEquals(ProductTitleInCart, productTitle);
		
		Assert.assertTrue(ProductTitleInCart.contains(productTitle));
                         //FastTrack 1 Nos              Fastrack
		 
	}

}
