package com.gojiralabs.gojira.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamCommons {
	private StreamCommons() {
		// private constructor to avoid instantiation
	}

	public static <T, R> List<R> mapAsArrayList(Collection<T> collection, Function<T, R> function) {
		return collection.stream().map(function).collect(Collectors.toCollection(ArrayList::new));
	}
}
