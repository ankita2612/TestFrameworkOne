package pages.zee5;

import org.openqa.selenium.By;
import org.testng.Assert;

import utils.AppiumUtils;

public class SugarBoxWalkThrough extends AppiumUtils {

	SugarBoxPopUp objSBPopup = new SugarBoxPopUp();

	public static class sugarBoxWalkThrough {

		public static final By skipBtn = By.xpath("//android.widget.TextView[@text='SKIP']");
		public static final By continueBtn = By.xpath("//android.widget.TextView[@text='CONTINUE']");
		public static final By continueWithSugarBoxBtn = By
				.xpath("//android.widget.TextView[@text='CONTINUE WITH SUGARBOX']");
	}

	public void verifySkip_continue_SBWalkThrPage(int deviceIndex, String deviceName) {

		Assert.assertTrue(isElementPresent(sugarBoxWalkThrough.skipBtn, deviceIndex),
				"Skip button is not present on SB Walk Through page on device: " + deviceName);
		logger.info("Skip button is present on SB Walk Through page on device: " + deviceName);
		Assert.assertTrue(isElementPresent(sugarBoxWalkThrough.continueBtn, deviceIndex),
				"Continue button is not present on SB Walk Through page on device: " + deviceName);
		logger.info("Continue button is present on SB Walk Through page on device: " + deviceName);
	}

	public void verifyContinueWithSB_Btn(int deviceIndex, String deviceName) {

		Assert.assertTrue(isElementPresent(sugarBoxWalkThrough.continueWithSugarBoxBtn, deviceIndex),
				"Continue with SB button is not present on SB Walk Through page on device: " + deviceName);
		logger.info("Continue with SB button is present on SB Walk Through page on device: " + deviceName);
	}

	public void verifyLearnMoreScreen(int deviceIndex, String deviceName) {

		objSBPopup.clickLearnMore(deviceIndex, deviceName);

	}

	public void continueToZee5App(int deviceIndex, String deviceName) {
		int i = 0;
		while (i < 2) {
			clickElement(sugarBoxWalkThrough.continueBtn, deviceIndex, deviceName);
			i++;
		}
		clickElement(sugarBoxWalkThrough.continueWithSugarBoxBtn, deviceIndex, deviceName);
	}
}
