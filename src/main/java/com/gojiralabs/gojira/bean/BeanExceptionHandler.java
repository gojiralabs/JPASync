package com.gojiralabs.gojira.bean;

import javax.annotation.Nonnull;

class BeanExceptionHandler {

	private BeanExceptionHandler() {
		// private constructor to avoid instantiation
	}

	public static <T> T handle(@Nonnull Exception e, boolean silenceExceptions) {
		if (silenceExceptions) {
			return null;
		}
		throw new BeanException(e);
	}
}
