package com.gojiralabs.gojira.reflection;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Classpath {

	private final ClassLoader classLoader;

	private Classpath(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public static final Classpath with(ClassLoader classLoader) {
		return new Classpath(classLoader);
	}

	public void addResource(String resourceFilePath) {
		addResource(new File(resourceFilePath));
	}

	public void addResource(File resourceFile) {
		try {
			addResource(resourceFile.toURI().toURL());
		} catch (MalformedURLException e) {
			throw new ReflectionException(e);
		}
	}

	public void addResource(URL resourceURL) {
		Method addURLMethod = Reflect.UNCHECKED.getDeclaredMethod(URLClassLoader.class, "addURL", URL.class);
		Reflect.UNCHECKED.invoke(addURLMethod, classLoader, resourceURL);
	}

	public Class<?> forName(String className, String resourceFilePath) {
		return forName(className, new File(resourceFilePath));
	}

	public Class<?> forName(String className, File resourceFile) {
		try {
			return forName(className, resourceFile.toURI().toURL());
		} catch (MalformedURLException e) {
			throw new ReflectionException(e);
		}
	}

	public Class<?> forName(String className, URL resourceURL) {
		Class<?> namedClass = Reflect.SILENT.forName(className);
		if (namedClass != null) {
			return namedClass;
		}
		addResource(resourceURL);
		return Reflect.UNCHECKED.forName(className);
	}
}
