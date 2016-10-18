package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

import javax.annotation.Nonnull;

public class ByteArrays {
	private ByteArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(@Nonnull byte[] array, byte element) {
		int index = 0;
		for (byte current : array) {
			if (current == element) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(@Nonnull byte[] array, byte element) {
		return indexOf(array, element) != -1;
	}

	@Nonnull
	public static byte[] add(@Nonnull byte[] array, byte element) {
		return add(array, element, array.length);
	}

	@Nonnull
	public static byte[] add(@Nonnull byte[] array, byte element, int index) {
		return add(array, new byte[] { element }, index);
	}

	@Nonnull
	public static byte[] add(@Nonnull byte[] array, @Nonnull byte[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		byte[] copy = new byte[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	@Nonnull
	public static byte[] remove(@Nonnull byte[] array, int index) {
		return remove(array, index, index);
	}

	@Nonnull
	public static byte[] remove(@Nonnull byte[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		byte[] copy = new byte[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	@Nonnull
	public static Byte[] box(@Nonnull byte[] array) {
		Byte[] copy = new Byte[array.length];
		int i = 0;
		for (byte element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static byte[] unbox(@Nonnull Byte[] array) {
		byte[] copy = new byte[array.length];
		int i = 0;
		for (Byte element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static byte[] deepCopy(@Nonnull byte[] array) {
		byte[] copy = new byte[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	@Nonnull
	public static byte[] reverse(@Nonnull byte[] array) {
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

	@Nonnull
	public static byte[] concat(@Nonnull byte[] firstArray, @Nonnull byte[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
