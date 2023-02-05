package frameWorkPackages.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import frameWorkpackage.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\main\\java\\frameWorkPackages\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException, InterruptedException
	{
		driver=initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	public Object[][] getDataFromExcel() throws IOException
	{
		DataFormatter formatter = new DataFormatter();
		String File = "C:\\Automation_FrameWork\\ExcelDriven.xlsx";
		FileInputStream fis = new FileInputStream(File);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		
		Object[][] data = new Object [rowCount-1][columnCount];
		
		for(int i=0;i<rowCount-1;i++)
		{
			row = sheet.getRow(i+1);
			for(int j=0;j<columnCount;j++)
			{
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		
		return data;
				
	}
	
	public String getScreenShort(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" +testCaseName+".png" );
		FileUtils.copyFile(source, file);
	return System.getProperty("user.dir")+"//reports//" +testCaseName+".png";
	}
	
}
