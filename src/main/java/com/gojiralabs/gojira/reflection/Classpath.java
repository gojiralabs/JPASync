package com.gojiralabs.gojira.reflection;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.annotation.Nonnull;

public class Classpath {
	@Nonnull
	private final ClassLoader classLoader;

	private Classpath(@Nonnull ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public static final Classpath with(@Nonnull ClassLoader classLoader) {
		return new Classpath(classLoader);
	}

	public void addResource(@Nonnull String resourceFilePath) {
		addResource(new File(resourceFilePath));
	}

	@SuppressWarnings("null")
	public void addResource(@Nonnull File resourceFile) {
		try {
			addResource(resourceFile.toURI().toURL());
		} catch (MalformedURLException e) {
			throw new ReflectionException(e);
		}
	}

	@SuppressWarnings("null")
	public void addResource(@Nonnull URL resourceURL) {
		Method addURLMethod = Reflect.UNCHECKED.getDeclaredMethod(URLClassLoader.class, "addURL", URL.class);
		Reflect.UNCHECKED.invoke(addURLMethod, classLoader, resourceURL);
	}

	public Class<?> forName(@Nonnull String className, @Nonnull String resourceFilePath) {
		return forName(className, new File(resourceFilePath));
	}

	@SuppressWarnings("null")
	public Class<?> forName(@Nonnull String className, @Nonnull File resourceFile) {
		try {
			return forName(className, resourceFile.toURI().toURL());
		} catch (MalformedURLException e) {
			throw new ReflectionException(e);
		}
	}

	public Class<?> forName(@Nonnull String className, @Nonnull URL resourceURL) {
		Class<?> namedClass = Reflect.SILENT.forName(className);
		if (namedClass != null) {
			return namedClass;
		}
		addResource(resourceURL);
		return Reflect.UNCHECKED.forName(className);
	}
}
