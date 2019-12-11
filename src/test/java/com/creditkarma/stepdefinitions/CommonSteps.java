package com.creditkarma.stepdefinitions;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;

import com.creditkarma.pages.HomePage;
import com.creditkarma.pages.SettingsPage;
import com.creditkarma.utils.DbConnect;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/*
 * As the name suggests, this class will have definitions for all the steps which are common and will be
 * used in multiple feature files.
 */

public class CommonSteps {
	HomePage homePageObj = new HomePage();
	SettingsPage settingsPageObj = new SettingsPage();
	DbConnect dbConnectObj = new DbConnect();


	@Given("^I am on ABC application homepage$")
	public void i_am_on_ABC_application_homepage(){
		homePageObj.navigateToHomePage();
	}

	@When("^I hover over the left nav bar$")
	public void i_hover_over_the_left_nav_bar() throws InterruptedException{
		homePageObj.hoverOverLeftNav();
	}

	@When("^I click on \"([^\"]*)\" sub-option under \"([^\"]*)\" main option$")
	public void i_click_on_sub_option_under_main_option(String subMenuItem, String mainMenuItem) throws InterruptedException{
		homePageObj.clickMenuItem(subMenuItem, mainMenuItem);
	}

	@Then("^I should be navigated to \"([^\"]*)\" landing page$")
	public void i_should_be_navigated_to_landing_page(String pageType){
		switch(pageType) {
		case "Auto Loans":
			/*
			 * ToDo in future.
			 */
			break;
		case "Card Settings":
			Assert.assertTrue(settingsPageObj.validateLandingPage(pageType),
					"Failing test scenario. Card Settings landing page is not loaded as expected.");
			break;
		case "Home Loan":
			/*
			 * ToDo in future.
			 */
			break;
		case "Personal Loan":
			/*
			 * ToDo in future.
			 */
			break;
		default:
			Assert.fail("This landing page is unexpected. Failing this test scenario.");
			break;
		}
	}

	@When("^I click on Cards dropdown on \"([^\"]*)\" page$")
	public void i_click_on_Cards_dropdown_on_page(String pageType) throws InterruptedException{
		switch(pageType) {
		case "Credit Cards":
			/*
			 * ToDo in future.
			 */
			break;
		case "Travel Cards":
			/*
			 * ToDo in future.
			 */
			break;
		default:
			Assert.fail("Unexpected page is loaded. Failing this test scenario.");
			break;
		}
	}

	@Then("^I should see a list of all the loan types on \"([^\"]*)\" page$")
	public void i_should_see_a_list_of_all_the_loan_types_on_page(String pageType) throws Throwable {
		switch(pageType) {
		case "Home Loan":
			/*
			 * ToDo in future.
			 */
			break;
		case "Travel Loan":
			/*
			 * ToDo in future.
			 */
			break;
		default:
			Assert.fail("Unexpected page is loaded. Failing this test scenario.");
			break;
		}
	}

	@When("^I select a random loan type on \"([^\"]*)\" page$")
	public void i_select_a_random_loan_type_on_page(String pageType){
		switch(pageType) {
		case "Fixed":
			/*
			 * ToDo in future.
			 */
			break;
		case "Reducing":
			/*
			 * ToDo in future.
			 */
			break;
		default:
			Assert.fail("Unexpected page is loaded. Failing this test scenario.");
			break;
		}
	}


	@Then("^Cards drop down on \"([^\"]*)\" page should be populated with correct card types$")
	public void cards_drop_down_on_page_should_be_populated_with_correct_card_types(String pageType)
			throws ClassNotFoundException, SQLException{
		String selectedCardType = "";
		switch(pageType) {
		case "Credit Card":
			break;
		case "Travels Card":
			break;
		default:
			Assert.fail("Unexpected page is loaded. Failing this test scenario.");
			break;
		}

		dbConnectObj.connectToSpecificDb(selectedCardType);
		List<String> actualCardCodes = dbConnectObj.getActualCardTypeValues(selectedCardType);
		List<String> expCardCodes = dbConnectObj.getActualCardTypeValues(selectedCardType);
		Collections.sort(expCardCodes);
		Collections.sort(actualCardCodes);
		Assert.assertEquals(expCardCodes, actualCardCodes, "Invalid shifts displayed for " + selectedCardType
				+ ". Failing test scenario.");        
	}

	@When("^I select a random card on \"([^\"]*)\" page$")
	public void i_select_a_random_card_on_page(String pageType){
		switch(pageType) {
		case "visa":
			break;
		case "mastercard":
			break;
		default:
			Assert.fail("Unexpected page is loaded. Failing this test scenario.");
			break;
		}
	}
}