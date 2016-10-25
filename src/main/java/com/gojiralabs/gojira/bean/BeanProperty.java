package com.gojiralabs.gojira.bean;

import static com.gojiralabs.gojira.bean.BeanAnalyzer.lookupField;
import static com.gojiralabs.gojira.bean.BeanAnalyzer.lookupGetter;
import static com.gojiralabs.gojira.bean.BeanAnalyzer.lookupSetter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.gojiralabs.gojira.common.Lazy;

public class BeanProperty {
	private final String name;
	private final Class<?> beanClass;
	private final Lazy<Method> getter;
	private final Lazy<Method> setter;
	private final Lazy<Field> field;

	public BeanProperty(final String name, Class<?> beanClass) {
		this.name = name;
		this.beanClass = beanClass;
		getter = Lazy.from(() -> lookupGetter(beanClass, name));
		setter = Lazy.from(() -> lookupSetter(beanClass, name));
		field = Lazy.from(() -> lookupField(beanClass, name));
	}

	@SuppressWarnings("unchecked")
	<T> T get(Object bean) {
		if (!canRead()) {
			throw new BeanException("Cannot read property %s from %s", name, beanClass.getName());
		}
		try {
			return (T) getGetter().invoke(bean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new BeanException(e);
		}
	}

	void set(Object bean, Object value) {
		if (!canWrite()) {
			throw new BeanException("Cannot write property %s from %s", name, beanClass.getName());
		}
		try {
			getSetter().invoke(bean, value);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new BeanException(e);
		}
	}

	public boolean canRead() {
		return getGetter() != null;
	}

	public boolean canWrite() {
		return getSetter() != null;
	}

	public String getName() {
		return name;
	}

	public Method getGetter() {
		return getter.get();
	}

	public Method getSetter() {
		return setter.get();
	}

	public Field getField() {
		return field.get();
	}
}
