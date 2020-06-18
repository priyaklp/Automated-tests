package automationFramework;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScreenshotTest extends Base {

	@BeforeMethod
	public void setUp(){
		initialization();

	}
	@AfterMethod
	public void teardown(){
		driver.close();

	}
	@Test
	public void takeScreenshotTest(){
		driver.findElement(By.xpath("//*[contains(.,'Enter using password')]/span")).click();

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("idgad");
		driver.findElement(By.xpath("//button[@name='commit'][contains(text(),'Enter')]")).click();	
		}
}
