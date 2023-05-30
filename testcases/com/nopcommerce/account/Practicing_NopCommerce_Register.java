package com.nopcommerce.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Practicing_NopCommerce_Register {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	private String emailAddress = getEmailRandom();
	private String passWord = "suncd@123";
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
	}
	
	@Test
	public void Register_01_With_Empty_Data() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getFirstNameErrorMessageText(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessageText(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessageText(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "Password is required.");
	}
	
	@Test
	public void Register_02_With_Invalid_Email() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.enterToFirstName("Sun");
		registerPage.enterToLastName("Beos U Nu");
		registerPage.enterToEmailTextBox("emailTest@gmail@vn");
		registerPage.enterToPassWordTextBox(passWord);
		registerPage.enterToConfirmPassWordTextBox(passWord);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Wrong email");
	}
	
	@Test
	public void Register_03_User_Successfully() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.enterToFirstName("Sun");
		registerPage.enterToLastName("Beos U Nu");
		registerPage.enterToEmailTextBox(emailAddress);
		registerPage.enterToPassWordTextBox(passWord);
		registerPage.enterToConfirmPassWordTextBox(passWord);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegistrationSuccessMessageText(), "Your registration completed");
	}
	
	@Test
	public void Register_04_User_Existing_Email() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.enterToFirstName("Sun");
		registerPage.enterToLastName("Beos U Nu");
		registerPage.enterToEmailTextBox(emailAddress);
		registerPage.enterToPassWordTextBox(passWord);
		registerPage.enterToConfirmPassWordTextBox(passWord);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getEmailErrorMessageText(), "The specified email already exists");
	}
	
	@Test
	public void Register_05_User_With_Invalid_Password() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.enterToFirstName("Sun");
		registerPage.enterToLastName("Beos U Nu");
		registerPage.enterToEmailTextBox(emailAddress);
		registerPage.enterToPassWordTextBox("pass");
		registerPage.enterToConfirmPassWordTextBox("pass");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getPasswordErrorMessageText(), "Password must meet the following rules:\\nmust have at least 6 characters");
	}
	
	@Test
	public void Register_06_User_With_Invalid_ConfirmPassword() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.enterToFirstName("Sun");
		registerPage.enterToLastName("Beos U Nu");
		registerPage.enterToEmailTextBox(emailAddress);
		registerPage.enterToPassWordTextBox("PassWord123");
		registerPage.enterToConfirmPassWordTextBox("password123");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "The password and confirmation password do not match.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public String getEmailRandom() {
		Random rand = new Random();
		return "sun" + rand.nextInt(99999) + "@mailinator.com";
		
	}
	
	
	
}
