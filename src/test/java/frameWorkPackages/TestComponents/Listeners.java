package frameWorkPackages.TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import frameWorkPackages.resources.GetReporter;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = GetReporter.getReporterObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	

	@Override		
    public void onTestStart(ITestResult result) {					
        // TODO Auto-generated method stub				
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }
  	
	
	@Override		
    public void onTestSuccess(ITestResult result) {					
        // TODO Auto-generated method stub				
     extentTest.get().log(Status.PASS, "Test Passed");
		
    }

	@Override		
    public void onTestFailure(ITestResult result) {					
        // TODO Auto-generated method stub				
        extentTest.get().fail(result.getThrowable());
        try {
        	driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();      	
        }
        String filePath=null;
		try {
			filePath = getScreenShort(result.getMethod().getMethodName(), driver);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }		
	
    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    

    @Override		
    public void onTestSkipped(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		
    @Override		
    public void onStart(ITestContext result) {					
        // TODO Auto-generated method stub				
				
    }	
    		

    	
	@Override		
    public void onFinish(ITestContext result) {					
        // TODO Auto-generated method stub				
        extent.flush();		
		
    }	

}
