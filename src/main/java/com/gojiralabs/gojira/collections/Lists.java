package com.gojiralabs.gojira.collections;

import static com.gojiralabs.gojira.common.Checker.checkArgument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

public class Lists {
	private Lists() {
		// private constructor to avoid instantiation
	}

	@SuppressWarnings("null")
	@Nonnull
	public static <T> List<T> nullToEmpty(List<T> list) {
		return list == null ? Collections.emptyList() : list;
	}

	@Nonnull
	public static <T> List<T> nullToEmptyArrayList(List<T> list) {
		checkArgument(list == null || list instanceof ArrayList, "Argument is not an ArrayList");
		return list == null ? new ArrayList<>() : list;
	}

	@Nonnull
	public static <T> List<T> nullToEmptyLinkedList(List<T> list) {
		checkArgument(list == null || list instanceof LinkedList, "Argument is not an LinkedList");
		return list == null ? new LinkedList<>() : list;
	}
}
