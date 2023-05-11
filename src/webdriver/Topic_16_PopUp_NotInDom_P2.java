package webdriver;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_PopUp_NotInDom_P2 {
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
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(new FirefoxProfile());
//		options.addPreference("dom.webnotifications.enabled", false);
//		driver = new FirefoxDriver(options);

		Map<String, Integer> prefs = new HashMap<String, Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(option);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Popup_Fixed_NotInDOM_Tiki() {
		driver.get("https://tiki.vn/");
		By loginPopUp = By.cssSelector("div.ReactModal__Content");
		// verify popup chua hien thi
		Assert.assertEquals(driver.findElements(loginPopUp).size(), 0);
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElements(loginPopUp).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopUp).isDisplayed());
		
		//driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("012345678");
		//login with email
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		
		// nhap user/pw
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		// so sanh error mesage
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		
		// close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		//popup bien mat
		
		Assert.assertEquals(driver.findElements(loginPopUp).size(), 0);
	}

	@Test
	public void TC_02_Popup_Fixed_NotInDOM_Facebook() {
		driver.get("https://www.facebook.com/");
		By  createAccPopupBy= By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		Assert.assertEquals(driver.findElements(createAccPopupBy).size(), 0);
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(createAccPopupBy).size(), 1);
		
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("23");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Oct");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1990");
		
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(createAccPopupBy).size(), 0);
		
	}

	// @Test
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
		// driver.quit();
	}
}