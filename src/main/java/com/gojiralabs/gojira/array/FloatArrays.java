package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArgument;
import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

public class FloatArrays {
	private FloatArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(float[] array, float element) {
		int index = 0;
		for (float current : array) {
			if (Float.compare(current, element) == 0) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(float[] array, float element) {
		return indexOf(array, element) != -1;
	}

	public static float[] add(float[] array, float element) {
		return add(array, element, array.length);
	}

	public static float[] add(float[] array, float element, int index) {
		return add(array, new float[] { element }, index);
	}

	public static float[] add(float[] array, float[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		float[] copy = new float[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	public static float[] remove(float[] array, int index) {
		return remove(array, index, index);
	}

	public static float[] remove(float[] array, int fromIndex, int toIndex) {
		checkArgument(fromIndex <= toIndex, "fromIndex cannot be greater than toIndex");
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		float[] copy = new float[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	public static Float[] box(float[] array) {
		Float[] copy = new Float[array.length];
		int i = 0;
		for (Float element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	public static float[] unbox(Float[] array) {
		float[] copy = new float[array.length];
		int i = 0;
		for (float element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	public static float[] deepCopy(float[] array) {
		float[] copy = new float[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	public static float[] reverse(float[] array) {
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

	public static float[] concat(float[] firstArray, float[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
