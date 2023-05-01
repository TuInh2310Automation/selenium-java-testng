package webdriver;


import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Checkbox_RadioButton {
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
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By btnLogin = By.cssSelector("button.fhs-btn-login");
		assertFalse(driver.findElement(btnLogin).isEnabled());
		String backgrounColor = driver.findElement(btnLogin).getCssValue("background-image");
		System.out.print(backgrounColor);
		Assert.assertTrue(backgrounColor.contains("rgb(224, 224, 224)"));
		
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987633463");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		Assert.assertTrue(driver.findElement(btnLogin).isEnabled());
		String activeBackground = driver.findElement(btnLogin).getCssValue("background-color");
		Color activeBackgrounColor = Color.fromString(activeBackground);
		
		Assert.assertEquals(activeBackgrounColor.asHex(), "#c92127");
	}

	//@Test
	public void TC_02_Default_CheckBox_Radio_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Truong hop chon 1 check box/ radio
		driver.findElement(By.xpath("//label[contains(text(),'Arthritis')]/preceding-sibling::input")).click();
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).click();
		
		
		assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Arthritis')]/preceding-sibling::input")).isSelected());
		assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).isSelected());
		
		// Checkbox co the tuw bo chon dc
		driver.findElement(By.xpath("//label[contains(text(),'Arthritis')]/preceding-sibling::input")).click();
		//verify checkbox tu bo chon
		assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Arthritis')]/preceding-sibling::input")).isSelected());
		
		// radio ko the tu bo chon dc
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).click();
		
		//verify radio ko tu bo chon dc 
		assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).isSelected());
	}

	//@Test
	public void TC_03_Default_CheckBox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> checkboxList = driver.findElements(By.cssSelector("input.form-checkbox"));
		for (WebElement eachCheckBox : checkboxList) {
			eachCheckBox.click();
			sleepInSecond(1);
		}
		for (WebElement eachCheckBox : checkboxList) {
			Assert.assertTrue(eachCheckBox.isSelected());
		}
	}

	//@Test
	public void TC_04_Default_CheckBox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(3);
		checkToCheckBox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		uncheckToCheckBox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
	}
	
	//@Test
	public void TC_05_Custom_Radio_Button() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div//input")).isSelected());
		
	}
	
	@Test
	public void TC_06_Custom_Radio_Button() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		driver.findElement(By.xpath("//div[@data-value='Cần Thơ']")).click();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	}
	
	
	public void checkToCheckBox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckBox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(
					);
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}