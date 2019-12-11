package com.creditkarma.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUI {

	private static final int  MAX_TIMEOUT_LIMIT = 60;//in seconds.
	private static final int LOCATOR_TYPE_XPATH = 1;
	private static final int LOCATOR_TYPE_ID = 2;
	private static final int LOCATOR_TYPE_CSS = 3;
	private static final int LOCATOR_TYPE_NAME = 4;
		
	/*
	 *WebDriver is declared as static so that same copy is shared across all objects
	 *of BaseUI
	 */
	public static WebDriver driver;

	//default constructor
	public BaseUI() {
	}

	/**
	 * This method is invoked before the scenario execution starts. It does the job of 
	 * setting basic configuration and initialize driver
	 */
	public void setBaseConfigurations() {
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
		driver = new ChromeDriver();
	}

	/**
	 * This method is called after a scenario has completed its execution. Quits driver
	 */
	public void doCleanUp() {
		System.out.println("In doCleanup method. It is time to quit driver instance object.");
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}

	/**
	 * This is a generic method.
	 * This method is responsible for hovering over an element which is supplied as an argument.
	 * @throws InterruptedException 
	 */
	public void hoverOverAnElement(WebElement elHandle) throws InterruptedException{
		Actions actions = new Actions(driver);
		actions.moveToElement(elHandle).perform();
	}

	/**
	 * As the name suggests, this method does the actual job of clicking on a UI element.
	 * 
	 * @param locator	: UI element to be clicked on.
	 * @throws InterruptedException 
	 */
	public boolean clickItem(String locator){
		WebElement elHandle = getElementHandleById(locator);
		elHandle.click();
		return true;
	}

	/**
	 * As the name suggests, this method returns the handle of an element.
	 * 
	 * @param locator	: element whose handle is to be retrieved.
	 * @return
	 */
	public WebElement getElementHandleById(String locator) {
		WebElement elHandle = null;
		elHandle = (new WebDriverWait(driver, MAX_TIMEOUT_LIMIT)).
				until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
		return elHandle;
	}

	/**
	 * As the name suggests, this method returns the handle of an element.
	 * 
	 * @param locator	: element whose handle is to be retrieved.
	 * @return
	 */
	public WebElement getElementHandleByXpath(String locator) {
		WebElement elHandle = null;
		elHandle = (new WebDriverWait(driver, MAX_TIMEOUT_LIMIT)).
				until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		return elHandle;
	}
	
	/**
	 * This method is responsible for retrieving the handle of an element base on the type of the
	 * locator supplied.
	 * 
	 * @param locator		:	of the UI element whose handle is to be retrieved
	 * @param locatorType	:	type of the locator(xpath/id/css/name) 		
	 * @return				:	handle of the element
	 */
	public WebElement getElementHandle(String locator, int locatorType) {
		WebElement elHandle = null;
		switch(locatorType) {
		case LOCATOR_TYPE_XPATH:
			elHandle = (new WebDriverWait(driver, MAX_TIMEOUT_LIMIT)).
			until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			break;
		case LOCATOR_TYPE_ID:
			elHandle = (new WebDriverWait(driver, MAX_TIMEOUT_LIMIT)).
			until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
			break;
		case LOCATOR_TYPE_CSS:
			elHandle = (new WebDriverWait(driver, MAX_TIMEOUT_LIMIT)).
			until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
			break;
		case LOCATOR_TYPE_NAME:
			elHandle = (new WebDriverWait(driver, MAX_TIMEOUT_LIMIT)).
			until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
			break;
		default:
			elHandle = null;
			break;
		}
		return elHandle;
	}
	
	/**
	 * As the name suggests, this method returns list of all the options / values in a drop down.
	 * 
	 * @param columnLocator	:	column that contains drop down.
	 * @return
	 */
	public List<WebElement> getDropDownValues(String columnLocator) {
		WebElement elHandle = getElementHandleByXpath(columnLocator);
		Select obj = new Select(elHandle);
		List<WebElement> list = obj.getOptions();
		return list;
	}
}