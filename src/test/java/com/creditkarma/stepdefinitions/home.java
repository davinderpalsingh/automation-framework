package com.creditkarma.stepdefinitions;

import com.creditkarma.pages.HomePage;

import cucumber.api.java.en.Given;

public class home {
	
	HomePage obj = new HomePage();
	
	@Given("^I am on the homepage$")
	public void i_am_on_the_homepage() throws Throwable {
		obj.navigateToHomePage();
	}

}
