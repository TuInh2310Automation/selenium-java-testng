package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open register page
		driver.get("https://demo.nopcommerce.com/register");
	}
	// <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
	// Tên thẻ của element (Tagname HTML) : input
	// Tên của thuộc tính( Atribute Name): type, data-val, data-val-required, id, name
	// Giá trị của thuộc tính (Attribute value) : text, true, First name is required.FirstName, FirstName
	

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
	}

	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/search");
		// Nhap text vao Search text
		driver.findElement(By.className("search-text")).sendKeys("MacBook");
	}

	@Test
	public void TC_03_Name() {
		// click vaof advance checkbox
		driver.findElement(By.name("advs")).click();
		
	}
	
	@Test
	public void TC_04_TagName() {
		System.out.println( "memeo" + driver.findElements(By.tagName("input")).size());
		}
	
	@Test
	public void TC_05_LinkText() {
		// click vao duong link
		driver.findElement(By.linkText("Addresses")).click();
	}
	
	@Test
	public void TC_06_PartialLinkText() {
		driver.findElement(By.partialLinkText("vendor account")).click();
	}

	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register");
		
		// 1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selemium");
		
		// 2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
				
		// 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("vananh@gmail.com");
		
		
	}
	
	@Test
	public void TC_08_XPath() {
		driver.get("https://demo.nopcommerce.com/register");
		
		// 1
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selemium");
		
		// 2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
				
		// 3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("vananh@gmail.com");
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}