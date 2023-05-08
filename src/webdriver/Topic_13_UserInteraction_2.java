package webdriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_UserInteraction_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	

	//@Test
	public void TC_01_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// get 12 items
		List<WebElement> numberList = driver.findElements(By.cssSelector("ol.ui-selectable>li"));
		//click and hold 1st element
		action.clickAndHold(numberList.get(0))
		// move to 8 element
		.moveToElement(numberList.get(7))
		//release chuot trai
		.release();
		sleepInSecond(3);
		List<WebElement> selectedNumberList = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
		Assert.assertEquals(selectedNumberList.size(),8);
	}

	@Test
	public void TC_02_Click_and_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		Keys key = Keys.NULL;
		if (osName.contains("Window")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		// get 12 items
		List<WebElement> numberList = driver.findElements(By.cssSelector("ol.ui-selectable>li"));
		//nhan ctrl xuong
		action.keyDown(key).perform();
		//click chon so random
		action.click(numberList.get(0))
		.click(numberList.get(6))
		.click(numberList.get(11))
		.perform();
		// nha phim ctrl ra
		action.keyUp(key).perform();
		sleepInSecond(3);
		List<WebElement> selectedNumberList = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
		Assert.assertEquals(selectedNumberList.size(),3);
	}

	@Test
	public void TC_03_Hover_fahasa() {
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
		driver.quit();
	}
}