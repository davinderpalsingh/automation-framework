package com.creditkarma.pages;

import java.util.Map;

import com.creditkarma.utils.BaseUI;
import com.creditkarma.utils.TableParser;

public class SettingsPage {

	BaseUI obj = new BaseUI();
	TableParser tObj = new TableParser();

	private static final String CREDIT_PLAN_GROUPS_BTN = "credit-plangroups";
	private static final String LOAN_ORDER_TYPES_BTN = "loan-ordertypes";
	private static final String CRAD_GROUPS_BTN = "crad-workmixgroups";
	private static final String CREDIT_PLAN_GROUPS_BTN_TXT = "Credit Plan Groups";
	private static final String LOAN_ORDER_TYPES_BTN_TXT = "Loan Order Types";
	private static final String APPROVAL_GROUPS_BTN_TXT = "Approval Groups";
	private static final String SETTINGS_PAGE_VALIDATOR = "/settings";
	private static final String CREDIT_PLAN_GROUPS_TABLE_ID = "plangroup-table";
	private static final String LOAN_ORDER_TYPES_TABLE_ID = "ordertype-table";
	private static final String TRAVEL_CARD_GROUPS_TABLE_ID = "XXXX";
	private static final String TABLE_COL_HDR_XPATH1 = "//table[@id='";
	private static final String TABLE_COL_HDR_XPATH2 = "']/theader[@class='theader-color']/tr";
	private static final String TR_PLAN_GROUPS_XPATH = "//td[@id='cardplangroup-table-mixgroup-10']/select";
	private static final String LOAN_GROUP_TBL_XPATH = "//td[@id='plangroup-table-level2group-0']/select";
	private static final String PLAN_GROUPS_TABLE_TBODY = "//table[@id='plangroup-table']/tbody";

	private String [] travelCardColumns = {"LVHM", "CARD GROUP ID", "AGE GROUP", "GENDER GROUP",
			"AMLE GROUP", "CREDIT ORDER TYPEs", "USER ID"};
	private String [] creditCardColumns = {"LVHM", "CARD TYPE", "CARD ATTRB ID", "TRAVEL GRP ID",
			"MEFAME GROUP", "STATUS"};
	private String [] creditTypeColumns = {"LVHM", "ID", "CARD GROUP ID", "BRIEFCSE ID",
			"AUTO IMMUNE BATCH ", "VISA TYPE", "MASTERCARD TYPE", "MWALLET", "GOOGLE PAY", "APPLE PAY"};

	/**
	 * This method validates if the page loaded is expected or not by searching for 
	 * sequence of characters in the current url.
	 * 
	 * @param pageType
	 * @return
	 */
	public boolean validateLandingPage(String pageType) {
		return (BaseUI.driver.getCurrentUrl().contains(SETTINGS_PAGE_VALIDATOR));
	}

	/**
	 * This method validates whether buttons expected on Settings page are present or not.
	 * It in turn calls BaseUI class method to check the presence of buttons.
	 * 
	 * @return	:	returns whether the buttons are present or missing on Settings page. 
	 */
	public boolean validateExpectedButtonsArePresent() {
		boolean buttonMissing = false;
		if(obj.getElementHandleById(CREDIT_PLAN_GROUPS_BTN) == null ||
				obj.getElementHandleById(LOAN_ORDER_TYPES_BTN) == null ||
				obj.getElementHandleById(CRAD_GROUPS_BTN) == null)
			buttonMissing = true;
		return buttonMissing;
	}

	/**
	 * As the name suggests, this method is responsible for clicking a button on the Settings landing page.
	 * It in turn calls BaseUI class method to do the actual job of clicking.
	 * 
	 * @param btnText	: this parameter helps us decide which button to click.
	 * @throws InterruptedException
	 */
	public boolean clickOnButton(String btnText){
		boolean btnClickSuccess = false;
		switch(btnText) {
		case CREDIT_PLAN_GROUPS_BTN_TXT:
			btnClickSuccess = obj.clickItem(CREDIT_PLAN_GROUPS_BTN);
			break;
		case LOAN_ORDER_TYPES_BTN_TXT:
			btnClickSuccess = obj.clickItem(LOAN_ORDER_TYPES_BTN);
			break;
		case APPROVAL_GROUPS_BTN_TXT:
			btnClickSuccess = obj.clickItem(CRAD_GROUPS_BTN);
			break;
		default:
			btnClickSuccess = false;
			break;
		}
		return btnClickSuccess;
	}

	/**
	 * This method is responsible for validating column headers of a given table
	 * This method in turn calls TableParser class to do the validation on its behalf as TableParser
	 * is a utility class for all validations related to a table.
	 *  
	 * @param clickedBtnTxt:	represents the name of the table whose column headings we want to validate.
	 * @return
	 */
	public boolean validateTableColumnHeaders(String clickedBtnTxt) {
		boolean result = false;
		switch(clickedBtnTxt) {
		case CREDIT_PLAN_GROUPS_BTN_TXT:
			result = tObj.validateColumnHeaders(travelCardColumns, CREDIT_PLAN_GROUPS_TABLE_ID,
					TABLE_COL_HDR_XPATH1, TABLE_COL_HDR_XPATH2);
			break;
		case LOAN_ORDER_TYPES_BTN_TXT:
			result = tObj.validateColumnHeaders(creditCardColumns, LOAN_ORDER_TYPES_TABLE_ID,
					TABLE_COL_HDR_XPATH1, TABLE_COL_HDR_XPATH2);
			break;
		case APPROVAL_GROUPS_BTN_TXT:
			result = tObj.validateColumnHeaders(creditTypeColumns, LOAN_ORDER_TYPES_TABLE_ID,
					TABLE_COL_HDR_XPATH1, TABLE_COL_HDR_XPATH2);
			break;
		default:
			result = false;
			break;
		}
		return result;
	}

	/**
	 * This method is responsible for validating drop down values for a column in a table.
	 * This method in turn calls TableParser class to do the validation on its behalf as TableParser
	 * is a utility class for all validations related to a table. 
	 * 
	 * @param columnName				:	Name of the column which has drop down in it. 
	 * @param expectedDropDownValues	: Expected values in the drop down.
	 * @return
	 */
	public boolean validateDropDownValues(String columnName, String expectedDropDownValues) {
		boolean result = false;
		switch(columnName) {
			case "VISA":
				result = tObj.validateColumnDropDownValues(TRAVEL_CARD_GROUPS_TABLE_ID, expectedDropDownValues);
				break;
			case "MASTERCARD":
				result = tObj.validateColumnDropDownValues(TR_PLAN_GROUPS_XPATH, expectedDropDownValues);
				break;
			default:
				break;
		}
		return result;
	}

	/**
	 * This method is responsible for validating column data in the table for the columns provided in the
	 * test scenario.
	 * This method in turn calls TableParser class to do the validation on its behalf as TableParser
	 * is a utility class for all validations related to a table.
	 * 
	 * @param columnMap	: Key value pairs where column name is the key and expected data is value.
	 * @param tableName	: Name of the table in which to perform validation.
	 */
	public void validateColumnValues(Map<String,String>columnMap, String tableName) {
		switch(tableName) {
		case "Credit Cards":
			tObj.validateTableColumnValue(columnMap, LOAN_GROUP_TBL_XPATH, TABLE_COL_HDR_XPATH1, 
					TABLE_COL_HDR_XPATH2, PLAN_GROUPS_TABLE_TBODY);
			break;
		case "Travel Cards":
			//TBD
			break;
		default:
			break;
		}
	}
}