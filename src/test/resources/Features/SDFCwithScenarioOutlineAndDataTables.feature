Feature: SFDC Login page 
Background: 
  Given User goes to  "https://login.salesforce.com"
  When User is on "LoginPage"
  
 @InValidLogin 
Scenario Outline: Login Error Message1 - To validate Login with invalid credentials
    When User enters userName as <userName>
    And enters password as <password>
    And clicks on the loginButton
    Then <message> message should be displayed
    Examples:
   	|userName				 |password|message											|
   	|"User@gmail.com"|""			|"Please enter your password."|
   	|"123"				 	 |"22131"	  |"Please check your username and password. If you still can't log in, contact your Salesforce administrator."| 	
  
  @ValidLoginTrialMessage  
   Scenario: Login To SalesForce2 - Login with valid data and check for free trial message
    Given User goes to  "https://login.salesforce.com"
     When User is on "LoginPage"
    When User enters valid login credentials
    #Here the first row is defines the values, so can be used as a key and MAps can be used to get the data
    |userName|password|
    |firstlast@tekarch.com|salesforce5|
    And clicks on the loginButton
    And User is on "HomePage"
    Then "Welcome to your free trial should be displayed" title should be displayed
    
    @CheckRememberMe
    Scenario Outline: Check RemeberMe3 - Login with valid data and remember me checked should retain username and check box selection after logout
 		Given User goes to  "https://login.salesforce.com"
 		 When User is on "LoginPage"
   	When User enters valid credentials
   	#Here we get it as a list of lists to get the values  one by one
    |firstlast@tekarch.com|salesforce5|
    And selects the remember me checkbox
    And clicks on the loginButton
    And User is on "HomePage"
    Then <HomePageTitle> title should be displayed
    When user clicks on logout under userMenu drop down
    And User is on "LoginPage"
    Then  <LoginTitle> title should be displayed
    And user name field should have <UserName>
    And remember me checkbox should be selected
    Examples:
    |HomePageTitle|LoginTitle|UserName|
    |"Home Page ~ Salesforce - Developer Edition"|"Login \| Salesforce"|"firstlast@tekarch.com"|
    
    
    @ResetPassword
    Scenario: Forgot Password4A - To validate password reset
 	  Given User goes to  "https://login.salesforce.com"
 	  When User is on "LoginPage"
    When User clicks on Forgot password link
    When User is on "ForgotPasswordPage"
    Then "Forgot Your Password | Salesforce" title should be displayed
    Given User enters forgot password page username as "firstlast@tekarch.com"
    And clicks on continue button
    Then "We’ve sent you an email with a link to finish resetting your password." password reset message is displayed
    
    Scenario: to check how summary plugin works
    Given user enters url "http://www.google.com"
    When user searches "cucumber"
    
   