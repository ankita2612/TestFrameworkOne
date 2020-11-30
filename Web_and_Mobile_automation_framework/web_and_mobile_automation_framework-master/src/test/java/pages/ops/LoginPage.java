package pages.ops;

import org.openqa.selenium.By;

import utils.SeleniumUtils;

public class LoginPage extends SeleniumUtils {

	public static class LoginPage_locators {

		public static final By loginWithPass = By.xpath("//span[contains(text(),'LOGIN WITH PASSWORD')]");
		public static final By emailTextBox = By.id("emailOrMobile");
		public static final By passBox = By.id("password");
		public static final By loginBtn = By.xpath("//span[contains(text(),'Login')]");

	}

	public void clickLoginWithPass() {

		clickElement(LoginPage_locators.loginWithPass);
	}

	public void enterCred_andLogin(String email, String password) {

		sendKeysInTextBox(LoginPage_locators.emailTextBox, email);
		sendKeysInTextBox(LoginPage_locators.passBox, password);
		clickElement(LoginPage_locators.loginBtn);
	}

	public void loginUsingEmail_andPassword(String email, String password) {

		clickLoginWithPass();
		enterCred_andLogin(email, password);
	}
}
