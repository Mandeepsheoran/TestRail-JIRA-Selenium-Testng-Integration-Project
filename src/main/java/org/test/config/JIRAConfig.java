package org.test.config;

import org.aeonbits.owner.Config;

/**
 * Configuration interface to provide jira connection details from property file.
 * May 17, 2023
 * @author Mandeep Sheoran
 * @version 1.0
 * @see Config
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ 
	"system:properties", 
	"system:env",
	"file:${user.dir}/src/test/resources/jiraconfig.properties"
	})
public interface JIRAConfig extends Config{
	@Key("JIRA_URL")
	String jiraurl();
	
	@Key("JIRA_USERNAME")
	String jirausername();
	
	@Key("JIRA_PASSWORD")
	String jirapassword();
	
	@Key("JIRA_PROJECT")
	String jiraproject();
}
