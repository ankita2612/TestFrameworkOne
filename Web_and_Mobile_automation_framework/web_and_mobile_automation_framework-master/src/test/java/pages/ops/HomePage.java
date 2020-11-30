package pages.ops;

import org.openqa.selenium.By;

import utils.SeleniumUtils;

public class HomePage extends SeleniumUtils{

	public static class HomePage_locators {

		public static final By opsDropDown = By.xpath("//span[contains(text(), 'Ops')]");
		public static final By studentsBtn = By.xpath("//span[contains(text(), 'Students')]");

	}
	
	public void navigateToStudents() {
		
		clickElement(HomePage_locators.opsDropDown);
		clickElement(HomePage_locators.studentsBtn);
	}
}
