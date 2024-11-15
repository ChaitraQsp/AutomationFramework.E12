package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage { //Rule 1: create seperate pom class for web page
	
	//Rule 2: IdenTify the web elements using annotations
	@FindBy(id = "user-name") 
	private WebElement usernameEdt; //textField  
	
	@FindBy(id = "password") 
	private WebElement passwordEdt;
	
	@FindBy(id = "login-button")
	private WebElement loginBtn;
	
	//Rule 3: Create a constructor and initialize the web elements
	public LoginPage(WebDriver driver) //current driver ref
	{
		PageFactory.initElements(driver, this); //this.driver=driver
	}

	//Rule 4: Provide getters to access the webelements
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//business library - operation 
	/**
	 * This method will perform login operation
	 * @param USERNAME
	 * @param PASSWORD
	 */
	public void loginToApp(String USERNAME, String PASSWORD)
	{
		usernameEdt.sendKeys(USERNAME);
		passwordEdt.sendKeys(PASSWORD);
		loginBtn.click();
		
	}
	
	
	
	

}
