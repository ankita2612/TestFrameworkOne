package pages.sugarbox;

import org.openqa.selenium.By;
import org.testng.Assert;

import utils.AppiumUtils;

public class LoginPage extends AppiumUtils {

	// Login Page locators
	public static class LoginPage_locators {

		public static final By sboxLogo = By.xpath("//android.widget.ImageView[contains(@resource-id, 'sboxLogo')]");
		public static final By enterMob_text = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'enterMobileNumberTV')]");
		public static final By enterMobNo = By.xpath("//android.widget.EditText[@text='Mobile Number']");
		public static final By continueBtn = By.xpath("//android.widget.Button[@text='Continue']");

	}

	// verifying enter Mobile Number instruction text
	public void verifyMobNo_instructionText(int deviceIndex, String deviceName) {

		Assert.assertEquals(getElementText(LoginPage_locators.enterMob_text, deviceIndex, deviceName),
				"Enter your mobile number to join SugarBox");
		logger.info("Enter mobile number instructions text is verified on device: " + deviceName);
	}

	// verifying elements on Login Screen
	public void verifyLoginPageLocators(int deviceIndex, String deviceName) {

		elementPresentAssertion(LoginPage_locators.sboxLogo, deviceIndex,
				"SugarBox logo is not present on Login screen on device: " + deviceName);
		elementPresentAssertion(LoginPage_locators.enterMobNo, deviceIndex,
				"Enter Mobile Number text box is not present on Login screen on device: " + deviceName);
		elementPresentAssertion(LoginPage_locators.continueBtn, deviceIndex,
				"Continue button is not present on Login screen on device: " + deviceName);
		logger.info("All Elements verified on Login screen on device: " + deviceName);
	}

	// Entering Mobile Number in text box
	public void enterMobileNum(int deviceIndex, String number, String deviceName) {

		sendKeysInTextBox(LoginPage_locators.enterMobNo, number, deviceIndex);
		logger.info("User entered mobile number: " + number + " on Login screen on device: " + deviceName);
	}

	// Clicking on Continue button
	public void clickContinueBtn(int deviceIndex, String deviceName) {

		clickElement(LoginPage_locators.continueBtn, deviceIndex, deviceName);
		logger.info("User clicked on Continue button on Login screen on device: " + deviceName);
	}
}
