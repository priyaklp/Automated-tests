// In this program the script will run parallel both in Firefox as well as Chrome
// In this program i have covered the scenario of Removing the product from the Cart

package automationFramework;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTest {
	
	WebDriver driver = null;
	
	@Parameters("browserName")
	@BeforeTest
	
	public void setup(String browserName){
		System.out.println("Browser name is:"+" "+browserName);
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "D:/driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "D:/driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}	
	}
	@Test
	public void RemoveProdFromCart() throws InterruptedException{
		driver.get("http://ecom-optimus.myshopify.com");
		driver.manage().window().maximize();
		driver.get("http://ecom-optimus.myshopify.com");
		driver.findElement(By.xpath("//*[contains(.,'Enter using password')]/span")).click();

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("idgad");
		driver.findElement(By.xpath("//button[@name='commit'][contains(text(),'Enter')]")).click();
		driver.findElement(By.xpath("//*[text()='Catalog'][contains(@class,'site')]")).click();
		driver.findElement(By.xpath("(//span[text()='Another Round Neck Shirt']/..)[position()=3]")).click();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//*[contains(text(),'Add to cart')])[2]")).click();
		Thread.sleep(2000);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@aria-label='Close']")).click();
		driver.findElement(By.xpath("//*[@class='site-header__icon site-header__cart']")).click();
		driver.findElement(By.xpath("//*[text()='Remove']")).click();
		
	}
	@AfterTest
	public void teardown(){
		driver.close();
		System.out.println("Test completed*********");
		
	}

}