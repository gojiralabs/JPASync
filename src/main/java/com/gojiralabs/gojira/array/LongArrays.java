package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

import javax.annotation.Nonnull;

public class LongArrays {
	private LongArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(@Nonnull long[] array, long element) {
		int index = 0;
		for (long current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(@Nonnull long[] array, long element) {
		return indexOf(array, element) != -1;
	}

	@Nonnull
	public static long[] add(@Nonnull long[] array, long element) {
		return add(array, element, array.length);
	}

	@Nonnull
	public static long[] add(@Nonnull long[] array, long element, int index) {
		return add(array, new long[] { element }, index);
	}

	@Nonnull
	public static long[] add(@Nonnull long[] array, @Nonnull long[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		long[] copy = new long[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	@Nonnull
	public static long[] remove(@Nonnull long[] array, int index) {
		return remove(array, index, index);
	}

	@Nonnull
	public static long[] remove(@Nonnull long[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		long[] copy = new long[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	@Nonnull
	public static Long[] box(@Nonnull long[] array) {
		Long[] copy = new Long[array.length];
		int i = 0;
		for (Long element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static long[] unbox(@Nonnull Long[] array) {
		long[] copy = new long[array.length];
		int i = 0;
		for (long element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static long[] deepCopy(@Nonnull long[] array) {
		long[] copy = new long[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	@Nonnull
	public static long[] reverse(@Nonnull long[] array) {
		long[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			long holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}

	@Nonnull
	public static long[] concat(@Nonnull long[] firstArray, @Nonnull long[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
