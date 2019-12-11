package com.creditkarma.runner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.creditkarma.utils.BaseUI;
import com.creditkarma.utils.DataCreation;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(glue = "com/creditkarma/stepdefinitions",
features = "src/test/resources/features",
tags = "@wip")

public class TestNGRunCucumberTest extends AbstractTestNGCucumberTests{

	BaseUI obj = new BaseUI();
	Map<String,String> prop = new HashMap<>();
	private final String allureEnvPropFileName = "environment.properties";
	private final String allureEnvPropRelPath = "/target/allure-results/";

	@BeforeTest
	@Parameters({"testUrl","runEnv","buildUrl"})
	public void setParameters(@Optional("testingstring") String testUrl, 
			@Optional("testingBuildUrl") String runEnv, @Optional("testingTestdUrl") String buildUrl ) {
	
		System.out.println("Value stored in testUrl is -->" + testUrl);
		System.out.println("Value stored in runEnv is -->" + runEnv);
		System.out.println("Value stored in buildUrl is -->" + buildUrl);
		
		prop.put("TestSite", testUrl);
		prop.put("Env", runEnv);
		prop.put("BuildNo", buildUrl);
	}

	@BeforeMethod
	public void setUp() {
		/*
		* @BeforeMethod annotated method will be run before each test method.
		*/
		obj.setBaseConfigurations();
	}

	@AfterMethod
	public void cleanUp() {
		/*
		* @AfterMethod annotated method will be run after each test method.
		*/
		obj.doCleanUp();
	}

	@AfterTest
	public void createAllureEnvPropertiesFile() throws IOException{
		/*
		* @AfterTest annotated method will be run after all the test methods belonging to
		* the classes inside the <test> tag have run.
		*/
		DataCreation object = new DataCreation();
		
		//Create environment.properties file in allure-results directory
		object.createPropertyFile(allureEnvPropFileName, allureEnvPropRelPath, prop);
	}
}