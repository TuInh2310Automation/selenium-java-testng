package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Aleart {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		// 1. Co the switch qua alert luon
		// driver.switchTo().alert();

		// 2. Wait trc khi switch qua alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// Accept alert
		alert.accept();

		assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");

	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		// 1. Co the switch qua alert luon
		// driver.switchTo().alert();

		// 2. Wait trc khi switch qua alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Dimiss alert
		alert.dismiss();
		sleepInSecond(3);
		assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		// 1. Co the switch qua alert luon
		// driver.switchTo().alert();

		// 2. Wait trc khi switch qua alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		String  promptString = "This is automation tesst";
		alert.sendKeys(promptString);
		// Dimiss alert
		alert.accept();
		sleepInSecond(3);
		assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + promptString);
	}

	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com/");
		String url = driver.findElement(By.xpath("//a[@href='/basic_auth']")).getAttribute("href");
		System.out.println(url);
		driver.get(url);
		//driver.get(convertedURL("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		System.out.println(convertedURL(url, "admin", "admin"));
		sleepInSecond(10);
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public String convertedURL(String url, String password, String userName) {
		String[] urlArr = url.split("//");
		if (urlArr.length == 2) {
			return urlArr[0]+ "//" + userName  + ":" + password + "@" + urlArr[1];
		} else {
			return  "";
		}
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
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