package com.gojiralabs.gojira.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import com.gojiralabs.gojira.common.Strings;
import com.gojiralabs.gojira.reflection.Reflect;

class BeanAnalyzer {
	public static final String PREFIX_GET = "get";
	public static final String PREFIX_IS = "is";
	public static final String PREFIX_SET = "set";

	private BeanAnalyzer() {
		// private constructor to avoid instantiation
	}

	public static Method lookupGetter(Class<?> beanClass, String propertyName) {
		for (String prefix : new String[] { PREFIX_GET, PREFIX_IS }) {
			String getterName = prefix + Strings.capitalize(propertyName);
			Method getter = Reflect.SILENT.getDeclaredMethod(beanClass, getterName);
			if (getter != null) {
				return getter;
			}
		}
		return null;
	}

	public static Method lookupSetter(Class<?> beanClass, String propertyName) {
		Set<Method> setters = Reflect.SILENT.getDeclaredMethodsNamed(beanClass, PREFIX_SET + Strings.capitalize(propertyName));
		Method setter = null;
		for (Method method : setters) {
			if (method.getParameterCount() == 1) {
				if (setter == null) {
					setter = method;
				} else {
					throw new BeanException("There's more than one writer method for %s on %s", propertyName, beanClass);
				}
			}
		}
		return setters.size() == 0 ? null : setters.iterator().next();
	}

	public static Field lookupField(Class<?> beanClass, String propertyName) {
		return Reflect.SILENT.getDeclaredField(beanClass, propertyName);
	}
}
