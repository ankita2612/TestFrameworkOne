package pages.hackathon;

import org.openqa.selenium.By;

import utils.SeleniumUtils;

public class RegistrationPage extends SeleniumUtils {
//test merge branch ter
	public static class  RegistrationPage_locators {

		public static final By studentName = By.name("studentName");
		public static final By parentName = By.name("parentName");
		public static final By emailTextBox = By.name("email");
		public static final By mobileTextBox = By.name("mobile");
		public static final By yesRadioBtn = By.xpath("//label[contains(text(), 'Yess')]");
		public static final By createAccount = By.xpath("//button[contains(text(), 'Create Account to Play')]");
		public static final By grade = By.xpath("(//div[contains(@class, 'FormikSelectGrades')])");

	}

	public void chooseGrade(int grade) {

		findElements_andClickViaIndex(RegistrationPage_locators.grade, grade);
	}

	public void enterStudentName(String studentName) {

		sendKeysInTextBox(RegistrationPage_locators.studentName, studentName);
	}

	public void enterParentName(String parentName) {

		sendKeysInTextBox(RegistrationPage_locators.parentName, parentName);
	}

	public void enterEmailId(String emailId) {

		sendKeysInTextBox(RegistrationPage_locators.emailTextBox, emailId);
	}

	public void enterMobileNum(String mobileNo) {

		sendKeysInTextBox(RegistrationPage_locators.mobileTextBox, mobileNo);
	}

	public void chooseLaptopYes() {

		waitForElementPresense_AndClick(RegistrationPage_locators.yesRadioBtn);
	}

	public void clickCreateAccount() {

		clickElement(RegistrationPage_locators.createAccount);
	}

	public void registerStudentOnHackthon(String studentName, String parentName, String emailId, String mobileNo,
			int grade) {

		enterStudentName(studentName);
		chooseGrade(grade);
		enterParentName(parentName);
		enterEmailId(emailId);
		enterMobileNum(mobileNo);
		chooseLaptopYes();
		clickCreateAccount();
	}
}
