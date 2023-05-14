package webdriver;


import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Handle_Frame_IFrame {
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
	public void TC_01_Ifram_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'/kyna.vn')]")).isDisplayed());
		// switch sang the chua frame do
		//1
		//driver.switchTo().frame(0);
		// 2
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/kyna.vn')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "164K likes");
		
		//switch ve trang main page
		driver.switchTo().defaultContent();
		
		
		//switch qua chat iframe
		driver.switchTo().frame("cs_chat_iframe");
		
		// click vao chat de show len support
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Meomeo");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		sleepInSecond(2);
		
		// quay ve main
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excell");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
	}

	@Test
	public void TC_02_Frame_HDFC() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("meoemeo");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input#keyboard")).sendKeys("memeoemem");
	}

	@Test
	public void TC_03_() {
		
	}

	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
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