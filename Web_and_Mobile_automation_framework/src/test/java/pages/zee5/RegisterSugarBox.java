package pages.zee5;

import org.openqa.selenium.By;

import pages.zee5.SugarBoxDisconnected.SBDisconnected;
import pages.zee5.SugarBoxWalkThrough.sugarBoxWalkThrough;
import pages.zee5.SugarBoxWelcomeBack.welcomeBackScreen;
import utils.AppiumUtils;

public class RegisterSugarBox extends AppiumUtils {

	SugarBoxWelcomeBack objSBWelcome = new SugarBoxWelcomeBack();
	SugarBoxWalkThrough objSBWalkThr = new SugarBoxWalkThrough();
	SugarBoxDisconnected objSBDisconnect = new SugarBoxDisconnected();

	public static class MobileNumberVerification {

		public static final By enterMobileNumber = By.xpath("//android.widget.EditText[@text='ENTER MOBILE NUMBER']");
		public static final By submitBtn = By.xpath("//android.widget.Button[@text='SUBMIT']");
		public static final By otpTextBox1 = By.id("com.graymatrix.did:id/otpET1");
		public static final By otpTextBox2 = By.id("com.graymatrix.did:id/otpET2");
		public static final By otpTextBox3 = By.id("com.graymatrix.did:id/otpET3");
		public static final By otpTextBox4 = By.id("com.graymatrix.did:id/otpET4");

	}

	public void submitMobileNumber(String phoneNumber, int deviceIndex, String deviceName) {
		sendKeysInTextBox(MobileNumberVerification.enterMobileNumber, phoneNumber, deviceIndex);
		clickElement(MobileNumberVerification.submitBtn, deviceIndex, deviceName);
	}

	public void verifyUserLoggedInSB(String phoneNumber, int deviceIndex, String deviceName) {

		if (isElementPresent(MobileNumberVerification.enterMobileNumber, deviceIndex)) {

			submitMobileNumber(phoneNumber, deviceIndex, deviceName);
			logger.info("Thanks for sharing Phone Number, we are checking if you are already registered on device: "
					+ deviceName);

			if (isElementPresent(SBDisconnected.crossBtn, deviceIndex)) {

				objSBDisconnect.closeDisconnectPopup(deviceIndex, deviceName);
				logger.info("Oops you are disconnected from SugarBox on device: " + deviceName);
			} else if (isElementPresent(sugarBoxWalkThrough.continueBtn, deviceIndex)) {

				objSBWalkThr.continueToZee5App(deviceIndex, deviceName);
				logger.info("Congo!! You can now play and download for free on device: " + deviceName);
			}
		} else if (isElementPresent(welcomeBackScreen.closeBtn, deviceIndex)) {

			objSBWelcome.closeWelcomeBackPopup(deviceIndex, deviceName);
			logger.info("Welcome Back!! We are happy to serve you again on device: " + deviceName);

		} else {
			logger.info("It's taking more time than usual on device: " + deviceName);
			tearDown(deviceIndex, deviceName);

		}
	}
}
