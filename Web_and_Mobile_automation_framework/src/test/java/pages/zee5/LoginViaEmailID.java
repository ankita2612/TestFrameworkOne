package pages.zee5;

import org.openqa.selenium.By;

import pages.zee5.LoginViaMobileNumber.LoginViaMobNum;
import utils.AppiumUtils;

public class LoginViaEmailID extends AppiumUtils {

	// Login via email id page locators
	public static class LoginUsingEmailID {

		public static final By emailIDTextBox = By
				.xpath("//android.widget.EditText[contains(@resource-id,'input_email_text')]");
		public static final By passwordTextBox = By
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

	// Login using email ID on Zee5 app, for now it's premium only
	public void loginWithEmailID(String email, String pswrd, int deviceIndex, String deviceName) {

		sendKeysInTextBox(LoginUsingEmailID.emailIDTextBox, email, deviceIndex);
		hideKeyboard(deviceIndex);
		sendKeysInTextBox(LoginUsingEmailID.passwordTextBox, pswrd, deviceIndex);
		hideKeyboard(deviceIndex);
		logger.info("Email: " + email + " and password: " + pswrd + " entered on device: " + deviceName);
		if (verifyLoginBtn_Enabled(deviceIndex, deviceName)) {
			clickElement(LoginViaMobNum.loginBtn, deviceIndex, deviceName);
		} else {
			logger.error(
					"Login button is still not enabled, looks like Zee5 is taking more time on device: " + deviceName);
		}

	}
}
