package org.igt.jira;

import org.igt.configfactory.JIRAConfigFactory;
import net.rcarz.jiraclient.Issue;

public class SendResultsToJIRA {
	
	public static Issue pushBugToJIRA(String issueSummary, String issueDescription) {			
			JIRAIntegration jira = new JIRAIntegration(JIRAConfigFactory.getConfig().jiraurl(), JIRAConfigFactory.getConfig().jirausername(), JIRAConfigFactory.getConfig().jirapassword(), JIRAConfigFactory.getConfig().jiraproject());		
			Issue bugID =jira.createJiraIssue("Bug", issueSummary, issueDescription);
			jira.getJiraIssue()
			           .addComment()
			           .addScreenshot();	
			return bugID;
	
	}
}
