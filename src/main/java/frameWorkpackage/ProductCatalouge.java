package frameWorkpackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameWorkPackages.AbstractComonents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent{
	WebDriver driver;
	public ProductCatalouge(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css = ".inventory_item_description")
	List<WebElement> products;
	
	By productBy = By.cssSelector(".inventory_item_description");
	By addToCart = By.cssSelector(".pricebar button:last-of-type");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	public WebElement getProduct(String shoppingProducts)
	{
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(shoppingProducts)).findFirst().orElse(null);
		return prod;
		
	}
	public void addProductToCart(String shoppingProducts)
	{
		WebElement prod = getProduct(shoppingProducts);
		prod.findElement(addToCart).click();
	}
	
	
}

