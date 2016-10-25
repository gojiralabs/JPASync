package com.gojiralabs.gojira.common;

import static com.gojiralabs.gojira.common.Checker.checkArgument;

import java.util.Random;

import com.gojiralabs.gojira.array.ArrayGroup;
import com.gojiralabs.gojira.array.CharArrays;

public class Strings {

	private static final Character[] LOWERCASE = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final Character[] UPPERCASE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final Character[] NUMBERS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final Random RANDOM = new Random();

	private Strings() {
		// private constructor to avoid instantiation
	}

	public static String capitalize(String value) {
		return value.isEmpty() ? value : Character.toUpperCase(value.charAt(0)) + value.substring(1);
	}

	public static String decapitalize(String value) {
		return value.isEmpty() ? value : Character.toLowerCase(value.charAt(0)) + value.substring(1);
	}

	public static String randomString(int length, char[] contents) {
		return randomString(length, length, contents);
	}

	public static String randomString(int minimumLength, int maximumLength, char[] contents) {
		return randomizeString(minimumLength, maximumLength, new ArrayGroup<>(CharArrays.box(contents)));
	}

	public static String randomAlphanumeric(int length) {
		return randomAlphanumeric(length, length);
	}

	public static String randomAlphanumeric(int minimumLength, int maximumLength) {
		return randomizeString(minimumLength, maximumLength, new ArrayGroup<>(LOWERCASE, UPPERCASE, NUMBERS));
	}

	public static String randomNumeric(int length) {
		return randomNumeric(length, length);
	}

	public static String randomNumeric(int minimumLength, int maximumLength) {
		return randomizeString(minimumLength, maximumLength, new ArrayGroup<>(NUMBERS));
	}

	public static String randomLowercase(int length) {
		return randomLowercase(length, length);
	}

	public static String randomLowercase(int minimumLength, int maximumLength) {
		return randomizeString(minimumLength, maximumLength, new ArrayGroup<>(LOWERCASE));
	}

	public static String randomUppercase(int length) {
		return randomUppercase(length, length);
	}

	public static String randomUppercase(int minimumLength, int maximumLength) {
		return randomizeString(minimumLength, maximumLength, new ArrayGroup<>(UPPERCASE));
	}

	private static String randomizeString(int minimumLength, int maximumLength, ArrayGroup<Character> group) {
		checkArgument(minimumLength >= 0, "The minimum length cannot be negative");
		checkArgument(maximumLength >= minimumLength, "The maximum length cannot be lesser than the minimum length");
		int length = group.length();
		int lengthVariation = RANDOM.nextInt(maximumLength - minimumLength);
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < minimumLength + lengthVariation; i++) {
			randomString.append(group.get(RANDOM.nextInt(length)));
		}
		return randomString.toString();
	}
}
