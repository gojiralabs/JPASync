package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

import javax.annotation.Nonnull;

public class FloatArrays {
	private FloatArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(@Nonnull float[] array, float element) {
		int index = 0;
		for (float current : array) {
			if (Float.compare(current, element) == 0) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(@Nonnull float[] array, float element) {
		return indexOf(array, element) != -1;
	}

	@Nonnull
	public static float[] add(@Nonnull float[] array, float element) {
		return add(array, element, array.length);
	}

	@Nonnull
	public static float[] add(@Nonnull float[] array, float element, int index) {
		return add(array, new float[] { element }, index);
	}

	@Nonnull
	public static float[] add(@Nonnull float[] array, @Nonnull float[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		float[] copy = new float[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	@Nonnull
	public static float[] remove(@Nonnull float[] array, int index) {
		return remove(array, index, index);
	}

	@Nonnull
	public static float[] remove(@Nonnull float[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		float[] copy = new float[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	@Nonnull
	public static Float[] box(@Nonnull float[] array) {
		Float[] copy = new Float[array.length];
		int i = 0;
		for (Float element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static float[] unbox(@Nonnull Float[] array) {
		float[] copy = new float[array.length];
		int i = 0;
		for (float element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static float[] deepCopy(@Nonnull float[] array) {
		float[] copy = new float[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	@Nonnull
	public static float[] reverse(@Nonnull float[] array) {
		float[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			float holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}

	@Nonnull
	public static float[] concat(@Nonnull float[] firstArray, @Nonnull float[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
