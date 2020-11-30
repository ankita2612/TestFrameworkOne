package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class Zee5SearchScreen extends AppiumUtils {

	public static class SearchScreen_locators {

		public static final By searchTextBox = By.xpath("//android.widget.EditText[@text='What are you looking for?']");
		public static final By searchResultText = By
				.xpath("//android.widget.TextView[contains(@resource-id,'search_result_text')]");
	}

	public void enterSearchQuery(int deviceIndex, String deviceName, String enterText) {

		sendKeysInTextBox(SearchScreen_locators.searchTextBox, enterText, deviceIndex);
		pressEnterBtn(deviceIndex);
	}

	public void verifySearchResult(int deviceIndex, String deviceName) {

		waitForElementPresense(SearchScreen_locators.searchResultText, deviceIndex);
		String queryResult = getElementText(SearchScreen_locators.searchResultText, deviceIndex, deviceName);
		if (queryResult == "null") {
			logger.info("Query Result text does not appaer on device: " + deviceName);
			tearDown(deviceIndex, deviceName);
		} else {
			logger.info("Search Query Result text on device: " + queryResult);
		}
	}
}
