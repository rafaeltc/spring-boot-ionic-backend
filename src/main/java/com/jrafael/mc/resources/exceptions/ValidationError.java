package com.jrafael.mc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
	}

	private static final long serialVersionUID = 1L;

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setList(List<FieldMessage> errors) {
		this.errors = errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName,message));
	}
}
