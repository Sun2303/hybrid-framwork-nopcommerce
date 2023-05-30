package com.nopcommerce.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import pageUIs.LoginPageUI;

public class Practicing_NopCommerce_Login {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();
	private String passWord = "suncd@123";
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		loginPage = new LoginPageObject(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
	}
	
	@Test
	public void Login_01_With_Empty_Data() {
		loginPage.clickToElement(driver, "//button[@class='button-1 login-button']");
		Assert.assertEquals(loginPage.getElementText(driver, LoginPageUI.EMAIL_ERROR_MSG), "Please enter your email");
	}
	
	@Test
	public void Login_02_With_Invalid_Email() {
		loginPage.enterToEmailTextBox("sunemail@com.vn@123");
		loginPage.enterToPassWordTextBox(passWord);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getElementText(driver, LoginPageUI.EMAIL_ERROR_MSG), "Wrong email");
	}
	
	@Test
	public void Login_03_With_Email_Not_Exist() {
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.enterToPassWordTextBox(passWord);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getElementText(driver, LoginPageUI.VALIDATION_ERROR_MSG), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void Login_04_With_Empty_Password() {
		loginPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.enterToFirstName("Sun CD");
		registerPage.enterToLastName("3107");
		registerPage.enterToEmailTextBox(emailAddress);
		registerPage.enterToPassWordTextBox(passWord);
		registerPage.enterToConfirmPassWordTextBox(passWord);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegistrationSuccessMessageText(), "Your registration completed");
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getElementText(driver, LoginPageUI.VALIDATION_ERROR_MSG), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_With_Wrong_Password() {
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.enterToPassWordTextBox("password132");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getElementText(driver, LoginPageUI.VALIDATION_ERROR_MSG), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_06_Successfully() {
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.enterToPassWordTextBox(passWord);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		homePage.clickToMyAccountLink();
		customerPage = new CustomerPageObject(driver);
		Assert.assertEquals(customerPage.getFristNameTextBoxAttributeValue(),"Sun CD");
		Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(),"3107");
		Assert.assertEquals(customerPage.getEmailAddressTextBoxAttributeValue(),emailAddress);
		
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
