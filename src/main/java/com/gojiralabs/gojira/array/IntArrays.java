package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArgument;
import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

import javax.annotation.Nonnull;

public class IntArrays {
	private IntArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(@Nonnull int[] array, int element) {
		int index = 0;
		for (int current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(@Nonnull int[] array, int element) {
		return indexOf(array, element) != -1;
	}

	@Nonnull
	public static int[] add(@Nonnull int[] array, int element) {
		return add(array, element, array.length);
	}

	@Nonnull
	public static int[] add(@Nonnull int[] array, int element, int index) {
		return add(array, new int[] { element }, index);
	}

	@Nonnull
	public static int[] add(@Nonnull int[] array, @Nonnull int[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		int[] copy = new int[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	@Nonnull
	public static int[] remove(@Nonnull int[] array, int index) {
		return remove(array, index, index);
	}

	@Nonnull
	public static int[] remove(@Nonnull int[] array, int fromIndex, int toIndex) {
		checkArgument(fromIndex <= toIndex, "fromIndex cannot be greater than toIndex");
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		int[] copy = new int[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	@Nonnull
	public static Integer[] box(@Nonnull int[] array) {
		Integer[] copy = new Integer[array.length];
		int i = 0;
		for (Integer element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static int[] unbox(@Nonnull Integer[] array) {
		int[] copy = new int[array.length];
		int i = 0;
		for (int element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static int[] deepCopy(@Nonnull int[] array) {
		int[] copy = new int[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	@Nonnull
	public static int[] reverse(@Nonnull int[] array) {
		int[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			int holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}

	@Nonnull
	public static int[] concat(@Nonnull int[] firstArray, @Nonnull int[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
