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

import javax.annotation.Nonnull;

public class Reflect {
	public static final Reflect SILENT = new Reflect(true);
	public static final Reflect UNCHECKED = new Reflect(false);

	private boolean silenceExceptions;

	private Reflect(boolean silenceExceptions) {
		this.silenceExceptions = silenceExceptions;
	}

	@SuppressWarnings("unchecked")
	public <T> Class<T> forName(@Nonnull String className) {
		try {
			return (Class<T>) Class.forName(className);
		} catch (final ClassNotFoundException e) {
			return handleException(e);
		}
	}

	public <T> T newInstance(@Nonnull Class<T> type) {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return handleException(e);
		}
	}

	public <T> Constructor<T> getConstructor(@Nonnull Class<T> type, @Nonnull Class<?>... parameterTypes) {
		try {
			return type.getConstructor(parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return handleException(e);
		}
	}

	public <T> Constructor<T> getDeclaredConstructor(@Nonnull Class<T> type, @Nonnull Class<?>... parameterTypes) {
		try {
			return type.getDeclaredConstructor(parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return handleException(e);
		}
	}

	public Method getMethod(@Nonnull Class<?> type, @Nonnull String methodName, @Nonnull Class<?>... parameterTypes) {
		try {
			return type.getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return handleException(e);
		}
	}

	public Set<Method> getMethodsNamed(@Nonnull Class<?> type, @Nonnull String methodName) {
		Set<Method> methods = new HashSet<>();
		for (Method method : type.getMethods()) {
			if (method.getName().equals(methodName)) {
				methods.add(method);
			}
		}
		return methods;
	}

	public Method getDeclaredMethod(@Nonnull Class<?> type, @Nonnull String methodName, @Nonnull Class<?>... parameterTypes) {
		try {
			return type.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return handleException(e);
		}
	}

	public Set<Method> getDeclaredMethodsNamed(@Nonnull Class<?> type, @Nonnull String methodName) {
		Set<Method> methods = new HashSet<>();
		for (Method method : type.getDeclaredMethods()) {
			if (method.getName().equals(methodName)) {
				methods.add(method);
			}
		}
		return methods;
	}

	public Field getField(@Nonnull Class<?> type, @Nonnull String fieldName) {
		try {
			return type.getField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			return handleException(e);
		}
	}

	public Field getDeclaredField(@Nonnull Class<?> type, @Nonnull String fieldName) {
		try {
			return type.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			return handleException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T invoke(@Nonnull Method method, @Nonnull Object object, @Nonnull Object... arguments) {
		try {
			return (T) method.invoke(object, arguments);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return handleException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(@Nonnull Field field, @Nonnull Object object) {
		field.setAccessible(true);
		try {
			return (T) field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return handleException(e);
		}
	}

	public void set(@Nonnull Field field, @Nonnull Object object, @Nonnull Object value) {
		field.setAccessible(true);
		try {
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			handleException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Nonnull
	public <T> Set<Constructor<T>> getConstructorsAnnotatedWith(Class<T> type, Class<? extends Annotation> annotationClass) {
		Constructor<?>[] constructors = type.getDeclaredConstructors();
		return filterAccessibles(Arrays.copyOf(constructors, constructors.length, Constructor[].class), annotationClass);
	}

	@SuppressWarnings("null")
	@Nonnull
	public Set<Method> getMethodsAnnotatedWith(@Nonnull Class<?> type, @Nonnull Class<? extends Annotation> annotationClass) {
		return filterAccessibles(type.getDeclaredMethods(), annotationClass);
	}

	@SuppressWarnings("null")
	@Nonnull
	public Set<Field> getFieldsAnnotatedWith(@Nonnull Class<?> type, @Nonnull Class<? extends Annotation> annotationClass) {
		return filterAccessibles(type.getDeclaredFields(), annotationClass);
	}

	@Nonnull
	private <T extends AccessibleObject> Set<T> filterAccessibles(@Nonnull T[] accessibles,
			@Nonnull Class<? extends Annotation> annotationClass) {
		Set<T> filtered = new HashSet<>();
		for (T accessible : accessibles) {
			if (accessible.isAnnotationPresent(annotationClass)) {
				filtered.add(accessible);
			}
		}
		return filtered;
	}

	private <T> T handleException(@Nonnull Exception e) {
		if (silenceExceptions) {
			return null;
		}
		throw new ReflectionException(e);
	}
}
