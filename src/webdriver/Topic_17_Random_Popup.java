package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Random_Popup {
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

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Yêu cầu:
		// Random popup nên nó có thể hiển thị 1 cách ngẫu nhiên hoặc ko hiển thị
		// Nếu như nó hiển thị thì mình cần thao tác lên popup -> Đóng n đi để qua step
		// tiếp theo
		// Khi mà đóng nó lại thì có thể refresh trang nó hiện lên lại/ hoặc là ko// Nếu
		// như nó ko hiển thị thì qua step tiếp theo luôn
	}

	//@Test
	public void TC_01_Random_FixedInDOM_Java_Code_Geek() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(5);
		By lePopBy= By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		// vi no luon co trong DOM nen dung ham isDisplay duoc
		
		if (driver.findElement(lePopBy).isDisplayed()) {
			// Nhap email
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys("vananhleektmtk53@gmail.com");
			sleepInSecond(3);
			//driver.findElement(By.xpath("//a[data-label='Get the Books']")).click();
			driver.findElement(By.cssSelector("a[data-label='Get the Books'], [data-label='OK']")).click();
			sleepInSecond(8);
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(), "Thank you!");
			//dong popup
			sleepInSecond(8);
		}
		
		// Qua buoc tiep theo
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(10);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul#posts-container>li:first-child>div.post-details>h2.post-title")).getText(),"Agile Testing Explained");
	}

	//@Test
	public void TC_02_Random_FixedInDOM_Java_Code_Geek() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);
		By popUp = By.cssSelector("div#tve-p-scroller");
		if (driver.findElement(popUp).isDisplayed()) {
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			sleepInSecond(3);
		}
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(3);
		assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
		
	}

	@Test
	public void TC_03_Random_NotInDom() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(3);
		By popUp = By.cssSelector("section#popup");
		if (driver.findElements(popUp).size() > 0 && driver.findElements(popUp).get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Meomeo");
			sleepInSecond(2);
			driver.findElement(By.cssSelector("button#close-popup")).click();
		}
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(2);
		
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