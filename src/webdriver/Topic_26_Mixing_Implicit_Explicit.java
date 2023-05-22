package webdriver;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Mixing_Implicit_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

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
	public void TC_01_Element_Found() {
		//Element co xuat hien va ko can cho het timeout
		// Du co set ca 2 loai wait thi ko anh huong
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		
		//Implicit: chi ap dung cho viec findElement/ findElements
		//Explicit Wait: Cho cac dieu kien cua Element
		
		driver.get("https://www.facebook.com/");
		
		//Explicit
		System.out.println("explicit start" + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("explicit end" + getTimeStamp());
		
		System.out.println("implicit start" + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("implicit end" + getTimeStamp());
	}

	//@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		
		System.out.println("implicit start" + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("implicit end" + getTimeStamp());
		}
	}

	//@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit_I() {
		// 3.1 Implicit = Explicit
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		//Explicit
		System.out.println("explicit start" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("explicit end" + getTimeStamp());
		}
		
		
	}
	
	//@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit_II() {
		// 3.3 Implicit = Explicit
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		//Explicit
		System.out.println("explicit start" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("explicit end" + getTimeStamp());
		}
		
		
	}
	//@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit_III() {
		// 3.3 Implicit > Explicit
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		//Explicit
		System.out.println("explicit start" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("explicit end" + getTimeStamp());
		}
			
	}
	
	//@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		//Explicit
		System.out.println("explicit start" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("explicit end" + getTimeStamp());
		}
			
	}
	
	@Test
	public void TC_05_Element_Not_Found_Explicit_Element() {
		
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		//Explicit
		System.out.println("explicit start" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("explicit end" + getTimeStamp());
		}
			
	}
	
	public String getTimeStamp() {
		Date datetime = new Date();
		return datetime.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}