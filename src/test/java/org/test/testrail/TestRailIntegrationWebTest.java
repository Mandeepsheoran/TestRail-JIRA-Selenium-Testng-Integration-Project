package org.test.testrail;

import java.io.IOException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.igt.jira.SendResultsToJIRA;
import org.igt.utils.TestRailSetUp;
import org.test.annotationinterface.TestRailAnnotation;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import net.rcarz.jiraclient.Issue;

public class TestRailIntegrationWebTest {

	public final int TEST_CASE_PASSED_STATUS = 1;
	public final int TEST_CASE_FAILED_STATUS = 5;
	protected String testCaseId;
	APIClient client = null;

	@Test(description = "Push test to TestRail")
	@TestRailAnnotation(id = "1")
	public void getRoyalAirPageTitle() {
		testCaseId = "1";
		Assert.assertTrue(true);
	}

	@Test(description = "Push test to TestRail")
	@TestRailAnnotation(id = "2")
	public void executeroyalAirLoginFlow() {
		testCaseId = "2";
		Assert.assertEquals("Royal Air Maroc Airlines - Royal Air Maroc", "TEST");
	}

	@Test(description = "Push test to TestRail")
	@TestRailAnnotation(id = "3")
	public void verifySupportEmailAddress() {
		testCaseId = "3";
		Assert.assertTrue(true);
	}

	@Test(description = "Push test to TestRail")
	@TestRailAnnotation(id = "4")
	public void verifySupportPhoneNo() {
		testCaseId = "4";
		Assert.assertTrue(true);
	}

	@AfterMethod
	public void postTestSteup(ITestResult result, ITestContext ctx) throws IOException, APIException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			TestRailSetUp.addResultForTestCase(testCaseId, TestRailSetUp.TEST_CASE_PASS_STATUS, "", "");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String jiraissueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()
					+ " was failed due to an exception";
			String jiraissueDescription = "Exception details : " + result.getThrowable().getMessage() + "\n";
			jiraissueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			Issue bugID = SendResultsToJIRA.pushBugToJIRA(jiraissueSummary, jiraissueDescription);
			TestRailSetUp.addResultForTestCase(testCaseId, TestRailSetUp.TEST_CASE_FAIL_STATUS,
					String.valueOf(result.getThrowable()), bugID.toString());
		}
	}
}
