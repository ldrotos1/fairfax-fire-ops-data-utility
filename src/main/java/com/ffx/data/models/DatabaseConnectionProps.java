package com.ffx.data.models;

import lombok.Builder;
import lombok.Getter;

/**
 * Class contains a set of properties used to create 
 * a database connection.
 *  
 * @author Louis Drotos
 */
@Getter
@Builder
public class DatabaseConnectionProps {
	private String host;
	private String port;
	private String databaseName;
	private String user;
	private String password;
	
	/**
	 * Constructs a JDBC URL
	 * 
	 * @return The JDBC URL
	 */
	public String getJdbcUrl() {
		
		StringBuilder url = new StringBuilder();
		url.append("jdbc:postgresql://");
		url.append(host);
		url.append(":");
		url.append(port);
		url.append("/");
		url.append(databaseName);
		return url.toString();
	}
}
