package automationFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;


public class BaseTest
{
	WebDriver driver;
	@BeforeClass  
	public void initDrivers()  
	{  
		test = extent.createTest("openBrowser");
		System.setProperty("webdriver.chrome.driver", "D:/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://ecom-optimus.myshopify.com");
		driver.findElement(By.xpath("//*[contains(.,'Enter using password')]/span")).click();

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("idgad");
		driver.findElement(By.xpath("//button[@name='commit'][contains(text(),'Enter')]")).click();
	} 

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void setUp()
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/testReports/MyOwnReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Host Name", "Priya");
		extent.setSystemInfo("Environment", "QA");

		htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
		htmlReporter.config().setReportName("My Own Report");

		htmlReporter.config().setTheme(Theme.DARK);
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
		else
		{
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	@AfterSuite
	public void tearDown()
	{
		extent.flush();
	}

}