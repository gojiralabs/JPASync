package com.gojiralabs.gojira.bean;

import javax.annotation.Nonnull;

import com.gojiralabs.gojira.cache.Cache;
import com.gojiralabs.gojira.cache.HashCache;

public class Beans {
	public static final Beans UNCHECKED = new Beans(false);
	public static final Beans SILENT = new Beans(true);

	private final Cache<Class<?>, Bean> cache;

	@SuppressWarnings("null")
	private Beans(boolean silenceExceptions) {
		cache = HashCache.from(k -> new Bean(k, silenceExceptions));
	}

	public <T> T get(@Nonnull Object bean, @Nonnull String property) {
		return cache.get(bean.getClass()).get(bean, property);
	}

	public void set(@Nonnull Object bean, @Nonnull String property,  Object value) {
		cache.get(bean.getClass()).set(bean, property, value);
	}
}
