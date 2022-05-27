#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: SFDC Login
  I want to use this template for my feature file

  Scenario: Login Error Message1 - To validate if correct error message is displayed on login without password
    Given User goes to  "https://login.salesforce.com"
    When User is on "LoginPage"
    When User enters userName as "User@gmail.com"
    And enters password as ""
    And clicks on the loginButton
    Then "Please enter your password." message should be displayed
    
   Scenario: Login To SalesForce2 - Login with valid data and check for free trial message
    Given User goes to  "https://login.salesforce.com"
     When User is on "LoginPage"
    When User enters userName as "firstlast@tekarch.com"
    And enters password as "salesforce5"
    And clicks on the loginButton
    And User is on "HomePage"
    Then "Welcome to your free trial should be displayed" title should be displayed
    
    Scenario: Check RemeberMe3 - Login with valid data and remember me checked should retain username and check box selection after logout
 		Given User goes to  "https://login.salesforce.com"
 		 When User is on "LoginPage"
    When User enters userName as "firstlast@tekarch.com"
    And enters password as "salesforce5"
    And selects the remember me checkbox
    And clicks on the loginButton
    And User is on "HomePage"
    Then "Home Page ~ Salesforce - Developer Edition" title should be displayed
    When user clicks on logout under userMenu drop down
    And User is on "LoginPage"
    Then "Login | Salesforce" title should be displayed
    And user name field should have "firstlast@tekarch.com"
    And remember me checkbox should be selected
    
    
    Scenario: Forgot Password4A - To validate password reset
 	  Given User goes to  "https://login.salesforce.com"
 	  When User is on "LoginPage"
    When User clicks on Forgot password link
    When User is on "ForgotPasswordPage"
    Then "Forgot Your Password | Salesforce" title should be displayed
    Given User enters forgot password page username as "firstlast@tekarch.com"
    And clicks on continue button
    Then "We’ve sent you an email with a link to finish resetting your password." password reset message is displayed
    
    Scenario:Forgot Password4B - Enter invalid user name and password, validate login fail 
   	Given User goes to  "https://login.salesforce.com"
   	When User is on "LoginPage"
    When User enters userName as "123"
    And enters password as "22131"
    And clicks on the loginButton
    Then "Please check your username and password. If you still can't log in, contact your Salesforce administrator." message should be displayed
     
    	 