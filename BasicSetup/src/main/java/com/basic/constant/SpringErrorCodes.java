package com.basic.constant;

public class SpringErrorCodes {
	
	private SpringErrorCodes() {
		throw new IllegalStateException("Utility class");
	}

	public static final int LOGIN_ERROR_CODE = 1000;
	public static final int CREATE_ERROR_CODE = 1001;
	public static final int UPDATE_ERROR_CODE = 1002;
	public static final int DELETE_ERROR_CODE = 1003;
	public static final int NOT_FOUND_ERROR_CODE = 1004;
	public static final int GET_DATA_ERROR_CODE = 1005;
	public static final int DUPLICATE_DATA_CODE = 1006;
	public static final int SERVER_ERROR_CODE = 1006;
	

	public static final String SERVER_ERROR = "Server error";
	public static final String NO_DATA_FOUND_MSG = "No data found";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong, please try again later.";
	/* login */
	public static final String LOGIN_ERROR_INVALID_CREDIANLS_MSG = "Invalid credentials";
	public static String ERROR_DUPLICATE_EMAIL_MSG="Uswer already present";
	/* User */
	public static final String CREATING_USER_MSG = "User created successfully";
	public static final String ERROR_CREATING_USER_MSG = "Error in creating user";
	
	
}
