package com.creditkarma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.creditkarma.utils.BaseUI;
import com.creditkarma.utils.DataCreation;

/*
 * This class represents the home page of ABC Application. This class is responsible for
 * performing all UI interactions on the home page.
 */
public class HomePage{
	BaseUI obj = new BaseUI();
	DataCreation dataCreationObj = new DataCreation();

	private static final String SPACE_HOME_URL = "http://www.crekaram.com"; 
	private static final String LEFT_NAV_BAR = "sidebar-wrapper";
	private static final String CARDS_MAIN_MENU_OPTION = "li-madit";
	private static final String LOANS_MAIN_MENU_OPTION = "li-matchingstool";
	private static final String SETTINGS_SUB_MENU_OPTION = "li-settings";
	private static final String GHI1_SUB_MENU_OPTION = "x-reg1(100)";
	private static final String GHI2_SUB_MENU_OPTION = "x-reg2(200)";
	private static final String GHI3_SUB_MENU_OPTION = "x-reg3(300)";
	private static final String GHI4_SUB_MENU_OPTION = "x-reg4(400)";
	private static final int MIN_GHI_COUNT = 1;
	private static final int MAX_GHI_COUNT = 4;
	
	//Default Constructor
	public HomePage() {
	}

	/**
	 * This method is responsible for loading ABC application home page and
	 * maximizes the window.
	 */
	public void navigateToHomePage() {
		//to() method will block/wait for the web page to load completely
		BaseUI.driver.navigate().to(SPACE_HOME_URL);
		BaseUI.driver.manage().window().maximize();
	}

	/**
	 * This method is responsible for hovering over the ABC application left
	 * navigation bar to show / expose the main menu options. It calls another generic method
	 * which does the real task of hovering.
	 * @throws InterruptedException 
	 */
	public void hoverOverLeftNav() throws InterruptedException {
		WebElement elHandle = BaseUI.driver.findElement(By.id(LEFT_NAV_BAR)); 
		obj.hoverOverAnElement(elHandle);
	}

	/**
	 * This method is responsible for menu interactions by clicking on respective
	 * menu / sub-menu options, it receives as parameters. 
	 * 
	 * @param subMenuItem	:	sub menu option to click on.
	 * @param mainMenuItem	:	main menu option to click on. 
	 * @throws InterruptedException 
	 */
	public boolean clickMenuItem(String subMenuItem, String mainMenuItem) throws InterruptedException {
		boolean invalidMenuOption = false;
		/*
		 * I am using switch inside switch to identify which menu option / sub option
		 * to click on.
		 * 
		 * The outer switch construct deals with main menu options.
		 * The inner switch deals with sub menu options.
		 */
		switch(mainMenuItem) {
		case "Cards":
			obj.clickItem(CARDS_MAIN_MENU_OPTION);
			switch(subMenuItem) {
				case "Credit Cards":
					obj.clickItem("XXX");
					break;
				case "Travel Cards":
					obj.clickItem("XXX");
					break;
				default:
					invalidMenuOption = true;
					break;
			}
			break;
		case "Loans":
			obj.clickItem(LOANS_MAIN_MENU_OPTION);
			switch(subMenuItem) {
			case "Personal":
				obj.clickItem(pickRandomOption(MIN_GHI_COUNT,MIN_GHI_COUNT));
				break;
			case "Home":
				obj.clickItem(SETTINGS_SUB_MENU_OPTION);
				break;
			default:
				invalidMenuOption = true;
				break;
			}
			break;
		default:
			invalidMenuOption = true;
			break;
		}
		return invalidMenuOption;
	}

	/**
	 * This method is responsible for generating random number within a range.
	 * The range is defined by min and max(inclusive).
	 * 
	 * @param min	:	represents lower range boundary
	 * @param max	:	represents upper range boundary
	 * @return
	 */
	private String pickRandomOption(int min, int max) {
		int randomNbr = dataCreationObj.generateRandomNumber(min, max);
		if(randomNbr == 1)
			return GHI1_SUB_MENU_OPTION;
		else if(randomNbr == 2)
			return GHI2_SUB_MENU_OPTION;
		else if(randomNbr == 3)
			return GHI3_SUB_MENU_OPTION;
		else
			return GHI4_SUB_MENU_OPTION;
	}
}