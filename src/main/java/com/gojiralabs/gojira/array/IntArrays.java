package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

public class IntArrays {
	private IntArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(int[] array, int element) {
		int index = 0;
		for (int current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(int[] array, int element) {
		return indexOf(array, element) != -1;
	}

	public static int[] add(int[] array, int element) {
		return add(array, element, array.length);
	}

	public static int[] add(int[] array, int element, int index) {
		return add(array, new int[] { element }, index);
	}

	public static int[] add(int[] array, int[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? -1 : 0));
		int[] copy = new int[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	public static int[] remove(int[] array, int index) {
		return remove(array, index, index);
	}

	public static int[] remove(int[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		int[] copy = new int[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	public static Integer[] box(int[] array) {
		Integer[] copy = new Integer[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static int[] unbox(Integer[] array) {
		int[] copy = new int[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static int[] deepCopy(int[] array) {
		int[] copy = new int[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static int[] reverse(int[] array) {
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

	public static int[] concat(int[] firstArray, int[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
