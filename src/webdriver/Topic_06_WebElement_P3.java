package webdriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_P3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress,firstName, lastName, fullName, password;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Random rand = new Random();
		emailAddress = "automation" + rand.nextInt(99999) + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		password = "12345678";
		
	}
	
	

	@Test
	public void TC_01_() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.id("send2")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).getText().contains("This is a required field."));
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).getText().contains("This is a required field."));
	}

	@Test
	public void TC_02_() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.id("email")).sendKeys("12341234@1234.1234");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.id("advice-validate-email-email")).getText().contains("Please enter a valid email address. For example johndoe@domain.com."));
	}

	@Test
	public void TC_05_CreateAcc() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='success-msg']")).getText().contains("Thank you for registering with Main Website Store."));
		String contactInfoString = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div//p")).getText();
		Assert.assertTrue(contactInfoString.contains(emailAddress));
		Assert.assertTrue(contactInfoString.contains(fullName));
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		sleepInSecond(6);
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}

	@Test
	public void TC_06_login() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		String contactInfoString = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div//p")).getText();
		Assert.assertTrue(contactInfoString.contains(emailAddress));
		Assert.assertTrue(contactInfoString.contains(fullName));
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
