package pages.sugarbox;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class AppDrawerPage extends AppiumUtils {

	// Drawer Page locators
	public static class AppDrawerPage_locators {

		public static final By faq = By.xpath("//android.widget.CheckedTextView[@text='FAQs']");
		public static final By privacyPolicy = By.xpath("//android.widget.CheckedTextView[@text='PRIVACY POLICY']");
		public static final By contactUs = By.xpath("//android.widget.CheckedTextView[@text='CONTACT US']");
	}

	// Clicking on Faq
	public void clickFaq(int deviceIndex, String deviceName) {

		clickElement(AppDrawerPage_locators.faq, deviceIndex, deviceName);
		logger.info("User clicked on Faqs on App Drawer screen on device: " + deviceName);
	}

	// Clicking on Privacy Policy
	public void clickPrivacyPolicy(int deviceIndex, String deviceName) {

		clickElement(AppDrawerPage_locators.faq, deviceIndex, deviceName);
		logger.info("User clicked on Privacy Policy on App Drawer screen on device: " + deviceName);
	}

	// Clicking on Contact Us
	public void clickContactUs(int deviceIndex, String deviceName) {

		clickElement(AppDrawerPage_locators.faq, deviceIndex, deviceName);
		logger.info("User clicked on Contact Us on App Drawer screen on device: " + deviceName);
	}
}
