package com.gojiralabs.gojira.bean;

public class BeanException extends RuntimeException {
	private static final long serialVersionUID = 2489652540420155157L;

	public BeanException() {
		super();
	}

	public BeanException(Throwable cause, String message, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BeanException(Throwable cause, String message) {
		super(message, cause);
	}

	public BeanException(Throwable cause, String messageFormat, Object... messageParameters) {
		super(String.format(messageFormat, messageParameters), cause);
	}

	public BeanException(String message) {
		super(message);
	}

	public BeanException(String messageFormat, Object... messageParameters) {
		super(String.format(messageFormat, messageParameters));
	}

	public BeanException(Throwable cause) {
		super(cause);
	}
}
