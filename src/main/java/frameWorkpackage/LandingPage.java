package frameWorkpackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameWorkPackages.AbstractComonents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * public LandingPage(WebDriver driver) { super(driver); this.driver= driver;
	 * PageFactory.initElements(driver, this);
	 * 
	 * }
	 */
	
	@FindBy(id="user-name")
	WebElement username;
	
	@FindBy(id="password")
	WebElement userPassword;
	
	@FindBy(id="login-button")
	WebElement login;
	
	@FindBy(xpath="//h3[contains(text(),'Epic sadface: Username and password do not match any user in this service')]")
	WebElement errorMessage;
	
	public void goTo() throws InterruptedException
	{
		driver.get("https://www.saucedemo.com/");
		//driver.manage().window().maximize();
		
	}
	public ProductCatalouge loginApplication(String user, String password)
	{
		username.sendKeys(user);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalouge catalouge = new ProductCatalouge(driver);
		return catalouge;
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
