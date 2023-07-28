# TestRail-JIRA-Selenium-Integration
This project contains integration between TestRail, JIRA and Selenium testng where automation test execution results will be pushed to TestRail and defects will be linked with JIRA stories.

Passed test will be updated directed with manual test case in TestRail.
For failed test case, it will first call JIRA to create a bug and the returned Bug id will be pushed to TestRail which will linked this `bug id with respective test case.

This will help us to find code coverage in TestRail using manual and automation test case metrics.

Following tools are used to achieve this:
- Selenium
- TestNg
- Owner's Library
- Json-Simple
- AssertJ
- JIRA client
- TestRail ApiClient
