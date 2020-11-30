package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class SugarBoxWelcomeBack extends AppiumUtils{
	
	public static class welcomeBackScreen {

		public static final By closeBtn = By.xpath("//android.widget.Button[@text='CLOSE']");
	}
	
	public void closeWelcomeBackPopup(int deviceIndex, String deviceName) {
		clickElement(welcomeBackScreen.closeBtn, deviceIndex, deviceName);
	}
}
