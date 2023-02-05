package frameWorkpackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameWorkPackages.AbstractComonents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".complete-header")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}

}