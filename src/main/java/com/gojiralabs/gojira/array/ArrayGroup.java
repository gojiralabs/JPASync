package com.gojiralabs.gojira.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ArrayGroup<T> {
	private final List<T[]> group = new ArrayList<>();

	@SafeVarargs
	public ArrayGroup(@Nonnull T[]... arrays) {
		Arrays.stream(arrays).forEach(group::add);
	}

	public T get(int index) {
		int position = index;
		for (T[] array : group) {
			if (position < array.length) {
				return array[position];
			} else {
				position -= array.length;
			}
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	public void set(int index, @Nullable T value) {
		int position = index;
		for (T[] array : group) {
			if (position < array.length) {
				array[position] = value;
				return;
			} else {
				position -= array.length;
			}
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	public int length() {
		return group.stream().mapToInt(a -> a.length).sum();
	}
}
