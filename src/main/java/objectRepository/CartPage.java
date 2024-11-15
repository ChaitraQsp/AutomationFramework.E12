package objectRepository;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	//Declaration
	@FindBy(className = "inventory_item_name")
	private WebElement productNameLnk;
	
	//Initialization
	public CartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getProductNameLnk() {
		return productNameLnk;
	}
	
	//Business library
	/**
	 * This method will capture the product title in cart and return to caller
	 * @return
	 */
	public String getProductTitle()
	{
		return productNameLnk.getText();
	}
	
	
	

}
