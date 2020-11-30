package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class Zee5LanguagePage extends AppiumUtils {

	public static class LanguagePage_locators {

		public static final By englishOption = By.xpath("//android.widget.CheckBox[@text='English']");
		public static final By doneBtn = By.xpath("//android.widget.TextView[@text='DONE']");
	}

	public void updateLanguage(int deviceIndex, String deviceName) {
		waitAndClickElement(LanguagePage_locators.englishOption, deviceIndex);
		waitAndClickElement(LanguagePage_locators.doneBtn, deviceIndex);
		logger.info("Language updated for device: " + deviceName);
	}
}
