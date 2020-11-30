package pages.sugarbox;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class TutorialPage extends AppiumUtils {

	// Tutorial Screen 1 objects
	public static class TutorialPage1_locators {

		public static final By text1 = By
				.xpath("//android.widget.TextView[@text='Stream or download videos without mobile data']");
		public static final By text2 = By.xpath("//android.widget.TextView[@text='Seamless Streaming']");
		public static final By text3 = By.xpath("//android.widget.TextView[@text='Super-fast downloads']");
		public static final By text4 = By.xpath("//android.widget.TextView[@text='No usage limits']");
		public static final By skipBtn = By.xpath("//android.widget.Button[@text='SKIP']");
		public static final By headerTitle = By
				.xpath("//android.widget.TextView[contains(@text, 'LIKE NEVER BEFORE')]");

	}

	// Tutorial Screen 2 objects
	public static class TutorialPage2_locators {

		public static final By headerTitle = By.xpath("//android.widget.TextView[@text='HOW IT WORKS']");
		public static final By title1 = By.xpath("//android.widget.TextView[@text='Locate']");
		public static final By desc1 = By.xpath("//android.widget.TextView[@text='Any nearby SugarBox Zone']");
		public static final By title2 = By.xpath("//android.widget.TextView[@text='Connect ']");
		public static final By desc2 = By.xpath("//android.widget.TextView[@text='To SugarBox Wi-Fi']");
		public static final By title3 = By.xpath("//android.widget.TextView[@text='Enjoy']");
		public static final By desc3 = By
				.xpath("//android.widget.TextView[@text='Unlimited access to all Movies and Shows']");
		public static final By doneBtn = By.xpath("//android.widget.Button[@text='DONE']");

	}

	// Verifying Tutorial Page 1 elements
	public void verifyTutorialPage1(int deviceIndex) {

		elementPresentAssertion(TutorialPage1_locators.text1, deviceIndex,
				"Text1 is not present or mismatches on Tutorial Screen ONE.");
		elementPresentAssertion(TutorialPage1_locators.text2, deviceIndex,
				"Text2 is not present or mismatches on Tutorial Screen ONE.");
		elementPresentAssertion(TutorialPage1_locators.text3, deviceIndex,
				"Text3 is not present or mismatches on Tutorial Screen ONE.");
		elementPresentAssertion(TutorialPage1_locators.text4, deviceIndex,
				"Text4 is not present or mismatches on Tutorial Screen ONE.");
		elementPresentAssertion(TutorialPage1_locators.skipBtn, deviceIndex,
				"Skip Button is not present on Tutorial Screen ONE.");
		elementPresentAssertion(TutorialPage1_locators.headerTitle, deviceIndex,
				"Header Title is not present or mismatches on Tutorial Screen ONE.");
		logger.info("All elements verified on Tutorial Screen ONE.");
		scrollHorizontal(700, 100, 200, deviceIndex);
	}

	// Verifying Tutorial Page 2 elements
	public void verifyTutorialPage2(int deviceIndex) {

		elementPresentAssertion(TutorialPage2_locators.title1, deviceIndex,
				"Title1 is not present or mismatches on Tutorial Screen TWO.");
		elementPresentAssertion(TutorialPage2_locators.title2, deviceIndex,
				"Title2 is not present or mismatches on Tutorial Screen TWO.");
		elementPresentAssertion(TutorialPage2_locators.title3, deviceIndex,
				"Title3 is not present or mismatches on Tutorial Screen TWO.");
		elementPresentAssertion(TutorialPage2_locators.desc1, deviceIndex,
				"Description1 is not present or mismatches on Tutorial Screen TWO.");
		elementPresentAssertion(TutorialPage2_locators.desc2, deviceIndex,
				"Description2 is not present or mismatches on Tutorial Screen TWO.");
		elementPresentAssertion(TutorialPage2_locators.desc3, deviceIndex,
				"Description3 is not present or mismatches on Tutorial Screen TWO.");
		elementPresentAssertion(TutorialPage2_locators.doneBtn, deviceIndex,
				"DONE Button is not present on Tutorial Screen TWO.");
		elementPresentAssertion(TutorialPage2_locators.headerTitle, deviceIndex,
				"Header Title is not present or mismatches on Tutorial Screen TWO.");
		logger.info("All elements verified on Tutorial Screen TWO.");

	}

	public void clickSkipBtn(int deviceIndex, String deviceName) {

		clickElement(TutorialPage1_locators.skipBtn, deviceIndex, deviceName);
		logger.info("Clicked on Skip Button on Tutorial Screen ONE.");
	}

	public void clickDoneBtn(int deviceIndex, String deviceName) {

		clickElement(TutorialPage2_locators.doneBtn, deviceIndex, deviceName);
		logger.info("Clicked on DONE Button on Tutorial Screen TWO.");
	}
}
