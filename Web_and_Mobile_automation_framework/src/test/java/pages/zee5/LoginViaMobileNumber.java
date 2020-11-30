package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class LoginViaMobileNumber extends AppiumUtils {

	// Login via mobile number page locators
	public static class LoginViaMobNum {

		public static final By mobileNumInputBox = By
				.xpath("//android.widget.EditText[contains(@resource-id, 'input_phone_text')]");
		public static final By passwordInputBox = By
				.xpath("//android.widget.EditText[contains(@resource-id, 'input_password_text')]");
		public static final By loginBtn = By.xpath("//android.widget.Button[@text='LOGIN']");

	}

	// Verifying if login button is enabled
	public boolean verifyLoginBtn_Enabled(int deviceIndex, String deviceName) {

		waitForElementsPresence(LoginViaMobNum.loginBtn, deviceIndex);
		boolean isEnabled = isEnabled(LoginViaMobNum.loginBtn, deviceIndex);
		logger.info("Login button status: " + isEnabled + " on device: " + deviceName);
		return isEnabled;
	}

	// Login with Phone number on Zee5
	public void loginWithPhoneNum(String phoneNum, String pswrd, int deviceIndex, String deviceName) {

		sendKeysInTextBox(LoginViaMobNum.mobileNumInputBox, phoneNum, deviceIndex);
		hideKeyboard(deviceIndex);
		sendKeysInTextBox(LoginViaMobNum.passwordInputBox, pswrd, deviceIndex);
		hideKeyboard(deviceIndex);
		logger.info("Phone number: " + phoneNum + " and password: " + pswrd + " entered on device: " + deviceName);
		if (verifyLoginBtn_Enabled(deviceIndex, deviceName)) {
			clickElement(LoginViaMobNum.loginBtn, deviceIndex, deviceName);
		} else {
			logger.error(
					"Login button is still not enabled, looks like Zee5 is taking more time on device: " + deviceName);
		}

	}
}
