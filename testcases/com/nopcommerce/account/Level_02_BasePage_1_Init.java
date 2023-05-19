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

import commons.BasePage;

//Vi phạm nguyên tắc: Không khởi tạo trực tiếp đối tượng trên class test

public class Level_02_BasePage_1_Init {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private BasePage basePage = new BasePage();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Register_01_Empty_Data() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"),
				"First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//input[@id='gender-male']");

		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Sun");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Beos");

		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "23");
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "November");
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1990");

		basePage.sendKeyToElement(driver, "//input[@id='Email']", "sunbeo@mail@test");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "mothaiba@123");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "mothaiba@123");

		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

	}

	@Test
	public void Register_03_Invalid_Password() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//input[@id='gender-male']");

		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Sun");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Beos");

		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "23");
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "November");
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1990");

		basePage.sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "motha");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "motha");

		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
		
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//input[@id='gender-male']");

		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Sun");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Beos");

		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "23");
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "November");
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1990");

		basePage.sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "mothaiba@123");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "mothaba@456");

		basePage.clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//input[@id='gender-male']");

		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Sun");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Beos");

		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "23");
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "November");
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1990");

		basePage.sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "mothaiba@123");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "mothaba@123");

		basePage.clickToElement(driver, "//button[@id='register-button']");
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
