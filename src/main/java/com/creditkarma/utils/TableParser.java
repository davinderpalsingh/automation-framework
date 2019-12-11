package com.creditkarma.utils;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/*
 * As the name suggests, this class will do the job of parsing a table and providing related information
 */
public class TableParser {
	BaseUI obj = new BaseUI();

	/**
	 * This method will validate if the table supplied has expected column headers or not.
	 * 
	 * @param columnHeaders	: array of expected column headers
	 * @param tableId	:	Id of the table used to ensure that the table is available in UI for processing
	 * @param xPath1	:	Partial xpath of the row containing column headings
	 * @param xPath2	:	Partial xpath of the row containing column headings
	 * @return			:	return true if a mismatch is found.
	 */
	public boolean validateColumnHeaders(String [] columnHeaders, String tableId,
			String xPath1, String xPath2) {
		boolean colHdrMisMatch = false;
		if(obj.getElementHandleById(tableId) != null) {
			
			//The table is displayed on the page.
			String colHdrRowHandle = xPath1 + tableId + xPath2;
			WebElement el = BaseUI.driver.findElement(By.xpath(colHdrRowHandle));
			List<WebElement> list = el.findElements(By.tagName("th"));

			for(int i=0; i<list.size();i++) {
				if(!(list.get(i).getText()).contentEquals(columnHeaders[i])) {
					colHdrMisMatch = true;
					break;
				}
			}
		}
		return colHdrMisMatch;
	}

	/**
	 * This method validates whether all the values in a drop down are as expected or not.
	 * 
	 * @param columnLocator	:	column which contains the drop down.
	 * @param expectedDropDownValues	:	what all values to expect in the drop down.
	 * @return
	 */
	public boolean validateColumnDropDownValues(String columnLocator, String expectedDropDownValues) {
		String commaSeparatedValues = "";
		List<WebElement> list = obj.getDropDownValues(columnLocator);
		for(int i=0; i<list.size();i++) {
			commaSeparatedValues += list.get(i).getText();
			/*
			 * Below if condition ensures that comma(,) do not get appended after the last value 
			 */
			if(list.size() - i != 1)
				commaSeparatedValues += ",";
		}
		return(expectedDropDownValues.contentEquals(commaSeparatedValues));
	}

	public boolean validateTableColumnValue(Map<String,String> columnMap, String tableId, 
			String xPath1ForColHeaders, String xPath2ForColHeaders, String tableBodyLocator) {

		int [] columnIndex = new int[columnMap.size()];
		int counter = 0;
		boolean matchFound = false;

		if(obj.getElementHandleById(tableId) != null) {
			//The table is displayed on the page.
			String colHdrRowHandle = xPath1ForColHeaders + tableId + xPath2ForColHeaders;
			WebElement el = BaseUI.driver.findElement(By.xpath(colHdrRowHandle));
			List<WebElement> list = el.findElements(By.tagName("th"));

			for(int i=0; i<list.size();i++) {
				String colHdr = list.get(i).getText();
				for (Map.Entry<String,String> entry : columnMap.entrySet()) {
					if(colHdr.equalsIgnoreCase((String)entry.getKey())) {
						columnIndex[counter] = i+1;
						counter++;
						break;
					}
				}
			}

			WebElement tableBody = BaseUI.driver.findElement(By.xpath(tableBodyLocator));
			List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
			for(int i=0; i<rows.size();i++) {
				String [] array = new String[columnMap.size()];
				for(int j=0;j<columnMap.size();j++) {
					String tdLocator = tableBodyLocator + "/tr[" + (i+1) + "]" + "/td[" + (columnIndex[j]) + "]//input";
					WebElement colHandle = BaseUI.driver.findElement(By.xpath(tdLocator));
					String colDataValue = colHandle.getAttribute("value");
					array[j] = colDataValue;
				}
				matchFound = matchInMap(columnMap,array);
			}
		}
		return matchFound;
	}

	private boolean matchInMap(Map<String,String> columnMap, String [] array) {
		return true;
	}
}