package com.gojiralabs.gojira.reflection;

public class ReflectionException extends RuntimeException {
	private static final long serialVersionUID = 7166214828268602633L;

	public ReflectionException() {
		super();
	}

	public ReflectionException(Throwable cause, String message, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ReflectionException(Throwable cause, String message) {
		super(message, cause);
	}

	public ReflectionException(Throwable cause, String messageFormat, Object... messageParameters) {
		super(String.format(messageFormat, messageParameters), cause);
	}

	public ReflectionException(String message) {
		super(message);
	}

	public ReflectionException(String messageFormat, Object... messageParameters) {
		super(String.format(messageFormat, messageParameters));
	}

	public ReflectionException(Throwable cause) {
		super(cause);
	}
}
