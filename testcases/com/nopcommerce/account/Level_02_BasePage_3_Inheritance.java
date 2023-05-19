package com.nopcommerce.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_BasePage_3_Inheritance extends BasePage {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Register_01_Empty_Data() {
		openPageUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"),
				"First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		openPageUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//input[@id='gender-male']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Sun");
		sendKeyToElement(driver, "//input[@id='LastName']", "Beos");

		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "23");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "November");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1990");

		sendKeyToElement(driver, "//input[@id='Email']", "sunbeo@mail@test");
		sendKeyToElement(driver, "//input[@id='Password']", "mothaiba@123");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "mothaiba@123");

		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

	}

	@Test
	public void Register_03_Invalid_Password() {
		openPageUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//input[@id='gender-male']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Sun");
		sendKeyToElement(driver, "//input[@id='LastName']", "Beos");

		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "23");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "November");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1990");

		sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		sendKeyToElement(driver, "//input[@id='Password']", "motha");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "motha");

		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
		
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		openPageUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//input[@id='gender-male']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Sun");
		sendKeyToElement(driver, "//input[@id='LastName']", "Beos");

		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "23");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "November");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1990");

		sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		sendKeyToElement(driver, "//input[@id='Password']", "mothaiba@123");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "mothaba@456");

		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		openPageUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//input[@id='gender-male']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Sun");
		sendKeyToElement(driver, "//input[@id='LastName']", "Beos");

		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "23");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "November");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1990");

		sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		sendKeyToElement(driver, "//input[@id='Password']", "mothaiba@123");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "mothaba@123");

		clickToElement(driver, "//button[@id='register-button']");
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
