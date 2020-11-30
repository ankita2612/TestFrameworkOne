package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class Zee5ListToWatch extends AppiumUtils {

	public static class ListToWatch {

		public static final By tvShows = By.xpath("//android.widget.Button[@text='TV SHOWS']");
		public static final By movies = By.xpath("//android.widget.Button[@text='MOVIES']");
		public static final By guestUsername = By.xpath("//android.widget.TextView[@text='Hi Guest']");
		public static final By homeOption = By.xpath("//android.widget.Button[@text='HOME']");
	}

	public void chooseTVShows(int deviceIndex, String deviceName) {
		clickElement(ListToWatch.tvShows, deviceIndex, deviceName);
	}

	public boolean verifyHomeOptionPresent(int deviceIndex, String deviceName) {
		return isElementPresent(ListToWatch.homeOption, deviceIndex);
	}

	public void scroll_and_chooseMovies(int deviceIndex, String deviceName) {
		
		if (isElementPresent(ListToWatch.movies, deviceIndex)) {
			clickElement(ListToWatch.movies, deviceIndex, deviceName);
		} else {
			int height = getHeight(deviceIndex);
			scrollVertical(368, height, 260, deviceIndex);
			waitForElementPresense(ListToWatch.movies, deviceIndex);
			clickElement(ListToWatch.movies, deviceIndex, deviceName);
		}
	}

	public boolean verifyUserLoggedInToZee(int deviceIndex) {
		return isElementPresent(ListToWatch.guestUsername, deviceIndex);
	}
}
