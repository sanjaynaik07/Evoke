package com.RLH.Runner;

import java.io.File;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


	@RunWith(Cucumber.class)
	@CucumberOptions(
			features = "C:\\Users\\sdhanavath\\eclipse-workspace\\RedLionHotels\\src\\main\\java\\com\\RLH\\Features", //the path of the feature files
			glue={"com\\RLH\\StepDefinitions"}, //the path of the step definition files
			plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
			//format= {"pretty","html:test-outout", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"}, //to generate different types of reporting
			monochrome = true, //display the console output in a proper readable format
			strict = true, //it will check if any step is not defined in step definition file
			dryRun = false //to check the mapping is proper between feature file and step def file
			//tags = {"~@SmokeTest" , "~@RegressionTest", "~@End2End"}			
			)

	




public class TestRunner {
	@AfterClass
    public static void setup() {
		
       Reporter.loadXMLConfig(new File("C:\\Users\\sdhanavath\\eclipse-workspace\\RedLionHotels\\src\\main\\java\\config\\extent-config.xml"));
		//extent=new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		// extent.loadConfig(new File("C:\\Users\\sdhanavath\\eclipse-workspace\\FreeCrmBDDFramework\\src\\main\\java\\config\\extent-config.xml"));
		
}

}
