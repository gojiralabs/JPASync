package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

import javax.annotation.Nonnull;

public class BooleanArrays {
	private BooleanArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(@Nonnull boolean[] array, boolean element) {
		int index = 0;
		for (boolean current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(@Nonnull boolean[] array, boolean element) {
		return indexOf(array, element) != -1;
	}

	@Nonnull
	public static boolean[] add(@Nonnull boolean[] array, boolean element) {
		return add(array, element, array.length);
	}

	@Nonnull
	public static boolean[] add(@Nonnull boolean[] array, boolean element, int index) {
		return add(array, new boolean[] { element }, index);
	}

	@Nonnull
	public static boolean[] add(@Nonnull boolean[] array, @Nonnull boolean[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		boolean[] copy = new boolean[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	@Nonnull
	public static boolean[] remove(@Nonnull boolean[] array, int index) {
		return remove(array, index, index);
	}

	@Nonnull
	public static boolean[] remove(@Nonnull boolean[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		boolean[] copy = new boolean[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	@Nonnull
	public static Boolean[] box(@Nonnull boolean[] array) {
		Boolean[] copy = new Boolean[array.length];
		int i = 0;
		for (boolean element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static boolean[] unbox(@Nonnull Boolean[] array) {
		boolean[] copy = new boolean[array.length];
		int i = 0;
		for (Boolean element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static boolean[] deepCopy(@Nonnull boolean[] array) {
		boolean[] copy = new boolean[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	@Nonnull
	public static boolean[] reverse(@Nonnull boolean[] array) {
		boolean[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			boolean holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}

	@Nonnull
	public static boolean[] concat(@Nonnull boolean[] firstArray, @Nonnull boolean[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
