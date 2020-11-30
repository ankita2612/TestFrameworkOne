package tests.mainUI;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.ops.HomePage;
import pages.ops.LoginPage;
import pages.ops.StudentPage;
import pages.studentPanel.RegistrationPage;
import utils.SeleniumUtils;

public class StudentRegistration extends SeleniumUtils {

	RegistrationPage objRegisterStudent = new RegistrationPage();
	LoginPage objOpsLogin = new LoginPage();
	HomePage objOpsHomePage = new HomePage();
	StudentPage objOpsStudent = new StudentPage();

	@BeforeClass
	@Parameters({ "browser", "propPath" })
	public void launchWebsite(String browser, String propPath) {

		openBrowser(browser, propPath);
		getURL(prop.getProperty("stageUrl"));
		logger.info("User is navigated to Registration Page.");
	}

	@Test
	@Parameters({ "grade", "gradeValue", "studentEmail", "studentNumber" })
	public void registerStudent(int grade, String gradeValue, String studentEmail, String studentNumber)
			throws Exception {

		objRegisterStudent.registerNewStudent(studentEmail, prop.getProperty("parentName"), studentNumber,
				prop.getProperty("studentName"), grade, gradeValue);
		Thread.sleep(4000);
		/*
		 * navigateToURL(prop.getProperty("opsStageUrl"));
		 * objOpsLogin.loginUsingEmail_andPassword(prop.getProperty("opsStageEmail"),
		 * prop.getProperty("opsStagePass")); objOpsHomePage.navigateToStudents();
		 * objOpsStudent.searchStudent_andNavigate_toDetails(studentEmail);
		 */
	}

	@AfterClass
	public void closeBrowser() {
		tearDown();
	}
}
