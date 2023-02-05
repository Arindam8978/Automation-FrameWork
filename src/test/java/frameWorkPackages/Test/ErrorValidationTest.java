package frameWorkPackages.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameWorkPackages.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{
	
	@Test
	public void LoginErrorValidation()	
	{
		landingpage.loginApplication("standard_user", "scauce_jpack");
		Assert.assertEquals("Epic sadface: Username password do not match any user in this service", landingpage.getErrorMessage());
		
	}
	

}
