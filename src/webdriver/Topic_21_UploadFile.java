package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_UploadFile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String lvhFilename = "lavanhi.jpg";
	String baluFileName = "balu.jpg";
	String coupleFieName = "couple.jpg";

	String lvhFilePath = projectPath + "/uploadFiles/" + lvhFilename;
	String baluFilePath = projectPath + "/uploadFiles/" + baluFileName;
	String coupleFilePath = projectPath + "/uploadFiles/" + coupleFieName;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Upload_1Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load file len
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(lvhFilePath);
		sleepInSecond(1);

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(baluFilePath);
		sleepInSecond(1);

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(coupleFilePath);
		sleepInSecond(1);

		// Verify file duoc load len thanh cong
		assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + lvhFilename + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + baluFileName + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + coupleFieName + "']")).isDisplayed());
		// click upload
		List<WebElement> startBtnList = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startBtn : startBtnList) {
			startBtn.click();
			sleepInSecond(2);
		}
		// verify upload file len thanh cong

		Assert.assertTrue(driver.findElement(By.cssSelector("p.name a[title='" + lvhFilename + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name a[title='" + baluFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name a[title='" + coupleFieName + "']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'lavanhi.jpg')]")).isDisplayed());

	}

	@Test
	public void TC_02_Multiple_File_PerTime() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load file len
		driver.findElement(By.cssSelector("input[type='file']"))
				.sendKeys(lvhFilePath + "\n" + baluFilePath + "\n" + coupleFilePath);
		sleepInSecond(3);

		// Verify file duoc load len thanh cong
		assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + lvhFilename + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + baluFileName + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + coupleFieName + "']")).isDisplayed());
		// click upload
		List<WebElement> startBtnList = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startBtn : startBtnList) {
			startBtn.click();
			sleepInSecond(2);
		}
		// verify upload file len thanh cong

		Assert.assertTrue(driver.findElement(By.cssSelector("p.name a[title='" + lvhFilename + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name a[title='" + baluFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name a[title='" + coupleFieName + "']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'lavanhi.jpg')]")).isDisplayed());

	}

	@Test
	public void TC_03_() {

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