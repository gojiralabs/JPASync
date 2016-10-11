package com.gojiralabs.gojira.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Reflect {
	public static final Reflect SILENT = new Reflect(true);
	public static final Reflect UNCHECKED = new Reflect(false);

	private boolean silenceExceptions;

	private Reflect(boolean silenceExceptions) {
		this.silenceExceptions = silenceExceptions;
	}

	@SuppressWarnings("unchecked")
	public <T> Class<T> forName(String className) {
		try {
			return (Class<T>) Class.forName(className);
		} catch (final ClassNotFoundException e) {
			return handleException(e);
		}
	}

	public <T> T newInstance(Class<T> type) {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return handleException(e);
		}
	}

	public <T> Constructor<T> getConstructor(Class<T> type, Class<?>... parameterTypes) {
		try {
			return type.getConstructor(parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return handleException(e);
		}
	}

	public <T> Constructor<T> getDeclaredConstructor(Class<T> type, Class<?>... parameterTypes) {
		try {
			return type.getDeclaredConstructor(parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return handleException(e);
		}
	}

	public Method getMethod(Class<?> type, String methodName, Class<?>... parameterTypes) {
		try {
			return type.getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return handleException(e);
		}
	}

	public Set<Method> getMethodsNamed(Class<?> type, String methodName) {
		Set<Method> methods = new HashSet<>();
		for (Method method : type.getMethods()) {
			if (method.getName().equals(methodName)) {
				methods.add(method);
			}
		}
		return methods;
	}

	public Method getDeclaredMethod(Class<?> type, String methodName, Class<?>... parameterTypes) {
		try {
			return type.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return handleException(e);
		}
	}

	public Set<Method> getDeclaredMethodsNamed(Class<?> type, String methodName) {
		Set<Method> methods = new HashSet<>();
		for (Method method : type.getDeclaredMethods()) {
			if (method.getName().equals(methodName)) {
				methods.add(method);
			}
		}
		return methods;
	}

	public Field getField(Class<?> type, String fieldName) {
		try {
			return type.getField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			return handleException(e);
		}
	}

	public Field getDeclaredField(Class<?> type, String fieldName) {
		try {
			return type.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			return handleException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T invoke(Method method, Object object, Object... arguments) {
		try {
			return (T) method.invoke(object, arguments);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return handleException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Field field, Object object) {
		field.setAccessible(true);
		try {
			return (T) field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return handleException(e);
		}
	}

	public void set(Field field, Object object, Object value) {
		field.setAccessible(true);
		try {
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			handleException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> Set<Constructor<T>> getConstructorsAnnotatedWith(Class<T> type, Class<? extends Annotation> annotationClass) {
		Constructor<?>[] constructors = type.getDeclaredConstructors();
		return filterAccessibles(Arrays.copyOf(constructors, constructors.length, Constructor[].class), annotationClass);
	}

	public Set<Method> getMethodsAnnotatedWith(Class<?> type, Class<? extends Annotation> annotationClass) {
		return filterAccessibles(type.getDeclaredMethods(), annotationClass);
	}

	public Set<Field> getFieldsAnnotatedWith(Class<?> type, Class<? extends Annotation> annotationClass) {
		return filterAccessibles(type.getDeclaredFields(), annotationClass);
	}

	private <T extends AccessibleObject> Set<T> filterAccessibles(T[] accessibles, Class<? extends Annotation> annotationClass) {
		Set<T> filtered = new HashSet<>();
		for (T accessible : accessibles) {
			if (accessible.isAnnotationPresent(annotationClass)) {
				filtered.add(accessible);
			}
		}
		return filtered;
	}

	private <T> T handleException(Exception e) {
		if (silenceExceptions) {
			return null;
		}
		throw new ReflectionException(e);
	}
}
