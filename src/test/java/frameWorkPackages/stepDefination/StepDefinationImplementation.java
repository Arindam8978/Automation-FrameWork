package frameWorkPackages.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import frameWorkPackages.TestComponents.BaseTest;
import frameWorkpackage.CheckOutPage;
import frameWorkpackage.ConfirmationPage;
import frameWorkpackage.LandingPage;
import frameWorkpackage.ProductCatalouge;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest{
	
	public LandingPage landingpage;
	public ProductCatalouge catalouge;
	public ConfirmationPage confirmatonPage ;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException, InterruptedException
	{
		landingpage = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String userName, String passWord)
	{
		catalouge = landingpage.loginApplication(userName, passWord);
	}
	
	@When ("^I add product (.+) to cart$")
	 public void i_add_product_to_cart(String productName)
	 {
		List<WebElement> products = catalouge.getProductList();
		catalouge.addProductToCart(productName);
	 }
	
	@And ("^checkout (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String productName)
	{
		CheckOutPage check = new CheckOutPage(driver);
		check.cartBag();
		check.verifyCartProducts(productName);
		check.personalInfrormation();
		check.submitOrder();
	}
	 
	 @Then ("{string} message is displayed in confirmation page")
	 public void message_is_displayed_in_confirmation_page(String string)
	 {
		    confirmatonPage =  new ConfirmationPage(driver);
			String confirmMessage = confirmatonPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	 }

	 @Then("^\"([^\"]*)\" Error message is displayed$")
	    public void something_error_message_is_displayed(String string1)
	 {
			Assert.assertEquals(string1, landingpage.getErrorMessage());
			driver.close();
	 }
	
	

}
