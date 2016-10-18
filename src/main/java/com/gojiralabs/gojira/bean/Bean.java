package com.gojiralabs.gojira.bean;

import javax.annotation.Nonnull;

import com.gojiralabs.gojira.cache.HashCache;

public class Bean {
	@Nonnull
	private Class<?> beanClass;
	private final HashCache<String, BeanProperty> properties;

	@SuppressWarnings("null")
	public Bean(@Nonnull Class<?> beanClass, boolean silenceExceptions) {
		this.beanClass = beanClass;
		properties = HashCache.from(n -> new BeanProperty(n, beanClass, silenceExceptions));
	}

	<T> T get(@Nonnull Object bean, @Nonnull String property) {
		return properties.get(property).get(bean);
	}

	void set(@Nonnull Object bean, @Nonnull String property, Object value) {
		properties.get(property).set(bean, value);
	}

	@Nonnull
	public Class<?> getBeanClass() {
		return beanClass;
	}
}
