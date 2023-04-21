package webdriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		
	}
	
	

	@Test
	public void TC_01_() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		driver.get("https://kenh14.vn/");
		driver.quit();
	}

	@Test
	public void TC_02_() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://kenh14.vn/");
		driver.quit();
	}

	@Test
	public void TC_03_() {
		System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		driver = new EdgeDriver();
		driver.get("https://kenh14.vn/");
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}