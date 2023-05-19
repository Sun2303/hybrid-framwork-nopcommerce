package com.nopcommerce.account;

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

//Class này vi phạm nguyên tắc DRY

public class Level_01_DRY {
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

	@Test
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

	@Test
	public void Register_02_Invalid_Email() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#gender-male")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Sun Beos");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Sunbeos123");
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("11");
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("November");
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText("1990");
		driver.findElement(By.id("Email")).sendKeys(getEmailRandom());
		driver.findElement(By.id("Password")).sendKeys("mothaiba@123");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("mothaiba@123");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Wrong email");

	}

	@Test
	public void Register_03_Invalid_Password() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#gender-male")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Sun");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Lee");
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("11");
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("November");
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText("1990");
		driver.findElement(By.id("Email")).sendKeys(getEmailRandom());
		driver.findElement(By.id("Password")).sendKeys("mot3");
		driver.findElement(By.id("Email")).sendKeys("mot3");
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#gender-male")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Sun");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Lee");
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("11");
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("November");
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText("1990");
		driver.findElement(By.id("Email")).sendKeys(getEmailRandom());
		driver.findElement(By.id("Password")).sendKeys("mothaiba123");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("mothaibaaaaa");
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(),
				"The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#gender-male")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Sun");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Lee");
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("11");
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("November");
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText("1990");
		driver.findElement(By.id("Email")).sendKeys(getEmailRandom());
		driver.findElement(By.id("Password")).sendKeys("mothaiba123");
		driver.findElement(By.id("Email")).sendKeys("mothaiba123");

		driver.findElement(By.cssSelector("button#register-button")).click();
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
