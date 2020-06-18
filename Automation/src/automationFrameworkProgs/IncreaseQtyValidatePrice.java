
// In this program i have added the product from the featured collection and as per the quantity, validated the total price of the product

package automationFramework;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IncreaseQtyValidatePrice extends BaseTest{

	@Test
	public void increaseQty() throws InterruptedException{
		driver.findElement(By.xpath("//span[text()='Round Neck Shirt 5']/..")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Add to cart')])[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@aria-label='Close']")).click();
		driver.findElement(By.xpath("//*[@class='site-header__icon site-header__cart']")).click();
		String Cart = driver.findElement(By.className("cart-header__title")).getText();

		if(Cart.equalsIgnoreCase("Your cart")){
			Assert.assertTrue(true, "the user is not in the Cart page");
		}
		String CartProdName = driver.findElement(By.xpath("//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td//div/a")).getText();
		if(CartProdName.equalsIgnoreCase("Round Neck Shirt 5"))
		{
			Assert.assertTrue(true,"The product is not added to Cart");
		}

		//validate for 1 quantity
		int iqty=1;
		String sqty = String.valueOf(iqty);
		driver.findElement(By.xpath("//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td[3]/div/input")).clear();
		driver.findElement(By.xpath("//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td[3]/div/input")).sendKeys(sqty);
		//Get the price for 1 product
		String price = driver.findElement(By.xpath("(//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td[2]//dd)[3]")).getText();
		String[] arr = price.split(" ");
		String pri = arr[1];
		String Amount = pri.replaceAll(",", "");
		Double q = Double.valueOf(Amount);

		//total price from the app
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String TotalPrice = driver.findElement(By.xpath("//td[contains(@class,'cart__final-price')]//div/span")).getText();
		String[] tPrice = TotalPrice.split(" ");
		String tAmount = tPrice[1].replaceAll(",", "");
		Double tq = Double.valueOf(tAmount);
		Assert.assertEquals(tq, iqty*q);
		System.out.println("*******************************");

		//increase the Quantity
		int increasedQty=3;
		String sqtyy = String.valueOf(increasedQty);
		driver.findElement(By.xpath("//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td[3]/div/input")).click();
		driver.findElement(By.xpath("//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td[3]/div/input")).clear();
		driver.findElement(By.xpath("//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td[3]/div/input")).sendKeys(sqtyy);
		//Get the price for 1 product
		String iprice = driver.findElement(By.xpath("(//*[@class='cart__row cart__row--heading']/following-sibling::tbody//td[2]//dd)[3]")).getText();
		String[] iarr = iprice.split(" ");
		String ipri = iarr[1];
		String iAmount = ipri.replaceAll(",", "");
		Double iq = Double.valueOf(iAmount);

		//total price from the app
		Thread.sleep(2000);//Wait is given because the app takes few seconds to update the value as per the quantity given
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String iTotalPrice = driver.findElement(By.xpath("//td[contains(@class,'cart__final-price')]//div/span")).getText();
		String[] itPrice = iTotalPrice.split(" ");
		String itAmount = itPrice[1].replaceAll(",", "");
		Double itq = Double.valueOf(itAmount);
		Assert.assertEquals(itq, increasedQty*iq);
		System.out.println("Automation Completed");
	}
}