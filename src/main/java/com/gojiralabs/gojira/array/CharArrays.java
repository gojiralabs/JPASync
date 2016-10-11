package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

public class CharArrays {
	private CharArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(char[] array, char element) {
		int index = 0;
		for (char current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(char[] array, char element) {
		return indexOf(array, element) != -1;
	}

	public static char[] add(char[] array, char element) {
		return add(array, element, array.length);
	}

	public static char[] add(char[] array, char element, int index) {
		return add(array, new char[] { element }, index);
	}

	public static char[] add(char[] array, char[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? -1 : 0));
		char[] copy = new char[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	public static char[] remove(char[] array, int index) {
		return remove(array, index, index);
	}

	public static char[] remove(char[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		char[] copy = new char[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	public static Character[] box(char[] array) {
		Character[] copy = new Character[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static char[] unbox(Character[] array) {
		char[] copy = new char[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static char[] deepCopy(char[] array) {
		char[] copy = new char[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static char[] reverse(char[] array) {
		char[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			char holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}

	public static char[] concat(char[] firstArray, char[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
