package pages.sugarbox;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class PersonalizationPage extends AppiumUtils {

	// Personalization Page locators
	public static class PersonalizationPage_locators {

		public static final By sbLogo = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'act_personal_img_logo')]");
		public static final By title = By.xpath("//android.widget.TextView[@text='Preparing your offline experience']");
		public static final By indicator = By.xpath("//android.view.View[@text='act_personal_indicator']");
		public static final By footerText = By.xpath(
				"//android.widget.TextView[@text='SugarBox requires access to the Internet once to prepare your device.']");
	}

	// Verifying Personalization Page Elements
	public void verifyPersonalizationPage(int deviceIndex, String deviceName) {

		elementPresentAssertion(PersonalizationPage_locators.sbLogo, deviceIndex,
				"SugarBox logo is not present on Personalization Screen.");
		elementPresentAssertion(PersonalizationPage_locators.title, deviceIndex,
				"Title is not present or mismatch on Personalization Screen.");
		elementPresentAssertion(PersonalizationPage_locators.footerText, deviceIndex,
				"Footer text is not present on mismatch on Personalization screen.");
		logger.info("Verified locators on Personalization Page on device: " + deviceName);
	}
}
