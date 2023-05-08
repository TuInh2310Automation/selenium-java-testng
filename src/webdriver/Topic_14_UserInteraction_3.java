package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_UserInteraction_3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	JavascriptExecutor javascriptExecutor;
	String dragAndDropFilePath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// @Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(8);
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(3);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");

	}

	// @Test
	public void TC_02_Right_() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);

		// Check quit menu visible
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

		// Hover on quit menu
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		// Check quit menu is selected
		Assert.assertTrue(
				driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
						.isDisplayed());

		// click quit menu
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		// clcik to alert
		driver.switchTo().alert().accept();
		sleepInSecond(3);

		// Check quit menu not visible
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

	}

	 //@Test
	public void TC_03_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallElement = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigElement = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(smallElement, bigElement).perform();
		sleepInSecond(3);
		Assert.assertEquals(bigElement.getText(), "You did great!");
		
		String bigCircleBackground = bigElement.getCssValue("background-color");
		Assert.assertEquals(Color.fromString(bigCircleBackground).asHex(), "#03a9f4");
		
	}
	 

	 @Test
	public void TC_04_Drag_And_Drop_HTML5() throws IOException {
		 String jsHelper = getContentFile(dragAndDropFilePath);
		 
		 driver.get("https://automationfc.github.io/drag-drop-html5/");
		 String sourceCss = "div#column-a";
		 String targetCss = "div#column-b";
		 
		// A to B
		 	jsHelper = jsHelper + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
			javascriptExecutor.executeScript(jsHelper);
			sleepInSecond(3);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
			// B to A
			//javascriptExecutor.executeScript(jsHelper);
			//sleepInSecond(3);
			//Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
			
	 }

	 public String getContentFile(String filePath) throws IOException {
			Charset cs = Charset.forName("UTF-8");
			FileInputStream stream = new FileInputStream(filePath);
			try {
				Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
				StringBuilder builder = new StringBuilder();
				char[] buffer = new char[8192];
				int read;
				while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
					builder.append(buffer, 0, read);
				}
				return builder.toString();
			} finally {
				stream.close();
			}
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