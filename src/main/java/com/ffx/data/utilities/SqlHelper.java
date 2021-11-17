package com.ffx.data.utilities;

/**
 * Provides helper methods for generating SQL statments
 * 
 * @author Louis Drotos
 */
public class SqlHelper {

	/**
	 * Converts a string property value into a SQL
	 * insert value. 
	 * 
	 * @param columnValue The property value
	 * @return The SQL value
	 */
	public static String createColumnInsertValue(String columnValue) {
		return columnValue != null ? "'" + columnValue + "'" : "NULL";
	}
}
