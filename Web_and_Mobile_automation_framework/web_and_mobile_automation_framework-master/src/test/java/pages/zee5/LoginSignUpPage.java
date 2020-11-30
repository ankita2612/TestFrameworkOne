package pages.zee5;

import org.openqa.selenium.By;
import org.testng.Assert;

import pages.zee5.SugarBoxPopUp.SugarBoxConnectPopup;
import pages.zee5.Zee5LocationPermission.GivePermission;
import utils.AppiumUtils;

public class LoginSignUpPage extends AppiumUtils {

	Zee5LocationPermission objZee5Permission = new Zee5LocationPermission();
	SugarBoxPopUp objSBPopup = new SugarBoxPopUp();
	RegisterSugarBox objRegisterSB = new RegisterSugarBox();
	Zee5ListToWatch objZeeListToWatch = new Zee5ListToWatch();
	LoginViaMobileNumber objLoginViaPhNno = new LoginViaMobileNumber();
	Zee5LanguagePage objLangPage = new Zee5LanguagePage();
	LoginViaEmailID objLoginViaEmail = new LoginViaEmailID();

	// Object Locators of Zee5 Login Page
	public static class LoginPage {

		public static final By skipBtn = By.xpath("//android.widget.TextView[@text='SKIP']");
		public static final By loginViaMobileNo = By
				.xpath("//android.widget.RelativeLayout[contains(@resource-id, 'login_register_mobile')]");
		public static final By loginViaEmail = By
				.xpath("//android.widget.RelativeLayout[contains(@resource-id, 'login_register_email')]");
		public static final By zee5AppLogo = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'z5LoginLogoImageView')]");
		public static final By loginWithFacebook = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'facebook')]");
		public static final By loginWithTwitter = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'twitter')]");
		public static final By loginWithGooglePlus = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'google_sign_in')]");
	}

	// Skip login screen
	public void skipLoginPage(int deviceIndex, String deviceName) {
		clickElement(LoginPage.skipBtn, deviceIndex, deviceName);
	}

	// Verify Mobile Number button on login screen
	public boolean verifyLoginViaMobileNumButton(int deviceIndex) {
		waitForElementsPresence(LoginPage.loginViaMobileNo, deviceIndex);
		return isElementPresent(LoginPage.loginViaMobileNo, deviceIndex);
	}

	// Click Login with Mobile Number button
	public void clickLoginViaMobileNum(int deviceIndex, String deviceName) {
		clickElement(LoginPage.loginViaMobileNo, deviceIndex, deviceName);
	}

	// Click Login with Email ID button
	public void clickLoginViaEmail(int deviceIndex, String deviceName) {
		clickElement(LoginPage.loginViaEmail, deviceIndex, deviceName);
	}

	// Verify buttons on Zee5 Login Page
	public void verifyButtonsON_Zee5LoginPage(int deviceIndex, String deviceName) {

		Assert.assertTrue(isElementPresent(LoginPage.zee5AppLogo, deviceIndex),
				"Zee5 app logo is not present on Login Page on device: " + deviceName);
		logger.info("Zee5 app logo is present on Login Page on device: " + deviceName);
		Assert.assertTrue(isElementPresent(LoginPage.loginWithFacebook, deviceIndex),
				"Login with Facebook button is not present on Login Page on device: " + deviceName);
		logger.info("Login with Facebook button is present on Login Page on device: " + deviceName);
		Assert.assertTrue(isElementPresent(LoginPage.loginWithTwitter, deviceIndex),
				"Login with Twitter button is not present on Login Page on device: " + deviceName);
		logger.info("Login with Twitter button is present on Login Page on device: " + deviceName);
		Assert.assertTrue(isElementPresent(LoginPage.loginWithGooglePlus, deviceIndex),
				"Login with Google Plus button is not present on Login Page on device: " + deviceName);
		logger.info("Login with Google Plus button is present on Login Page on device: " + deviceName);
	}

	// Verify buttons on SB popup, click on "Connect to SB" button, also verify if
	// user is logged in SB
	public void connectToSB_viaSBPopup(String phoneNumber, int deviceIndex, String deviceName) {

		objSBPopup.verifySBPopup_clickConnectSB(deviceIndex, deviceName);
		objRegisterSB.verifyUserLoggedInSB(phoneNumber, deviceIndex, deviceName);
	}

	
	
	
	
	// Verify Zee5 Login screen buttons, login with Phone Number or Email ID based
	// on "loginUsing" parameter
	public void verifyZee5LoginPage_loginWithPhoneNumber_orEmail(String phoneNumber, String email, String phoneNumPswrd,
			String emailPswrd, int deviceIndex, String deviceName, String loginUsing) {

		verifyButtonsON_Zee5LoginPage(deviceIndex, deviceName);
		if (loginUsing.contentEquals("phoneNum")) {

			clickLoginViaMobileNum(deviceIndex, deviceName);
			logger.info("Let's login using Phone Number to Zee5 on device: " + deviceName);
			objLoginViaPhNno.loginWithPhoneNum(phoneNumber, phoneNumPswrd, deviceIndex, deviceName);
			logger.info("You are logged in successfully with number " + phoneNumber + " on device: " + deviceName);
		} else if (loginUsing.contentEquals("emailID")) {
			clickLoginViaEmail(deviceIndex, deviceName);
			logger.info("Let's login using Email ID to Zee5 on device: " + deviceName);
			objLoginViaEmail.loginWithEmailID(email, emailPswrd, deviceIndex, deviceName);
			logger.info("You are logged in successfully with email " + email + " on device: " + deviceName);
		}
	}

	// Navigate to Language screen, uncheck English language and update
	public void navigateToLanguageScreen_update_language(int deviceIndex, String deviceName) {

		objZee5Permission.navigateLanguageScreen(deviceIndex, deviceName);
		objLangPage.updateLanguage(deviceIndex, deviceName);
	}

	// Connect to SB zone, login to Zee5 and navigate to Zee5 list to watch page
	public void connectToSB_login_ViaPhoneNum_or_ViaEmailID_Zee5_navigateToListTowatch(String phoneNumber, String email,
			String phoneNumPswrd, String emailPswrd, int deviceIndex, String deviceName, String loginUsing) {
		// Location Permission popup
		if (isElementPresent(GivePermission.givePermissionBtn, deviceIndex)) {

			logger.info("App is lasunched and Location Permission screen appeared on device: " + deviceName);
			objZee5Permission.giveLocationPermission(deviceIndex, deviceName);

			// SugarBox popup after allowing location permission
			if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

				logger.info("Connect to SugarBox popup appeared on Zee5 Welcome Screen on device: " + deviceName);
				connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
				navigateToLanguageScreen_update_language(deviceIndex, deviceName);

				// SugarBox popup after updating language on login page
				if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

					logger.info("Connect to SugarBox popup appeared  on Zee5 Login Page on device: " + deviceName);
					connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
					verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
							deviceIndex, deviceName, loginUsing);
				}
				// When SB popup doesn't appear on Zee5 Login Page
				else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {
					verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
							deviceIndex, deviceName, loginUsing);
				}
			}
			// when update language button appears without SB popup
			else if (isElementPresent(Zee5LocationPermission.welcomeToZee.updateLanguageBtn, deviceIndex)) {

				navigateToLanguageScreen_update_language(deviceIndex, deviceName);

				// SugarBox popup after updating language on login page
				if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

					logger.info("Connect to SugarBox popup appeared  on Zee5 Login Page on device: " + deviceName);
					connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
					verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
							deviceIndex, deviceName, loginUsing);
				}
				// When SB popup doesn't appear on Zee5 Login Page
				else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {
					verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
							deviceIndex, deviceName, loginUsing);
				}
			}
			// if location permission already given and Zee5 welcome sreen appears
		} else if (isElementPresent(Zee5LocationPermission.welcomeToZee.updateLanguageBtn, deviceIndex)) {

			objZee5Permission.navigateLanguageScreen(deviceIndex, deviceName);
			objLangPage.updateLanguage(deviceIndex, deviceName);

			// SugarBox popup after updating language on login page
			if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

				logger.info("Connect to SugarBox popup appeared  on Zee5 Login Page on device: " + deviceName);
				connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
				verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
						deviceIndex, deviceName, loginUsing);
			}

			// When SB popup doesn't appear on Zee5 Login Page
			else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {
				verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
						deviceIndex, deviceName, loginUsing);
			}
			// location permission is given and SB popup appears on Zee5 Login Page
		} else if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

			logger.info("App is launched and Connect to SugarBox popup appeared on device: " + deviceName);
			connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);

			if (isElementPresent(Zee5LocationPermission.welcomeToZee.updateLanguageBtn, deviceIndex)) {

				objZee5Permission.navigateLanguageScreen(deviceIndex, deviceName);
				objLangPage.updateLanguage(deviceIndex, deviceName);

				// SugarBox popup after updating language on login page
				if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

					logger.info("Connect to SugarBox popup appeared  on Zee5 Login Page on device: " + deviceName);
					connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
					verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
							deviceIndex, deviceName, loginUsing);
				}
				// When SB popup doesn't appear on Zee5 Login Page
				else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {
					verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
							deviceIndex, deviceName, loginUsing);
				}
			}
			// Zee5 login page after connecting to SB
			else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {

				verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd,
						deviceIndex, deviceName, loginUsing);
				// Verify if user is already logged in to Zee5
			} else if (objZeeListToWatch.verifyUserLoggedInToZee(deviceIndex)) {
				logger.info("You are already logged in to Zee5 on device: " + deviceName);
			}

			// SB popup doesn't appear then login to Zee5 using phone number
		} else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {

			logger.info("SugarBox popup dosen't appear on Splash screen on device: " + deviceName);
			verifyZee5LoginPage_loginWithPhoneNumber_orEmail(phoneNumber, email, phoneNumPswrd, emailPswrd, deviceIndex,
					deviceName, loginUsing);

			// Already logged in directly navigated to Zee5 "List to watch" page
		} else if (objZeeListToWatch.verifyUserLoggedInToZee(deviceIndex) == false) {

			logger.info("You are already logged in to Zee5 on device: " + deviceName);
		}
		// When it takes more time to find above elements closing the app
		else {
			logger.info("It's taking more time than usual on device: " + deviceName);
			tearDown(deviceIndex, deviceName);
		}
	}

	public void connectToSB_guestUser_Zee5HomePage(String phoneNumber, int deviceIndex, String deviceName) {

		// Location Permission popup
		if (isElementPresent(GivePermission.givePermissionBtn, deviceIndex)) {

			logger.info("App is launched and Location Permission screen appeared on device: " + deviceName);
			objZee5Permission.giveLocationPermission(deviceIndex, deviceName);

			// SugarBox popup after allowing location permission
			if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

				logger.info("Connect to SugarBox popup appeared on Zee5 Welcome Screen on device: " + deviceName);
				connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
				navigateToLanguageScreen_update_language(deviceIndex, deviceName);

				// SugarBox popup after updating language on login page
				if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

					logger.info("Connect to SugarBox popup appeared  on Zee5 Login Page on device: " + deviceName);
					connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
					skipLoginPage(deviceIndex, deviceName);
				} // To skip Zee5 Login page
				else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {
					skipLoginPage(deviceIndex, deviceName);
				}
			}
			// when update language button appears without SB popup
			else if (isElementPresent(Zee5LocationPermission.welcomeToZee.updateLanguageBtn, deviceIndex)) {

				navigateToLanguageScreen_update_language(deviceIndex, deviceName);

				// SugarBox popup after updating language on login page
				if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

					logger.info("Connect to SugarBox popup appeared  on Zee5 Login Page on device: " + deviceName);
					connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
					skipLoginPage(deviceIndex, deviceName);
				} // To skip Zee5 Login page
				else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {
					skipLoginPage(deviceIndex, deviceName);
				}
			}
			// if location permission already given and Zee5 welcome sreen appears
		} else if (isElementPresent(Zee5LocationPermission.welcomeToZee.updateLanguageBtn, deviceIndex)) {

			objZee5Permission.navigateLanguageScreen(deviceIndex, deviceName);
			objLangPage.updateLanguage(deviceIndex, deviceName);

			// SugarBox popup after updating language on login page
			if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

				logger.info("Connect to SugarBox popup appeared  on Zee5 Login Page on device: " + deviceName);
				connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
				skipLoginPage(deviceIndex, deviceName);
			} // To skip Zee5 Login page
			else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {

				logger.info("SugarBox popup dosen't appear on Splash screen on device: " + deviceName);
				skipLoginPage(deviceIndex, deviceName);
			}
			// location permission is given and SB popup appears on Zee5 Login Page
		} else if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

			logger.info("App is launched and Connect to SugarBox popup appeared on device: " + deviceName);
			connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);

			if (isElementPresent(Zee5LocationPermission.welcomeToZee.updateLanguageBtn, deviceIndex)) {

				objZee5Permission.navigateLanguageScreen(deviceIndex, deviceName);
				objLangPage.updateLanguage(deviceIndex, deviceName);

				// SugarBox popup after updating language on login page
				if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

					logger.info("Connect to SugarBox popup appeared  on Zee5 Login Page on device: " + deviceName);
					connectToSB_viaSBPopup(phoneNumber, deviceIndex, deviceName);
					skipLoginPage(deviceIndex, deviceName);
				} // To skip Zee5 Login page
				else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {

					logger.info("SugarBox popup dosen't appear on Splash screen on device: " + deviceName);
					skipLoginPage(deviceIndex, deviceName);
				}
			}
		} // SB popup doesn't appear on Zee5 login page then skipping login page
		else if (isElementPresent(LoginPage.loginViaMobileNo, deviceIndex)) {

			logger.info("SugarBox popup dosen't appear on Splash screen on device: " + deviceName);
			skipLoginPage(deviceIndex, deviceName);
		}
		// When it takes more time to find above elements closing the app
		else {
			logger.info("It's taking more time than usual on device: " + deviceName);
			tearDown(deviceIndex, deviceName);
		}
	}
}
