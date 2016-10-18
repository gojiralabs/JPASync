package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArgument;
import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

import javax.annotation.Nonnull;

public class DoubleArrays {
	private DoubleArrays() {
		// private constructor to avoid instantiation
	}

	public static int indexOf(double[] array, double element) {
		int index = 0;
		for (double current : array) {
			if (Double.compare(current, element) == 0) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static boolean contains(double[] array, double element) {
		return indexOf(array, element) != -1;
	}

	@Nonnull
	public static double[] add(@Nonnull double[] array, double element) {
		return add(array, element, array.length);
	}

	@Nonnull
	public static double[] add(@Nonnull double[] array, double element, int index) {
		return add(array, new double[] { element }, index);
	}

	@Nonnull
	public static double[] add(@Nonnull double[] array, double[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		double[] copy = new double[array.length + toAdd.length];
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	@Nonnull
	public static double[] remove(@Nonnull double[] array, int index) {
		return remove(array, index, index);
	}

	@Nonnull
	public static double[] remove(@Nonnull double[] array, int fromIndex, int toIndex) {
		checkArgument(fromIndex <= toIndex, "fromIndex cannot be greater than toIndex");
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		double[] copy = new double[array.length - toIndex + fromIndex - 1];
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	@Nonnull
	public static Double[] box(@Nonnull double[] array) {
		Double[] copy = new Double[array.length];
		int i = 0;
		for (Double element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static double[] unbox(@Nonnull Double[] array) {
		double[] copy = new double[array.length];
		int i = 0;
		for (double element : array) {
			copy[i++] = element;
		}
		return copy;
	}

	@Nonnull
	public static double[] deepCopy(@Nonnull double[] array) {
		double[] copy = new double[array.length];
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	@Nonnull
	public static double[] reverse(@Nonnull double[] array) {
		double[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			double holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}

	@Nonnull
	public static double[] concat(@Nonnull double[] firstArray, @Nonnull double[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}
}
