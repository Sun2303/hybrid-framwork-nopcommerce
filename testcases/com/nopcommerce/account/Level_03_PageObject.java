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
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_03_PageObject extends BasePage {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;

	private String emailAddress = getEmailRandom();
	private String passWord = "mothaba@123";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");

		// Mở Url nó ở page nào thì khởi tạo page đó lên
		// Từ page này chuyển qua page kia cũng khởi tạo page đó lên
		homePage = new HomePageObject(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void User_01_Register_Empty_Data() {
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
	public void User_02_Register_Invalid_Email() {
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
	public void User_03_Register_Invalid_Password() {
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

		Assert.assertEquals(registerPage.getPasswordErrorMessageText(),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void User_04_Register_Incorrect_Confirm_Password() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);

		homePage.clickToRegisterLink();

		registerPage = new RegisterPageObject(driver);

		registerPage.enterToFirstName("Sun");
		registerPage.enterToLastName("Beos U Nu");
		registerPage.enterToEmailTextBox(emailAddress);
		registerPage.enterToPassWordTextBox(passWord);
		registerPage.enterToConfirmPassWordTextBox("passWord123");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),
				"The password and confirmation password do not match.");

	}

	@Test
	public void User_05_Register_Success() {
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
	public void User_06_Login_Success() {
		registerPage.clickToNopCommerceLogo();

		homePage = new HomePageObject(driver);
		homePage.clickToLoginLink();

		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.enterToPassWordTextBox(passWord);
		loginPage.clickToLoginButton();

		homePage = new HomePageObject(driver);
		homePage.clickToMyAccountLink();

		customerPage = new CustomerPageObject(driver);

		// Verify
		Assert.assertEquals(customerPage.getFristNameTextBoxAttributeValue(), "Sun");
		Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(), "Beos U Nu");
		Assert.assertEquals(customerPage.getEmailAddressTextBoxAttributeValue(), emailAddress);
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
