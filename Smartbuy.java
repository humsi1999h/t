package smartbuy;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Smartbuy {

	public static String removeAfterSpace(String str) {
		int spaceIndex = str.indexOf(' ');
		if (spaceIndex != -1) {
			return str.substring(0, spaceIndex);
		} else {
			return str;
		}
	}

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void OpenWeb() {
		// Open WebSite
		driver.get("https://smartbuy-me.com/smartbuystore/en/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void Buyproduct() throws InterruptedException {
		Thread.sleep(2000);

		// Search process
		driver.findElement(By.xpath("//*[@id=\"js-site-search-input\"]")).sendKeys("ASUS Vivobook");
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("/html/body/main/header/div[4]/div/nav/div/div[2]/div/div/div/div/form/div/span/button/span"))
				.click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(1000);
		// Select process
		driver.findElement(By.xpath("//*[@id=\"change_view\"]/div/div/div/ul/div[1]/div/div/a/img")).click();
		WebElement PriceLap = driver
				.findElement(By.xpath("/html/body/main/div[3]/div[1]/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/p"));
		String Laptop = PriceLap.getText();
		String Pricewithoutjd = removeAfterSpace(Laptop);
		double Laptopprice = Double.parseDouble(Pricewithoutjd);

		Thread.sleep(2000);
		// Add to the cart
		driver.findElement(By.xpath("//*[@id=\"addToCartButton\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[1]")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("/html/body/main/div[3]/div[1]/div[2]/div[5]/div/div/div[1]/button")).click();
		Thread.sleep(2000);
        // Login process
		driver.findElement(By.xpath("(//input[@id='j_username'])[2]")).sendKeys("hamzahalhumsi@hotmail.com");
		driver.findElement(By.xpath("(//input[@id='j_password'])[2]")).sendKeys("hamzah123");
		driver.findElement(By.xpath("(//div[contains(@class,'col-md-6')])[3]")).click();
		Thread.sleep(1000);
		//placing an order process
		driver.findElement(By.xpath("//*[@id=\"countrySelector\"]/div/div/div/button/span[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"countrySelector\"]/div/div/div/div/div/input")).sendKeys("Amman");
		driver.findElement(By.xpath("//*[@id=\"countrySelector\"]/div/div/div/div/ul/li[6]/a/span[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@id='us3-address'])[1]")).sendKeys("Tla Al-Ali");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,900)", "");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"i18nAddressForm\"]/div[10]/div/div/div/div/button")).click();
		driver.findElement(By.xpath("//*[@id=\"i18nAddressForm\"]/div[10]/div/div/div/div/div/div/input"))
				.sendKeys("jo");
		driver.findElement(By.xpath("//*[@id=\"i18nAddressForm\"]/div[10]/div/div/div/div/div/ul/li[114]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"addressSubmit\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"deliveryMethodSubmit\"]")).click();
		Thread.sleep(1000);
		WebElement Delivery = driver
				.findElement(By.xpath("/html/body/main/div[3]/div[1]/div/div[2]/div[2]/div/div/div/div[2]/span"));
		String Deliverysmart = Delivery.getText();
		String Deliverywithoutjd = removeAfterSpace(Deliverysmart);
		double DeliveryPrice = Double.parseDouble(Deliverywithoutjd);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"timeSlotForm\"]/div[1]/div[2]/div/label")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[4]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"chooseDeliveryMethod_continue_button\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"paymentMethodForm\"]/div[1]/div/ul/li[2]/div[1]/label/img")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"chooseDeliveryMethod_continue_button\"]")).click();
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,500)", "");
		driver.findElement(By.xpath("//*[@id=\"Terms1\"]")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"silentOrderPostForm\"]/button")).click();
		double ORDERTOTAL = DeliveryPrice + Laptopprice;
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,500)", "");
		WebElement total = driver.findElement(
				By.xpath("/html/body/main/div[3]/div[1]/div/div[1]/div[2]/div/div[2]/div/div/div/div/div[3]/span"));
		String totalprice = total.getText();
		String totalwithoutjd = removeAfterSpace(totalprice);
		double TotalPrice = Double.parseDouble(totalwithoutjd);
		//assert the (Laptop price and delivery price ) with Total price
		Assert.assertEquals(TotalPrice, ORDERTOTAL);
		Thread.sleep(2000);
	}

	@AfterTest
	public void post() {
		driver.close();

	}
}
