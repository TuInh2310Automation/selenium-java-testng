package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_PopUp_FixInDom_P1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}
	
	

	@Test
	public void TC_01_Popup_in_Dom() {
	
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);
		
		By loginPopup = By.xpath("//div[@id='modal-login-v1' and @class='modal fade in']//div[@class='modal-content']");
		
//		WebElement elementBy= null;
//		try {
//			elementBy = driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style= 'display: none;']"));
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		Assert.assertTrue((elementBy == null) || (!elementBy.isDisplayed()));	
		
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @class='modal fade in']//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @class='modal fade in']//input[@id='password-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @class='modal fade in']//button[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @class='modal fade in']//div[@class='row error-login-panel']")).getText(),"Tài khoản không tồn tại!");
	}

	//@Test
	public void TC_02_Popup_in_Dom_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(3);
		
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456789");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
	}

	//@Test
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
		//driver.quit();
	}
}