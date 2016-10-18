package com.gojiralabs.gojira.cache;

import java.util.Map;

import javax.annotation.Nonnull;

public interface Cache<K, V> {
	V get(@Nonnull K key);

	@Nonnull
	Map<K, V> asMap();

	void clear();
}
