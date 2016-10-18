package com.gojiralabs.gojira.array;

import static com.gojiralabs.gojira.common.Checker.checkArrayIndex;

import java.lang.reflect.Array;
import java.util.Objects;

import javax.annotation.Nonnull;

public class ObjectArrays {
	private ObjectArrays() {
		// private constructor to avoid instantiation
	}

	public static <T> int indexOf(@Nonnull T[] array, Object element) {
		int index = 0;
		for (Object current : array) {
			if (Objects.equals(current, element)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static <T> boolean contains(@Nonnull T[] array, Object element) {
		return indexOf(array, element) != -1;
	}

	@Nonnull
	public static <T> T[] add(@Nonnull T[] array, T element) {
		return add(array, element, array.length);
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Nonnull
	public static <T> T[] add(@Nonnull T[] array, T element, int index) {
		return add(array, (T[]) create(array.getClass().getComponentType(), element), index);
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Nonnull
	public static <T> T[] add(@Nonnull T[] array, @Nonnull T[] toAdd, int index) {
		checkArrayIndex(array, index - (index > 0 ? 1 : 0));
		T[] copy = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + toAdd.length);
		System.arraycopy(array, 0, copy, 0, index);
		System.arraycopy(toAdd, 0, copy, index, toAdd.length);
		System.arraycopy(array, index, copy, index + toAdd.length, array.length - index);
		return copy;
	}

	@Nonnull
	public static <T> T[] remove(@Nonnull T[] array, int index) {
		return remove(array, index, index);
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Nonnull
	public static <T> T[] remove(@Nonnull T[] array, int fromIndex, int toIndex) {
		checkArrayIndex(array, fromIndex);
		checkArrayIndex(array, toIndex);
		T[] copy = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - toIndex + fromIndex - 1);
		System.arraycopy(array, 0, copy, 0, fromIndex);
		System.arraycopy(array, toIndex + 1, copy, fromIndex, array.length - toIndex - 1);
		return copy;
	}

	@SuppressWarnings("unchecked")
	@Nonnull
	public static <T> T[] deepCopy(@Nonnull T[] array) {
		T[] copy = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
		System.arraycopy(array, 0, copy, 0, copy.length);
		return copy;
	}

	@Nonnull
	public static <T> T[] reverse(@Nonnull T[] array) {
		T[] copy = array.clone();
		int left = 0;
		int right = copy.length - 1;
		while (left < right) {
			T holder = copy[left];
			copy[left++] = copy[right];
			copy[right--] = holder;
		}
		return copy;
	}

	@Nonnull
	public static <T> T[] concat(@Nonnull T[] firstArray, @Nonnull T[] secondArray) {
		return add(firstArray, secondArray, firstArray.length);
	}

	@SuppressWarnings("unchecked")
	@Nonnull
	private static <T> T[] create(@Nonnull Class<T> type, @Nonnull Object... elements) {
		T[] array = (T[]) Array.newInstance(type, elements.length);
		System.arraycopy(elements, 0, array, 0, array.length);
		return array;
	}
}
