package org.igt.utils;

import java.util.HashMap;
import java.util.Map;

import org.igt.configfactory.TestRailFactory;

import com.gurock.testrail.APIClient;

public class TestRailSetUp {

	public static String TEST_RUN_ID = TestRailFactory.getConfig().testrailrunid();
	public static String TEST_RAIL_USERNAME = TestRailFactory.getConfig().testrailusername();
	public static String TEST_RAIL_PASSWORD = TestRailFactory.getConfig().testrailpassword();
	public static String TEST_RAIL_ENGINE_URL = TestRailFactory.getConfig().testrailurl();
	public static int TEST_CASE_PASS_STATUS = 1;
	public static int TEST_CASE_FAIL_STATUS = 5;

	public static void addResultForTestCase(String testCaseId, int status, String error, String defectid) {
		String testRunID = TEST_RUN_ID;
		APIClient client = new APIClient(TEST_RAIL_ENGINE_URL);
		client.setUser(TEST_RAIL_USERNAME);
		client.setPassword(TEST_RAIL_PASSWORD);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("status_id", status);
		data.put("defects", defectid);
		data.put("comment", "this test is executed - status is updated via selenium test automation " + error);
		try {
			client.sendPost("add_result_for_case/" + testRunID + "/" + testCaseId, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
