package webdriver;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	

	//@Test
	public void TC_01_jQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//1 Click vào 1 thẻ bất kì để n xổ hết ra các item của dropdown
		
		selectItemInDropDown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span[class='ui-selectmenu-text']")).getText(), "Slow");
		
		selectItemInDropDown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span[class='ui-selectmenu-text']")).getText(), "Faster");
		//3 T item xem có đúng cái đang cần hay ko
		//3.1 Nếu nó nằm trong khoảng nhìn thấy của user thì ko cần phải scroll 
		//3.2 Nếu nó nằm ko nằm trong khoảng nhìn thấy của user thì scroll xuống đến item đó
		
		// 4 Kiểm tra text của item xem đúng cái mình mình mong muốn
		
		//5 click vào item 
		
		selectItemInDropDown("span#salutation-button", "ul#salutation-menu div[role='option']']", "Prof");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span[class='ui-selectmenu-text']")).getText(), "Prof");
	}

	//@Test
	public void TC_02_ReactJs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropDown("i.dropdown.icon", "span.text", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
	}
	//@Test
	public void TC_03_VuJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropDown("li.dropdown-toggle", "ul.dropdown-menu li", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
	}
	
	@Test
	public void TC_04_Edittable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndSelectItemInDropDown("input.search", "span.text", "Andorra");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Andorra");
		
		enterAndSelectItemInDropDown("input.search", "span.text", "Benin");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");
	}

	public void selectItemInDropDown(String parentCss, String allItemCss, String expectedTextItem)
	{
		//1 Click vào 1 thẻ bất kì để n xổ hết ra các item của dropdown
		driver.findElement(By.cssSelector(parentCss)).click();
		
		//2 Chờ cho tất cả các item được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		List<WebElement> sppedDropdownItemsElements =  driver.findElements(By.cssSelector(allItemCss));
		for (WebElement tempItem : sppedDropdownItemsElements) {
			String  tempText = tempItem.getText().trim();
			System.out.println(tempText);
			if (tempText.equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}
	}
	
	public void enterAndSelectItemInDropDown(String textboxCss , String allItemCss, String expectedTextItem)
	{
		//1 Nhap expect vao, xo ra tat ca item matching 
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		//2 Chờ cho tất cả các item được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		List<WebElement> sppedDropdownItemsElements =  driver.findElements(By.cssSelector(allItemCss));
		for (WebElement tempItem : sppedDropdownItemsElements) {
			String  tempText = tempItem.getText().trim();
			System.out.println(tempText);
			if (tempText.equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}
	}
	public void sleepInSecond(long timeInSecond) {
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