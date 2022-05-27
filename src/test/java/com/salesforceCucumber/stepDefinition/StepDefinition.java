package com.salesforceCucumber.stepDefinition;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import com.salesforce.homepages.HomePage;
import com.salesforce.loginpages.ForgotPasswordPage;
import com.salesforce.loginpages.LoginPage;
import com.salesforceCucumber.base.BaseMethods;
import com.salesforceCucumber.base.BasePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinition extends BaseMethods{
	LoginPage login;
	HomePage home;
	ForgotPasswordPage fpp;
	
	
	@Before
	public void setUp() {
		launchAndOpenBrowser("chrome");
	}
	//tags can be included in before and after to make the before to run for only those scenarios.	
	@After//("@CheckRememberMe or @InValidLogin")
	public void tearDown() {
		driver.quit();
	}
	@Given("User goes to  {string}")
	public void user_goes_to(String url) {
		BasePage base = new BasePage(driver);
		base.goToUrl(url);
	}
	
	@When("User is on {string}")
	public void user_is_on(String pageName) {
	    switch(pageName.toLowerCase()) {
	    case "loginpage":
	    	 login = new LoginPage(driver);
	    	break;
	    case "homepage":
	    	 home = new HomePage();
	    	break;
	    case "forgotpasswordpage":
	    	 fpp = new ForgotPasswordPage(driver);
	    	break;
	    default:
	    	System.out.println("Invalid page name");
	    	
	    }
	}
	
	@When("User enters userName as {string}")
	public void user_enters_user_name_as(String userNameValue) {
	    login.enterUserName(userNameValue);	    
	}

	@When("enters password as {string}")
	public void enters_password_as(String passwordValue) {
		 login.enterPassword(passwordValue);
	}
	@When("User enters valid credentials")
	public void user_enters_valid_credentials(DataTable dataTable) {
		List<List<String>> credentials = dataTable.asLists();
		String userNameValue = credentials.get(0).get(0);
		login.enterUserName(userNameValue);
		String passwordValue = credentials.get(0).get(1);
		login.enterPassword(passwordValue);
		
	}
	@When("User enters valid login credentials")
	public void user_enters_valid_login_credentials(DataTable dataTable) {
		List<Map<String,String>> credentials = dataTable.asMaps();
		String userNameValue = credentials.get(0).get("userName");
		login.enterUserName(userNameValue);
		String passwordValue = credentials.get(0).get("password");
		login.enterPassword(passwordValue);
	}

	@When("clicks on the loginButton")
	public void clicks_on_the_login_button() {
		 login.clickOnLoginButton();
	}

	@Then("{string} message should be displayed")
	public void should_be_displayed(String expectedMessage) throws InterruptedException {
		Thread.sleep(2000);	    
	    String actualMessage = login.getErrorText();
	    Assert.assertEquals(actualMessage, expectedMessage);
	    
	}
	@When("selects the remember me checkbox")
	public void selects_the_remember_me_checkbox() {
		login.selectRememberMeCheckBox(true);
	}

	@Then("{string} title should be displayed")
	public void title_should_be_displayed(String expectedTitle) throws InterruptedException {
		Thread.sleep(5000);
	    String actualTitle = login.getPageTitle();
	    Assert.assertEquals(actualTitle, expectedTitle);
	}

	@When("user clicks on logout under userMenu drop down")
	public void user_clicks_on_logout_under_user_menu_drop_down() {
	     login.logoutSalesForce();
	}

	@Then("user name field should have {string}")
	public void user_name_field_should_have(String expectedName) throws InterruptedException {
		Thread.sleep(2000);
	   String actualText = login.getTextValuePresentInUserName();
	   Assert.assertEquals(actualText, expectedName);
	}

	@Then("remember me checkbox should be selected")
	public void remember_me_checkbox_should_be_selected() {
		Assert.assertTrue(login.checkifSelected(login.rememberMeCheckBox));
	}
	
	@When("User clicks on Forgot password link")
	public void click_on_forgot_password_link() throws InterruptedException {
		Thread.sleep(2000);
		login.clickOnForgotYourPasswordLink();
	}
	
	@When("User enters forgot password page username as {string}")
	public void enter_username_in_forgot_password_field(String username) throws InterruptedException {
		Thread.sleep(2000);
		fpp.enterUserName(username);
	}
	@When("clicks on continue button") 
	public void clicks_onContinue_button() {
		fpp.clickOnContinueButton();
	}
	@Then("{string} password reset message is displayed")
	public void password_reset_message_is_displayed(String expectedMessage) {
		String actualMessage = fpp.getText(fpp.passwordResetMessage);
		Assert.assertEquals(actualMessage, expectedMessage);
	}
}
