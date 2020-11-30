package pages.ops;

import org.openqa.selenium.By;

import utils.SeleniumUtils;

public class StudentPage extends SeleniumUtils{

	public static class StudentPage_locators {

		public static final By emailField = By.xpath("//label[contains(text(), 'Email')]");
		public static final By searchStudentBtn = By.xpath("//button[contains(text(), 'Search Students')]");
		public static final By detailsBtn = By.xpath("//button[contains(text(), 'Details')]");
		
	}
	
	public void searchStudent_andNavigate_toDetails(String email) {
		
		sendKeysInTextBox(StudentPage_locators.emailField, email);
		clickElement(StudentPage_locators.searchStudentBtn);
		clickElement(StudentPage_locators.detailsBtn);
	}
}
