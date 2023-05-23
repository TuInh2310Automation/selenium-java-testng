package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_FluentWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	long allTime = 15;
	long pollingTime = 100;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();

	}

	//@Test
	public void TC_01_Fluent_WebDriver() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		findElement("//div[@id='start']/button").click();
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
	}

	@Test
		public void TC_02_Fluent_Element() {
			driver.get("https://automationfc.github.io/fluent-wait/");
			WebElement condountElement= findElement("//div[@id='javascript_countdown_time']");
			fluentElement=new FluentWait<WebElement>(condountElement);
			fluentElement.withTimeout(Duration.ofSeconds(allTime))
			.pollingEvery(Duration.ofMillis(pollingTime))
			.ignoring(NoSuchElementException.class);
			fluentElement.until(new Function<WebElement, Boolean>() {

				@Override
				public Boolean apply(WebElement element) {
					// TODO Auto-generated method stub
					String text= element.getText();
					System.out.println(text);
					
					return text.endsWith("00");
				}
			});
		}
	public WebElement findElement(String xpathLocator) {
		// Dung Fluent wait
		fluentDriver = new FluentWait<WebDriver>(driver);

		// Set tổng thời gian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(allTime))
				// 1/3s check 1 l
				.pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		// Apply dieu kien
		WebElement element = fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver t) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
		return element;
	}

	public String getTimeStamp() {
		Date datetime = new Date();
		return datetime.toString();
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}