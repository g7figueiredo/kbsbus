package br.com.kebase.logs;

import org.apache.log4j.PropertyConfigurator;

public class ApplicationProperties {

	private static final String CONFIG_LOG4J = "log4j.properties";
	
	public static void load() {
		PropertyConfigurator.configureAndWatch(CONFIG_LOG4J, 1000000);
	}

	
	

}
