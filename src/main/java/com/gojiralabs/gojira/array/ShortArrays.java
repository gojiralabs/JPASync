package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArgument;
import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

public class ShortArrays {
	private ShortArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(short[] array, short element) {
		int index = 0;
		for (short current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(short[] array, short element) {
		return indexOf(array, element) != -1;
	}

	public static short[] add(short[] array, short element) {
		return add(array, element, array.length);
	}

	public static short[] add(short[] array, short element, int index) {
		return add(array, new short[] { element }, index);
	}

	public static short[] add(short[] array, short[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		short[] copy = new short[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	public static short[] remove(short[] array, int index) {
		return remove(array, index, index);
	}

	public static short[] remove(short[] array, int fromIndex, int toIndex) {
		checkArgument(fromIndex <= toIndex, "fromIndex cannot be greater than toIndex");
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		short[] copy = new short[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	public static Short[] box(short[] array) {
		Short[] copy = new Short[array.length];
		int i = 0;
		for (Short element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	public static short[] unbox(Short[] array) {
		short[] copy = new short[array.length];
		int i = 0;
		for (short element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	public static short[] deepCopy(short[] array) {
		short[] copy = new short[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static short[] reverse(short[] array) {
		short[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			short holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}

	public static short[] concat(short[] firstArray, short[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
