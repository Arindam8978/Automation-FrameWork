package frameWorkPackages.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameWorkPackages.TestComponents.BaseTest;
import frameWorkpackage.CheckOutPage;
import frameWorkpackage.ConfirmationPage;
import frameWorkpackage.ProductCatalouge;

public class SubmitOrderTest extends BaseTest { 
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(String user,String password, String productName)
	{
		ProductCatalouge catalouge = landingpage.loginApplication(user, password);
		List<WebElement> products = catalouge.getProductList();
		catalouge.addProductToCart(productName);
		CheckOutPage check = new CheckOutPage(driver);
		check.cartBag();
		check.verifyCartProducts(productName);
		check.personalInfrormation();
		check.submitOrder();
		ConfirmationPage confirmatonPage =  new ConfirmationPage(driver);
		String confirmMessage = confirmatonPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANK YOU FOR YOUR ORDER"));
		
	}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		Object[][] Data = getDataFromExcel();
		return Data;
		//return new Object [][] {{"standard_user","secret_sauce","Sauce Labs Backpack"}};
	}
	

}
