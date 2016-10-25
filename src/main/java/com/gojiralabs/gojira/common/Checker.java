package com.gojiralabs.gojira.common;

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

	public static <T> T notNull(T object, String message, Object... messageParameters) {
		if (object == null) {
			// this is really supposed to throw a NullPointerException
			throw new NullPointerException(String.format(message, messageParameters)); // NOSONAR
		}
		return object;
	}

	public static void checkArrayIndex(boolean[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(byte[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(short[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(char[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(int[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(long[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(float[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static void checkArrayIndex(double[] array, int index) {
		checkArrayIndex(array.length, index);
	}

	public static <T> void checkArrayIndex(T[] array, int index) {
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

	public static void checkArgument(boolean condition, String message, Object... messageParameters) {
		if (!condition) {
			throw new IllegalArgumentException(String.format(message, messageParameters));
		}
	}
}
