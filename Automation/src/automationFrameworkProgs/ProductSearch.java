
//In this program we are Searching and Adding the product to the Cart and Validating whether the Product is in Cart or not

package automationFramework;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductSearch extends BaseTest
{
	@Test
	public void SearchAddProduct() throws Exception
	{
		driver.findElement(By.xpath("//span[text()='Search']/..")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Round Neck Shirt");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[text()='Round Neck Shirt']")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Add to cart')])[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String ProdName = driver.findElement(By.className("cart-popup-item__title")).getText();
		if(ProdName.equalsIgnoreCase("Round Neck Shirt"))
		{
			Assert.assertTrue(true, "the product is not present in the Cart list");
		}
		driver.findElement(By.xpath("//*[@aria-label='Close']")).click();
		driver.findElement(By.xpath("//*[@class='site-header__icon site-header__cart']")).click();
		String Cart = driver.findElement(By.className("cart-header__title")).getText();

		if(Cart.equalsIgnoreCase("Your cart")){
			Assert.assertTrue(true, "the user is not in the Cart page");
		}
		String CartProdName = driver.findElement(By.xpath("//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td//div/a")).getText();
		if(CartProdName.equalsIgnoreCase(ProdName))
		{
			Assert.assertTrue(true,"The product is not added to Cart");
		}
		driver.close();
		System.out.println("Automation Completed");
	}


}