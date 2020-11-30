package tests.hackathon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.hackathon.RegistrationPage;
import utils.SeleniumUtils;

public class RegisterStudentOnHackathon extends SeleniumUtils {

	RegistrationPage objRegister = new RegistrationPage();

	@BeforeClass
	@Parameters({ "browser", "propPath" })
	public void launchWebsite(String browser, String propPath) {

		openBrowser(browser, propPath);
		getURL(prop.getProperty("hackathonStagUrl"));
		logger.info("User is navigated to Registration Page.");
	}

	@Test
	@Parameters({ "studentEmail", "studentNumber", "grade" })
	public void registerStudent(String studentEmail, String studentNumber, int grade) throws Exception {

		objRegister.registerStudentOnHackthon(prop.getProperty("studentName"), prop.getProperty("parentName"),
				studentEmail, studentNumber, grade);
		Thread.sleep(4000);
	}

	@AfterClass
	public void closeBrowser() {
		tearDown();
	}
}
