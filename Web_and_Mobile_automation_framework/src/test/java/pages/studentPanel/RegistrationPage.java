package pages.studentPanel;

import org.openqa.selenium.By;

import utils.SeleniumUtils;

public class RegistrationPage extends SeleniumUtils {

	public static class RegistrationPage_locators {

		// email ID locator
		public static final By email_ID_textBox = By.xpath("//*[@id='email']");

		// parent name
		public static final By parent_name = By.xpath("//*[@id='parentName']");

		// dial code
		public static final By dial_code = By.xpath("//*[@id='dialCode']");

		// mobile no
		public static final By mobile_no = By.xpath("//*[@id='mobile']");

		// kid name
		public static final By kid_name = By.xpath("//*[@id='studentName']");

		// all grade labels
		public static final By kid_grade = By.xpath("//*[@id='grade']/label");

		// is Laptop
		public static final By is_laptop = By.xpath("//*[@id='isLaptop']/label[1]");

		// schedule Free Trial button
		public static final By scheduleFreeTrial = By.xpath("//div[contains(text(),'Schedule a Free Trial')]");

		// Grade dropdown
		public static final By gradeDropdown = By.xpath("//select[@name='grade']");

	}

	public void enterEmail_ID(String student_email_id) {

		sendKeysInTextBox(RegistrationPage_locators.email_ID_textBox, student_email_id);
	}

	public void enterParent_name(String student_parent_name) {

		sendKeysInTextBox(RegistrationPage_locators.parent_name, student_parent_name);
	}

	public void enterMobile_no(String student_mobile_no) {

		sendKeysInTextBox(RegistrationPage_locators.mobile_no, student_mobile_no);
	}

	public void enterKid_name(String student_kid_name) {

		sendKeysInTextBox(RegistrationPage_locators.kid_name, student_kid_name);
	}

	public void chooseGrade(int index, String value) {

		if (isElementPresent(RegistrationPage_locators.gradeDropdown) == true) {

			chooseGradeFrom_dropDown(value);
		} else {

			findElements_andClickViaIndex(RegistrationPage_locators.kid_grade, index);
		}
	}

	public void chooseYes_lapop() {

		clickElement(RegistrationPage_locators.is_laptop);
	}

	public void clickScheduleFreeTrial() {

		clickElement(RegistrationPage_locators.scheduleFreeTrial);
	}

	public void chooseGradeFrom_dropDown(String value) {

		selectOption_withValue(RegistrationPage_locators.gradeDropdown, value);

	}

	public void registerNewStudent(String student_email_id, String student_parent_name, String student_mobile_no,
			String student_kid_name, int index, String value) {

		enterEmail_ID(student_email_id);
		enterParent_name(student_parent_name);
		enterMobile_no(student_mobile_no);
		enterKid_name(student_kid_name);
		chooseGrade(index, value);
		chooseYes_lapop();
		clickScheduleFreeTrial();

	}

}
