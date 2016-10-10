package com.gojiralabs.gojira.common;

import javax.annotation.Nonnull;

public class Checker {

	public static <T> T notNull(T object) {
		if (object == null) {
			throw new NullPointerException();
		}
		return object;
	}

	public static <T> T notNull(T object, @Nonnull String message, @Nonnull Object... messageParameters) {
		if (object == null) {
			throw new NullPointerException(String.format(message, messageParameters));
		}
		return object;
	}
}
