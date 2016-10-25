package com.gojiralabs.gojira.bean;

import static com.gojiralabs.gojira.common.Checker.notNull;

import com.gojiralabs.gojira.cache.Cache;
import com.gojiralabs.gojira.cache.HashCache;

public class Bean {

	private Class<?> beanClass;
	private final HashCache<String, BeanProperty> propertyCache;

	public Bean(Class<?> beanClass) {
		this.beanClass = beanClass;
		propertyCache = HashCache.from(n -> new BeanProperty(n, beanClass));
	}

	<T> T get(Object beanInstance, String property) {
		return getProperty(property).get(beanInstance);
	}

	void set(Object beanInstance, String property, Object value) {
		getProperty(property).set(beanInstance, value);
	}

	<T> T getNested(Object beanInstance, String nestedProperty, Cache<Class<?>, Bean> beanProvider) {
		BeanAcessor accessor = getNestedProperty(beanInstance, nestedProperty, beanProvider);
		return notNull(accessor).property.get(accessor.beanInstance);
	}

	void setNested(Object beanInstance, String nestedProperty, Object value, Cache<Class<?>, Bean> beanProvider) {
		BeanAcessor accessor = getNestedProperty(beanInstance, nestedProperty, beanProvider);
		if (accessor != null) {
			accessor.property.set(accessor.beanInstance, value);
		}
	}

	private BeanAcessor getNestedProperty(Object beanInstance, String nestedProperty, Cache<Class<?>, Bean> beanProvider) {
		String[] nestedAccessors = nestedProperty.split("\\.");
		Object currentValue = notNull(beanInstance);
		Bean currentBean = beanProvider.get(beanInstance.getClass());
		for (int i = 0; i < nestedAccessors.length - 1; i++) {
			notNull(currentValue);
			currentValue = currentBean.get(currentValue, nestedAccessors[i]);
			currentBean = beanProvider.get(currentValue.getClass());
		}
		return new BeanAcessor(currentBean.getProperty(nestedAccessors[nestedAccessors.length - 1]), currentValue);
	}

	public BeanProperty getProperty(String property) {
		return propertyCache.get(property);
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	private class BeanAcessor {

		private BeanProperty property;

		private Object beanInstance;

		public BeanAcessor(BeanProperty property, Object beanInstance) {
			this.property = property;
			this.beanInstance = beanInstance;
		}
	}
}
