package frameWorkPackages.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class GetReporter {
	
	public static ExtentReports getReporterObject()
	{
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter repoter = new ExtentSparkReporter(path);
		repoter.config().setReportName("Web Automation Results");
		repoter.config().setDocumentTitle("Test Results");
		
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(repoter);
		extend.setSystemInfo("Tester", "Arindam Das");
		return extend;
		
	}

}
