package com.nopcommerce.user;

import org.testng.annotations.*;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.UserCustomerInforPageObject;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserLoginPageObject;
import pageObjects.user.nopCommerce.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Level_18_Pattern_Object extends BaseTest {

	@Parameters({"browser" ,"envName", "serverName", "ipAddress", "portNumber", "osName", "osName"})
	@BeforeClass
	public void beforeClass( String browserName, String envName, String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional( "Windows") String osName, @Optional("10") String osVersion){
		driver = getBrowserDriver(envName, serverName, browserName,  ipAddress,  portNumber,   osName,  osName );
		homePage = PageGeneratorManager.getPageGeneratorManager().getUserHomePage(driver);
		showBrowserConsoleLogs(driver);

		firstName = "curry";
		lastName = "pham";
		emailAddress = "curry" + generateFakeNumber() + "@gmail.com";
		password = "123456";
		day = "1";
		month = "January";
		year = "2000";
	}

	@Test
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register page'");
		registerPage = homePage.clickToRegister();
		showBrowserConsoleLogs(driver);

		registerPage.clickToRadioButtonByLabel(driver, "Female");

		log.info("Register - Step 02: Enter to First Name textbox with value is '" + firstName + "'");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("Register - Step 03: Enter to Last name textbox with value is '" + lastName + "'");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);

		registerPage.selectedDropdownByName(driver, "DateOfBirthDay", day);
		registerPage.selectedDropdownByName(driver, "DateOfBirthMonth", month);
		registerPage.selectedDropdownByName(driver, "DateOfBirthYear", year);

		log.info("Register - Step 04: Enter to Email Address textbox with value is '" + emailAddress + "'");
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);

		registerPage.clickToCheckboxByLabel(driver, "Newsletter:");

		log.info("Register - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "Password", password);

		log.info("Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register - Step 07: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");
		homePage = PageGeneratorManager.getPageGeneratorManager().getUserHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Register - Step 08: Verify register success mesage is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_02_Login");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Home page'");
		loginPage = homePage.openLoginPage();
		showBrowserConsoleLogs(driver);

		log.info("Login - Step 02: Enter to  Email Address textbox with value is '" + emailAddress + "'");
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("Login - Step 03: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Login - Step 04: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getPageGeneratorManager().getUserHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Login - Step 05: Verify login success mesage is displayed");
		Assert.assertEquals(homePage.getSuccessLogin(), "My account...");
	}

	@Test
	public void User_03_My_Account() {
		log.info("My_Account - Step 01: Navigate to 'My account' page");
		customerInforPage = homePage.clickToCustomerInfor();
		showBrowserConsoleLogs(driver);

		log.info("My_Account - Step 02: Verify 'Cutomer Infor' page is displayed");
		Assert.assertEquals(customerInforPage.getSuccessLogin(), "My account");

		log.info("My_Account - Step 03: Verify 'First name' page is displayed");
		Assert.assertEquals(customerInforPage.getTexboxValueByID(driver, "FirstName"), firstName);

		log.info("My_Account - Step 04: Verify 'Last name' page is displayed");
		Assert.assertEquals(customerInforPage.getTexboxValueByID(driver, "LastName"), lastName);

		log.info("My_Account - Step 05: Verify 'Email' page is displayed");
		Assert.assertEquals(customerInforPage.getTexboxValueByID(driver, "Email"), emailAddress);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String firstName, lastName, password, emailAddress, day, month, year;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;

}
