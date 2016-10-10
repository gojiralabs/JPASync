package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

public class ByteArrays {
	private ByteArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(byte[] array, byte element) {
		int index = 0;
		for (byte current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(byte[] array, byte element) {
		return indexOf(array, element) != -1;
	}

	public static byte[] add(byte[] array, byte element) {
		return add(array, element, array.length);
	}

	public static byte[] add(byte[] array, byte element, int index) {
		return add(array, new byte[] { element }, index);
	}

	public static byte[] add(byte[] array, byte[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? -1 : 0));
		byte[] copy = new byte[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	public static byte[] remove(byte[] array, int index) {
		return remove(array, index, index);
	}

	public static byte[] remove(byte[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		byte[] copy = new byte[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	public Byte[] boxArray(byte[] array) {
		Byte[] copy = new Byte[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public byte[] unboxArray(Byte[] array) {
		byte[] copy = new byte[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static byte[] deepCopy(byte[] array) {
		byte[] copy = new byte[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static byte[] reverse(byte[] array) {
		byte[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			byte holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}
}
