package com.gojiralabs.gojira.common;

import javax.annotation.Nonnull;

public class Checker {

	private Checker() {
		// private constructor to avoid instantiation
	}

	public static <T> T notNull(T object) {
		if (object == null) {
			// this is really supposed to throw a NullPointerException
			throw new NullPointerException(); // NOSONAR
		}
		return object;
	}

	public static <T> T notNull(T object, @Nonnull String message, @Nonnull Object... messageParameters) {
		if (object == null) {
			// this is really supposed to throw a NullPointerException
			throw new NullPointerException(String.format(message, messageParameters)); // NOSONAR
		}
		return object;
	}

	public static void checkArrayIndex(@Nonnull boolean[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(@Nonnull byte[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(@Nonnull short[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(@Nonnull char[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(@Nonnull int[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(@Nonnull long[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(@Nonnull float[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(@Nonnull double[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static <T> void checkArrayIndex(@Nonnull T[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	private static void checkArrayIndex(int arrayLength, int index) {
		if (index < 0 || index >= arrayLength) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	public static void checkArgument(boolean condition) {
		if (!condition) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkArgument(boolean condition, @Nonnull String message, @Nonnull Object... messageParameters) {
		if (!condition) {
			throw new IllegalArgumentException(String.format(message, messageParameters));
		}
	}
}
