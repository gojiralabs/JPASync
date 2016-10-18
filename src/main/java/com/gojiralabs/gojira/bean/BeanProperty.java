package com.gojiralabs.gojira.bean;

import static com.gojiralabs.gojira.bean.BeanAnalyzer.lookupField;
import static com.gojiralabs.gojira.bean.BeanAnalyzer.lookupGetter;
import static com.gojiralabs.gojira.bean.BeanAnalyzer.lookupSetter;
import static com.gojiralabs.gojira.bean.BeanExceptionHandler.handle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import com.gojiralabs.gojira.common.Lazy;

public class BeanProperty {
	private final String name;
	private final Class<?> beanClass;
	private final boolean silenceExceptions;
	private final Lazy<Method> getter;
	private final Lazy<Method> setter;
	private final Lazy<Field> field;

	public BeanProperty(@Nonnull final String name, @Nonnull Class<?> beanClass, boolean silenceExceptions) {
		this.name = name;
		this.silenceExceptions = silenceExceptions;
		this.beanClass = beanClass;
		getter = Lazy.from(() -> lookupGetter(beanClass, name));
		setter = Lazy.from(() -> lookupSetter(beanClass, name));
		field = Lazy.from(() -> lookupField(beanClass, name));
	}

	@SuppressWarnings("unchecked")
	<T> T get(@Nonnull Object bean) {
		if (!canRead()) {
			throw new BeanException("Cannot read property %s from %s", name, beanClass.getName());
		}
		try {
			return (T) getGetter().invoke(bean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			return handle(e, silenceExceptions);
		}
	}

	void set(@Nonnull Object bean, Object value) {
		if (!canWrite()) {
			throw new BeanException("Cannot write property %s from %s", name, beanClass.getName());
		}
		try {
			getSetter().invoke(bean, value);
		} catch (IllegalAccessException | InvocationTargetException e) {
			handle(e, silenceExceptions);
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
