package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextBox = By.id("mail");
	By under18Checkbox = By.id("under_18");
	By eduTextBox = By.id("edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
    By pwTextBox = By.id("disable_password");
    By bioTextArea = By.id("bio");
    By job1DropDown = By.id("job1");
    By developmentCheckBox = By.id("development");
    
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	

	@Test
	public void TC_01_Display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		if(driver.findElement(emailTextBox).isDisplayed()) {
			driver.findElement(emailTextBox).sendKeys("Automation Testing");
			System.out.println("emailTextBox is display");
		} else {
			System.out.println("emailTextBox is not display");
		}
		
		if(driver.findElement(eduTextBox).isDisplayed()) {
			driver.findElement(eduTextBox).sendKeys("Automation Testing");
			System.out.println("eduTextBox is display");
		} else {
			System.out.println("eduTextBox is not display");
		}
		
		if(driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("nameUser5Text is display");
		} else {
			System.out.println("nameUser5Text is not display");
		}
	}

	@Test
	public void TC_02_Enable() {
		if(driver.findElement(emailTextBox).isEnabled()) {
			driver.findElement(emailTextBox).sendKeys("Automation Testing");
			System.out.println("emailTextBox is enable");
		} else {
			System.out.println("emailTextBox is not enable");
		}
		
		if(driver.findElement(bioTextArea).isEnabled()) {
			driver.findElement(bioTextArea).sendKeys("Automation Testing");
			System.out.println("bioTextArea is enable");
		} else {
			System.out.println("bioTextArea is not enable");
		}
	}

	@Test
	public void TC_03_Select() {
		
		Assert.assertFalse(driver.findElement(under18Checkbox).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckBox).isSelected());
		
		driver.findElement(developmentCheckBox).click();
		driver.findElement(under18Checkbox).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(developmentCheckBox).isSelected());
		Assert.assertTrue(driver.findElement(under18Checkbox).isSelected());
	}
	
	@Test
	public void TC_04_Register() {
		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup/");
		By emailTextBox = By.id("email");
		driver.findElement(emailTextBox).sendKeys("vananhleektmtk53@gmail.com");
		By pwTextBox = By.id("new_password");
		WebElement pwElement = driver.findElement(pwTextBox);

		
		pwElement.sendKeys("abc");
		sleepInSecond(9);
		driver.findElement(By.id("create-account-enabled")).click();
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
//		
//		pwElement.clear();
//		pwElement.sendKeys("Aa@123456");
//		driver.findElement(By.id("create-account-enabled")).click();
//		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
//		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
//		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
//		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
//		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
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
		driver.quit();
	}
}