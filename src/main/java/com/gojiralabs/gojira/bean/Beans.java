package com.gojiralabs.gojira.bean;

import com.gojiralabs.gojira.cache.Cache;
import com.gojiralabs.gojira.cache.HashCache;

public class Beans {
	private Beans() {
		// private constructor to avoid instantiation
	}

	private static final Cache<Class<?>, Bean> cache = HashCache.from(k -> new Bean(k));

	public static <T> T get(Object bean, String property) {
		return getBean(bean.getClass()).get(bean, property);
	}

	public static void set(Object bean, String property, Object value) {
		getBean(bean.getClass()).set(bean, property, value);
	}

	public static <T> T getNested(Object bean, String nestedProperty) {
		return getBean(bean.getClass()).getNested(bean, nestedProperty, cache);
	}

	public static void setNested(Object bean, String nestedProperty, Object value) {
		getBean(bean.getClass()).setNested(bean, nestedProperty, value, cache);
	}

	public static Bean getBean(Class<?> beanClass) {
		return cache.get(beanClass);
	}
}
