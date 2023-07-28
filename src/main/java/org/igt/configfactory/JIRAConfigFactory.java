package org.igt.configfactory;

import org.aeonbits.owner.ConfigCache;
import org.test.config.JIRAConfig;

public class JIRAConfigFactory {

private JIRAConfigFactory() {}
	
	public static JIRAConfig getConfig() {	
		return ConfigCache.getOrCreate(JIRAConfig.class);
	}
}
