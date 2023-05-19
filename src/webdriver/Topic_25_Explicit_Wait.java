package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Explicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	WebDriverWait explicitWait;
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
		driver.manage().window().maximize();
		
	}

	// @Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		driver.findElement(By.cssSelector("div#start button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}

	// @Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		driver.findElement(By.cssSelector("div#start button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}

	//@Test
	public void TC_03_Ajax_Loaiding() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);
		// wait for date picker duoc hien thi
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		// verify ko cos ngay nao duoc chon
		Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer div.RadAjaxPanel")).getText(),"No Selected Dates to display.");
		// wait for date 19 clicked
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		//wait cho ajax icon loading bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*= RadCalendar1]>div.raDiv")));
		
		// Wait cho ngay duoc chon, co the click duoc tro lai 
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer div.RadAjaxPanel")).getText(),"Friday, May 19, 2023");
		
	}

	@Test
	public void TC_04_Upload_Files() {
		driver.get("https://gofile.io/uploadFiles");
		explicitWait = new WebDriverWait(driver, 20);
		// wait button upload file dc visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
		
		//upload 
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(lvhFilePath + "\n" + baluFilePath + "\n"+ coupleFilePath);	
		
		// wait for all loading icon bien mat 
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.mainUploadFilesList div.progress"))));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccess div.border-success")));

		
		assertEquals(driver.findElement(By.cssSelector("div.mainUploadSuccess div.border-success")).getText(), "Your files have been successfully uploaded");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}