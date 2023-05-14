package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	public void TC_01_ID() {
		// Parent Page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// 1- Lay ra Id của tab/windown dang dung, active
		String parentPageWindowID = driver.getWindowHandle();

		// click vao gooogle link de bat ra 1 tab moi
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		// 2- Lay ra tat ca cac ID
		switchToWindowFromID(parentPageWindowID);
		// kiem tra
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Meo");
		sleepInSecond(3);

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		switchToWindowFromID(driver.getWindowHandle());
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

// chi dung dc cho 2 window/tab
	public void switchToWindowFromID(String currentWindowID) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set ko cho phep trung
		// Case 1: cho co 2 window hoawcj 2 tab

		for (String id : allWindowIDs) {
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
				sleepInSecond(3);
			}
		}
	}



	//@Test
	public void TC_02_Title() {
		// Parent Page
				driver.get("https://automationfc.github.io/basic-form/index.html");
				// click vao gooogle link de bat ra 1 tab moi
				driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
				sleepInSecond(3);
				// 2- Lay ra tat ca cac ID
				switchToWindowByPageTitle("Google");
				// kiem tra
				driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Meo");
				sleepInSecond(3);

				Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
				switchToWindowByPageTitle( "Selenium WebDriver");
				Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_03_Live_Guru() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product IPhone has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		//driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		closeAllWindowsWithoutParent(parentID);
		switchToWindowByPageTitle("Mobile");
		driver.findElement(By.cssSelector("a[title='IPhone']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "IPhone - Mobile");
		
		
	}
	// dung dc cho tu 2 window/tab tro len
		public void switchToWindowByPageTitle(String expectedPageTitle) {
			Set<String> allWindowIDs = driver.getWindowHandles(); // Set ko cho phep trung
			// Case 1: cho co 2 window hoawcj 2 tab

			for (String id : allWindowIDs) {
				// Switch tung ID truoc
				driver.switchTo().window(id);
				// lay ra title cua page
				String actualPageTitle = driver.getTitle();
				if (actualPageTitle.equals(expectedPageTitle)) {
					break;
				}
			}
		}
	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindowSet = driver.getWindowHandles();
		for (String id : allWindowSet) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
		}
		driver.switchTo().window(parentID);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}