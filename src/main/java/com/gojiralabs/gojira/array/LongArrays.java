package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArgument;
import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

public class LongArrays {
	private LongArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(long[] array, long element) {
		int index = 0;
		for (long current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(long[] array, long element) {
		return indexOf(array, element) != -1;
	}

	public static long[] add(long[] array, long element) {
		return add(array, element, array.length);
	}

	public static long[] add(long[] array, long element, int index) {
		return add(array, new long[] { element }, index);
	}

	public static long[] add(long[] array, long[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		long[] copy = new long[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	public static long[] remove(long[] array, int index) {
		return remove(array, index, index);
	}

	public static long[] remove(long[] array, int fromIndex, int toIndex) {
		checkArgument(fromIndex <= toIndex, "fromIndex cannot be greater than toIndex");
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		long[] copy = new long[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	public static Long[] box(long[] array) {
		Long[] copy = new Long[array.length];
		int i = 0;
		for (Long element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	public static long[] unbox(Long[] array) {
		long[] copy = new long[array.length];
		int i = 0;
		for (long element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	public static long[] deepCopy(long[] array) {
		long[] copy = new long[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static long[] reverse(long[] array) {
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

	public static long[] concat(long[] firstArray, long[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
