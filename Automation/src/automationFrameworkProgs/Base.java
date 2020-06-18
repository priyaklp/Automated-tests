package automationFramework;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	public static WebDriver driver;

	public static void initialization(){
		System.setProperty("webdriver.chrome.driver", "D:/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://ecom-optimus.myshopify.com");

	}
	public void failed(){
		TakesScreenshot ts = (TakesScreenshot)driver;
		File scrFile = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("C:/Users/Admin/Desktop/Automation/src/screenshots/testfailure.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
