package com.salesforceCucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/Features/SDFCwithScenarioOutlineAndDataTables.feature",
glue = "com.salesforceCucumber.stepDefinition",//strict = true,
//Use tags to run only some scenarios.'Or' is used to run scenarios with atleast one of the mentioned tags
//'And' is used to run scenarios only if scenario has all the tags mentioned 
//tags="@ValidLoginTrialMessage or @InValidLogin", 
tags="not @ResetPassword",
monochrome = true,
plugin={"pretty","html:target/cucumber-reports/cucumber.html",
"json:target/cucumber-reports/cucumber.json" }, 	

dryRun=false

)

public class RunnerFile extends AbstractTestNGCucumberTests{
	

}
