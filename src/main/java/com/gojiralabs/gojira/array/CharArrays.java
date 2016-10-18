package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

import javax.annotation.Nonnull;

public class CharArrays {
	private CharArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(@Nonnull char[] array, char element) {
		int index = 0;
		for (char current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(@Nonnull char[] array, char element) {
		return indexOf(array, element) != -1;
	}

	@Nonnull
	public static char[] add(@Nonnull char[] array, char element) {
		return add(array, element, array.length);
	}

	@Nonnull
	public static char[] add(@Nonnull char[] array, char element, int index) {
		return add(array, new char[] { element }, index);
	}

	@Nonnull
	public static char[] add(@Nonnull char[] array, @Nonnull char[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		char[] copy = new char[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	@Nonnull
	public static char[] remove(@Nonnull char[] array, int index) {
		return remove(array, index, index);
	}

	@Nonnull
	public static char[] remove(@Nonnull char[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		char[] copy = new char[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	@Nonnull
	public static Character[] box(@Nonnull char[] array) {
		Character[] copy = new Character[array.length];
		int i = 0;
		for (char element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static char[] unbox(@Nonnull Character[] array) {
		char[] copy = new char[array.length];
		int i = 0;
		for (Character element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static char[] deepCopy(@Nonnull char[] array) {
		char[] copy = new char[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	@Nonnull
	public static char[] reverse(@Nonnull char[] array) {
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

	@Nonnull
	public static char[] concat(@Nonnull char[] firstArray, @Nonnull char[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
