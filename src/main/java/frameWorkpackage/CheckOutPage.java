package frameWorkpackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import frameWorkPackages.AbstractComonents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	public WebDriverWait wait;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(css=".shopping_cart_badge")
	WebElement cart;
	
	@FindBy(id="checkout")
	WebElement checkoutButton;
	
	@FindBy(id="first-name")
	WebElement firstName;
	
	@FindBy(id="last-name")
	WebElement lastName;
	
	@FindBy(id="postal-code")
	WebElement postalCode;
	
	@FindBy(id ="continue")
	WebElement process;
	
	@FindBy(id="finish")
	WebElement wrapup;
	
	
	
	public void cartBag()
	{
		 wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_badge")));
		cart.click();
		
	}
	public void verifyCartProducts(String shoppingProducts)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item_name")));
		String results = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
		Assert.assertTrue(results.contains(shoppingProducts));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
		checkoutButton.click();		
	}
	public void personalInfrormation()
	{
		firstName.sendKeys("Arindam");
		lastName.sendKeys("Das");
		postalCode.sendKeys("700135");
	}
	public ConfirmationPage submitOrder()
	{
		process.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wrapup.click();
		return new ConfirmationPage(driver);
	}
	

}
