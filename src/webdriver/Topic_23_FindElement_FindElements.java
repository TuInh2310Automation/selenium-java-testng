package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_FindElement_FindElements {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// Aplly 15s cho viec tim elements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_() {
		// Tim thay duy nhat 1 element/node
		// tim thay va thao tac truc tiep len node do
		// vi no tìm thấy nên ko phari cho timeout 15s
		driver.findElement(By.cssSelector("input#email"));

		// Tim thay nhieu hon 1 element/node
		// No se thao tac voi node dau tien
		// Ko quan tam cac node con lai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("memeo@gmai.com");
		// Ko tim thay element/node nao
		// co chế 0.5 s tìm lại 1 lần
		// Nếu trong thời gian tìm lại mà thấy elements thì thỏa mãn đk => Pass
		// Nếu hết thoi gian này( 15s) ma van ko tim thay element thì
		// đánh fail tc tại step này
		// Throw ra 1 ngoại lê: NoSuchElementException
		driver.findElement(By.cssSelector("input[type='check']"));
	}

	@Test
	public void TC_02_() {
		// Tim thay duy nhat 1 element/node
		// tim thay va luu no vao list = 1 element
		// vi no tim thay nen ko can phai cho het 15s timeout
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elements.size());

		// - tim thay nhieu hon 1 element/node
		// tim thay va luu no vao list = element tuong ung
		elements = driver.findElements(By.cssSelector("input[type='email"));
		System.out.println("List element number = " + elements.size());

		// Ko tim thay element/node nao
		// co chế 0.5 s tìm lại 1 lần
		// Nếu trong thời gian tìm lại mà thấy elements thì thỏa mãn đk => Pass
		// Nếu hết thoi gian này( 15s) ma van ko tim thay element thì
		// + ko danh fail tc ma chayj step ke tiep
		// Tra lai 1 list rong (empty)
		elements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("List element number = " + elements.size());
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}