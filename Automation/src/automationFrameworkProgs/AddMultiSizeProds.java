package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddMultiSizeProds extends BaseTest {

	@Test
	public void addMultiSizeProds() throws InterruptedException{

		driver.findElement(By.xpath("//*[text()='Catalog'][contains(@class,'site')]")).click();
		driver.findElement(By.xpath("(//span[text()='Another Round Neck Shirt']/..)[position()=1]")).click();

		//changing the colour
		WebElement ColourDropDownLocator = driver.findElement(By.xpath("//*[@class='single-option-selector single-option-selector-product-template product-form__input'][@id='SingleOptionSelector-0']"));
		Select colour = new Select(ColourDropDownLocator);
		colour.selectByValue("Silver");
		//changing the size
		WebElement SizeDropdownLocator = driver.findElement(By.xpath("//*[@class='single-option-selector single-option-selector-product-template product-form__input'][@id='SingleOptionSelector-1']"));
		Select size = new Select(SizeDropdownLocator);
		size.selectByValue("L");

		driver.findElement(By.xpath("(//*[contains(text(),'Add to cart')])[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@aria-label='Close']")).click();

		driver.findElement(By.xpath("//*[@class='site-header__icon site-header__cart']")).click();
		String CartSize = driver.findElement(By.xpath("//tr[@class='cart__row']//ul/li[2]")).getText();
		String[] spSize = CartSize.split(" ");
		if(spSize[1].equalsIgnoreCase("L")){
			Assert.assertTrue(true, "It not the added size of the product");
		}

		driver.navigate().back();
		// change the size again
		
		WebElement SizeDropdownLocator1 = driver.findElement(By.xpath("//*[@class='single-option-selector single-option-selector-product-template product-form__input'][@id='SingleOptionSelector-1']"));
		SizeDropdownLocator1.click();
		Select size1 = new Select(SizeDropdownLocator1);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		size1.selectByValue("2XL");

		driver.findElement(By.xpath("(//*[contains(text(),'Add to cart')])[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@aria-label='Close']")).click();

		driver.findElement(By.xpath("//*[@class='site-header__icon site-header__cart']")).click();
		String CartSize1 = driver.findElement(By.xpath("//tr[@class='cart__row']//ul/li[2]")).getText();
		String[] spSize1 = CartSize1.split(" ");
		if(spSize1[1].equalsIgnoreCase("2XL")){
			Assert.assertTrue(true, "It not the added size of the product");
		}
		driver.close();
	}
}