package com.gojiralabs.gojira.collections;

import static com.gojiralabs.gojira.common.Checker.checkArgument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lists {
	private Lists() {
		// private constructor to avoid instantiation
	}

	public static <T> List<T> nullToEmpty(List<T> list) {
		return list == null ? Collections.emptyList() : list;
	}

	public static <T> List<T> nullToEmptyArrayList(List<T> list) {
		checkArgument(list == null || list instanceof ArrayList, "Argument is not an ArrayList");
		return list == null ? new ArrayList<>() : list;
	}

	public static <T> List<T> nullToEmptyLinkedList(List<T> list) {
		checkArgument(list == null || list instanceof LinkedList, "Argument is not an LinkedList");
		return list == null ? new LinkedList<>() : list;
	}
}
