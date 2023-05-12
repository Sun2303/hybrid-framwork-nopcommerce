package eclipseTips;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotKeys {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	// String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void Register_01_Empty_Data() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.id("FirstName-error")).getText(), "First name is required.");
		Assert.assertEquals(driver.findElement(By.id("LastName-error")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "Password is required.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "sun" + rand.nextInt(9999) + "@gmail.com";
	}

}
