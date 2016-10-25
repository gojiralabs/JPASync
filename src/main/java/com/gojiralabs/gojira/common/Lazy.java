package com.gojiralabs.gojira.common;

import java.util.function.Supplier;

public class Lazy<T> {

	private Supplier<T> initializer;
	private T value;
	private boolean initialized;

	public static <T> Lazy<T> from(Supplier<T> initializer) {
		return new Lazy<>(initializer);
	}

	private Lazy(Supplier<T> initializer) {
		this.initializer = initializer;
	}

	public synchronized T get() {
		if (!initialized) {
			value = initializer.get();
			initialized = true;
		}
		return value;
	}
}
