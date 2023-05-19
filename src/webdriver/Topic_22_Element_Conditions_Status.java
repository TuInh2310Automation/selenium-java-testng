package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Element_Conditions_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitDriverWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitDriverWait = new WebDriverWait(driver, 10);
	}

	// @Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://vi-vn.facebook.com/");
		// 1. Co tren UI( bat buoc)
		// 1. Co trong HTML bat buoc
		// cho cho email address textbox hien thi trong 10s
		explicitDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("email")));
		// driver.findElement(By.id("email")).sendKeys("meme@gmail.com");

	}

	// @Test
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		// 2. Ko co tren UI ( bat buoc)
		// 1. co tren html
		driver.get("https://vi-vn.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		// wait re-enter email textbox ko hien thi trong 10s
		explicitDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	// @Test
	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		// 2. Ko co tren UI ( bat buoc)
		// 1. ko co tren html
		driver.get("https://vi-vn.facebook.com/");
		// wait re-enter email textbox ko hien thi trong 10s
		explicitDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	// @Test
	public void TC_03_Presence_I() {
		driver.get("https://vi-vn.facebook.com/");
		// 1. Co tren UI
		// 1. Co trong HTML (bat buoc)
		explicitDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}

	//@Test
	public void TC_03_Presence_II() {
		driver.get("https://vi-vn.facebook.com/");
		// 1. Ko Co tren UI
		// 1. Co trong HTML (bat buoc)
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		// wait re-enter email textbox ko hien thi trong 10s
		explicitDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}

	@Test
	public void TC_04_Staleness() {
		driver.get("https://vi-vn.facebook.com/");
		// 2. Ko co tren UI ( bat buoc)
		// 1. ko co tren html
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		// Phase 1: element co trong DOM, HTML
		WebElement reEnterEmailElement= driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
		
		// Thao tac vs element khac lam cho elent re-enter email ko con nua
		
		//close popup di
		driver.findElement(By.cssSelector("img[class='_8idr img']")).click();
		// Cho cho re-enter email texxtbox ko con trong DOm trong vong 10s
		explicitDriverWait.until(ExpectedConditions.stalenessOf(reEnterEmailElement));
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}