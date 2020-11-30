package pages.sugarbox;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class PlayerScreen extends AppiumUtils {

	// Player Screen Locators
	public static class PlayerScreen_locators {

		public static final By playBtn = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'act_play_img_play')]");
		public static final By downloadBtn = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'downloadBtn')]");
		public static final By contentTitle = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'contentTitle')]");

		// Locators on Player screen
		public static final By exoPlayer = By
				.xpath("//android.widget.FrameLayout[contains(@resource-id, 'exo_ad_overlay')]");
		public static final By previousTrack = By
				.xpath("//android.widget.ImageButton[contains(@resource-id, 'exo_prev')]");
		public static final By rewindBtn = By.xpath("//android.widget.ImageButton[contains(@resource-id, 'exo_rew')]");
		public static final By playBtnOnPlayer = By
				.xpath("//android.widget.ImageButton[contains(@resource-id, 'exo_play')]");
		public static final By pauseBtn = By.xpath("//android.widget.ImageButton[contains(@resource-id, 'exo_pause')]");
		public static final By forwardBtn = By
				.xpath("//android.widget.ImageButton[contains(@resource-id, 'exo_ffwd')]");
		public static final By nextTrack = By.xpath("//android.widget.ImageButton[contains(@resource-id, 'exo_next')]");

		// Locators on Seek bar
		public static final By playedTill = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'exo_position')]");
		public static final By seekBar = By.xpath("//android.widget.SeekBar[contains(@resource-id, 'exo_progress')]");
		public static final By totalDuaration = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'exo_duration')]");

	}

	// Verifying locators on Player Screen
	public void verifyPlayerScreen_locators(int deviceIndex, String deviceName) {

		elementPresentAssertion(PlayerScreen_locators.playBtn, deviceIndex,
				"Play button is not present on Player screen on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.downloadBtn, deviceIndex,
				"Download button is not present on Player screen on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.contentTitle, deviceIndex,
				"Content title is not present on Player screen on device: " + deviceName);
		logger.info("Locators on Player Screen verified on device: " + deviceName);
	}

	// Start playing content
	public void startPlaying(int deviceIndex, String deviceName) {

		clickElement(PlayerScreen_locators.playBtn, deviceIndex, deviceName);
		logger.info("User started playing content on Player Screen on device: " + deviceName);
	}

	// Verifying locators on Player
	public void verifyPlayerLocators(int deviceIndex, String deviceName) {

		elementPresentAssertion(PlayerScreen_locators.exoPlayer, deviceIndex,
				"Exo Player overlay is not present on player on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.pauseBtn, deviceIndex,
				"Pause button is not present on Player on device: " + deviceName);
		pauseContentOnPlayer(deviceIndex, deviceName);
		elementPresentAssertion(PlayerScreen_locators.previousTrack, deviceIndex,
				"Previous Track button is not present on Player on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.rewindBtn, deviceIndex,
				"Rewind button is not present on Player on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.playBtnOnPlayer, deviceIndex,
				"Play button is not present on Player on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.forwardBtn, deviceIndex,
				"Forward button is not present on Player on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.nextTrack, deviceIndex,
				"Next Track button is not present on Player on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.playedTill, deviceIndex,
				"Content played till is not present on Player on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.seekBar, deviceIndex,
				"Seek Bar is not present on Player on device: " + deviceName);
		elementPresentAssertion(PlayerScreen_locators.totalDuaration, deviceIndex,
				"Total durations is not present on Player on device: " + deviceName);
		logger.info("Locators on Player verified on device: " + deviceName);

	}

	// Start playing content on Player
	public void playContentOnPlayer(int deviceIndex, String deviceName) {

		clickElement(PlayerScreen_locators.playBtnOnPlayer, deviceIndex, deviceName);
		logger.info("User started playing contnet on Player on device: " + deviceName);
	}

	// Pause content on Player
	public void pauseContentOnPlayer(int deviceIndex, String deviceName) {

		clickElement(PlayerScreen_locators.pauseBtn, deviceIndex, deviceName);
		logger.info("User paused content on Player on device: " + deviceName);
	}

	// Clicking on Player overlay
	public void clickExoPlayer(int deviceIndex, String deviceName) {

		clickElement(PlayerScreen_locators.exoPlayer, deviceIndex, deviceName);
		logger.info("User clicked on Exo Player overlay on Player on device: " + deviceName);
	}

	// Start downloading content
	public void startDownloading(int deviceIndex, String deviceName) {

		clickElement(PlayerScreen_locators.downloadBtn, deviceIndex, deviceName);
		logger.info("User started downloading on Player screen on device: " + deviceName);
	}

	// Return Content's played time on Seek bar on Player
	public String getCurrentPlayTime(int deviceIndex, String deviceName) {

		logger.info("Getting Content's played time on Seek baar on Player on device: " + deviceName);
		String playedTime = getElementText(PlayerScreen_locators.playedTill, deviceIndex, deviceName);
		logger.info("Content played time is: " + playedTime + " on Player on device: " + deviceName);
		return playedTime;

	}

	// Returns Content title
	public String getContentTitle(int deviceIndex, String deviceName) {

		logger.info("Getting Content title on Player screen on device: " + deviceName);
		return getElementText(PlayerScreen_locators.contentTitle, deviceIndex, deviceName);
	}

	// Verifying Content is playing or Not
	public void verifyContentIsPlaying(int deviceIndex, String deviceName) throws Exception {

		TimeUnit.MINUTES.sleep(1);
		
		clickExoPlayer(deviceIndex, deviceName);
		pauseContentOnPlayer(deviceIndex, deviceName);
		String playedTime = getCurrentPlayTime(deviceIndex, deviceName);

		if (playedTime == "00:00") {

			logger.info("Content is not playing on Player on device: " + deviceName);
		} else {

			logger.info("Content is playing on Player on device: " + deviceName);
		}
	}
}
