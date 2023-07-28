package org.igt.jira;

import java.io.File;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JIRAIntegration {
	
	private static JiraClient jira;
	private String project;
	public static Issue newIssue;
	public static Issue issue;

	public JIRAIntegration(String jiraUrl, String username, String password, String project) {
		BasicCredentials creds = new BasicCredentials(username, password);
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}

	public Issue createJiraIssue(String issueType, String summary, String description) { 
		try {
			FluentCreate newIssueFluentCreate = jira.createIssue(project, issueType);			
			newIssueFluentCreate.field(Field.SUMMARY, summary);
			newIssueFluentCreate.field(Field.DESCRIPTION, description);
			 newIssue = newIssueFluentCreate.execute();
			 System.out.println("New bug created. Jira ID : "+ newIssue);		 
			return newIssue;
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JIRAIntegration getJiraIssue() { 
		try {
		 issue =jira.getIssue(String.valueOf(newIssue));
		 System.out.println("Details of created JIRA bug is : "+ issue.getDescription());			
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JIRAIntegration addComment() {
		try {
			issue.addComment("Raised bug due to mismatch in expected and actual result.");
			 System.out.println("Comment added in JIRA against newly raised bug for test failure.");		
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JIRAIntegration addWatcher() {
		try {
			issue.addWatcher("mandeep");
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JIRAIntegration assignBug() {
		try {
			issue.update().field(Field.ASSIGNEE, "vijay sawale").execute();
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JIRAIntegration assignAndChangeBugStatus() {
		try {
			issue.transition().field(Field.ASSIGNEE, "vijay sawale").execute("Open");
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JIRAIntegration getLastAddedComment() {
		String addedcomment=issue.getComments().get(0).getBody();
		 System.out.println("Last added comment is : "+ addedcomment);		
		return this;
	}
	
	public JIRAIntegration addScreenshot() {
		try {
			File file = new File("C:\\Users\\Mandeep.Sheoran\\Pictures\\Capture.png");
			issue.addAttachment(file);
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JIRAIntegration linkIssue() {
		try {
			newIssue.link("AIR-10", "Add web link");
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JIRAIntegration createSubtaskUnderBug() {
		try {
			Issue subtask=newIssue.createSubtask()
					.field(Field.SUMMARY,"Code changes to fix this bug")
					.field(Field.ASSIGNEE, "vijay sawale")
					.execute();
			System.out.println("New subtask under bug"+ newIssue+ " is : "+subtask);
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JIRAIntegration searchIssueByAssignee() {
		try {
			Issue.SearchResult sr = jira.searchIssues("assignee=vijay sawale");
			System.out.println("Total assigned bug to concerned developer are : "+ sr.total); 
			sr.issues.forEach(s->System.out.println(s));
			for(Issue i : sr.issues) {
				System.out.println("Result : "+ i);
			}
		} catch (JiraException e) {
			e.printStackTrace();
		}
		return this;
	}
}
