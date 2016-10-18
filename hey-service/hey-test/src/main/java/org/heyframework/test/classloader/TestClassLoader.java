package org.heyframework.test.classloader;

import java.net.URL;

public class TestClassLoader {

	@SuppressWarnings("restriction")
	public static void main(String[] args) {
		// 1、Bootstrap ClasLoader 所加载的jar
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toExternalForm());
		}

		System.out.println(System.getProperty("sun.boot.class.path"));

		// 2、

	}

}
