package com.gurock.testrail;

/**
 * Custom exception class to throw for TestRail API related exceptions.
 * May 18, 2023
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 */
public class APIException extends Exception{

	public APIException(String message) {
		super(message);
	}
}
