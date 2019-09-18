package com.basic.webservice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Response {
	private String message;
    private Status status;
    private Object data;
    static Map<String, List<Object>> errorListForCsv = new LinkedHashMap<>();
    
    public enum Status {
        SUCCESS, ERROR
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static Map<String, List<Object>> getErrorListForCsv() {
		return errorListForCsv;
	}

	public static void setErrorListForCsv(Map<String, List<Object>> errorListForCsv) {
		Response.errorListForCsv = errorListForCsv;
	}
	public static void removeFromMap(String key) {
		errorListForCsv.remove(key);
	}
    
    
}
