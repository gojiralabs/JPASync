package com.gojiralabs.gojira.cache;

import java.util.Map;

public interface Cache<K, V> {

	V get(K key);

	Map<K, V> asMap();

	void clear();
}
