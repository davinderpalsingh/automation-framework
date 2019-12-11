package com.creditkarma.stepdefinitions;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.creditkarma.pages.HomePage;
import com.creditkarma.pages.SettingsPage;
import com.creditkarma.utils.DataCreation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Settings {
	HomePage homePageObj = new HomePage();
	SettingsPage obj = new SettingsPage();
	CommonSteps commonStepsObj = new CommonSteps();
	DataCreation dataCreationObj = new DataCreation();

	private String clickedBtnTxt = "";

	@Given("^I am on Card Apply landing page$")
	public void i_am_on_card_apply_landing_page() throws Throwable {
		homePageObj.navigateToHomePage();
		homePageObj.hoverOverLeftNav();
		Assert.assertFalse(homePageObj.clickMenuItem("Loans", "Personal"), "Invalid menu option."
				+ "Failing this test scenario.");
	}

	@Then("^I should see \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" buttons$")
	public void i_should_see_and_buttons(String planGroups, String orderTypes, String workMixGroups){
		Assert.assertFalse(obj.validateExpectedButtonsArePresent(), "One of the buttons"
					+ "out of Credit Cards, Loans and Investment is missing on card settings page."
					+ " Failing this test scenario.");
	}

	@When("^I click on \"([^\"]*)\" button$")
	public void i_click_on_button(String btnText) throws InterruptedException{
		Assert.assertTrue(obj.clickOnButton(btnText), "Button is not clicked due to some reason. Please"
				+ " check. Failing this test scenario.");
		clickedBtnTxt = btnText;
	}

	@Then("^I should see( Credit Cards| Loans| Investment Groups) table populated with expected column headings$")
	public void i_should_see_Plan_Groups_table_populated_with_expected_column_headings(String tblName){
		Assert.assertFalse(obj.validateTableColumnHeaders(clickedBtnTxt), "One of the column heading in "
				+ tblName +" table is not as expected. Failing this test scenario.");
	}

	@Then("^I should be able to validate Dropdown values for \"([^\"]*)\" and \"([^\"]*)\" columns against provided data for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Dropdown_values_for_and_columns_against_provided_data_for_and(
			String columnName1, String columnName2, String workMixGrpDropdownValues, String levelTwoGroupDropdownValues){
		Assert.assertTrue(obj.validateDropDownValues(columnName1, workMixGrpDropdownValues), "Dropdown values for"
				+ " column " + columnName1 + " are not as expected. Failing this test scenario.");
		Assert.assertTrue(obj.validateDropDownValues(columnName2, levelTwoGroupDropdownValues), "Dropdown values for"
				+ " column " + columnName2 + " are not as expected. Failing this test scenario.");
	}

	@Then("^I should be able to validate \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" columns against provided data for \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" in \"([^\"]*)\" table$")
	public void i_should_be_able_to_validate_and_columns_against_provided_data_for_and_in_table(String planGrp, 
		String orderTypeRev, String chuteId, String planGrpVal, String orderTypeRevVal, String chuteIdVal, String tableName){
		Map<String,String> columnMap = new HashMap<String,String>();

		columnMap.put(planGrp, planGrpVal);
		columnMap.put(orderTypeRev, orderTypeRevVal);
		columnMap.put(chuteId, chuteIdVal);
		
		obj.validateColumnValues(columnMap, tableName);
	}

	@When("^I update any \"([^\"]*)\" column randomly$")
	public void i_update_any_column_randomly(String arg1) {
		int randomIndex = dataCreationObj.generateRandomNumber(1, 20);
	}

	@Then("^I should be able to validate updated value in \"([^\"]*)\" and \"([^\"]*)\" table$")
	public void i_should_be_able_to_validate_updated_value_in_and_table(String arg1, String arg2) {
	}
}