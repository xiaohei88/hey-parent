package org.heyframework.test.lambda;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Henry on 12/15/16.
 */
public class Main {

	@Test
	public void test() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Before Java8, too much code for too little to do");
			}
		}).start();

		new Thread(() -> {
			System.out.println("In Java8, Lambda expression rocks !!");
		}).start();
	}

	@Test
	public void test1() {
		JButton show = new JButton("Show");
		show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Event handling without lambda expression is boring");
			}
		});

		show.addActionListener((e) -> {
			System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
		});
	}

	@Test
	public void test2() {
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		for (String feature : features) {
			System.out.println(feature);
		}
		// features.forEach((feature) -> System.out.println(feature));
		System.out.println("======1");
		features.forEach(System.out::println);
		System.out.println("======1");
	}

	@Test
	public void test3() {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		System.out.println("Languages which starts with J :");
		filter(languages, str -> str.startsWith("J"));
		System.out.println("Languages which ends with a ");
		filter(languages, (str) -> str.endsWith("a"));
		System.out.println("Print all languages :");
		filter(languages, (str) -> true);
		System.out.println("Print no language : ");
		filter(languages, (str) -> false);
		System.out.println("Print language whose length greater than 4:");
		filter(languages, (str) -> str.length() > 4);
	}

	@Test
	public void test4() {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		handle(languages, str -> str.contains("J"));

		handle(languages, new Handle<String>() {
			@Override
			public boolean test(String s) {
				return s.contains("J");
			}
		});
	}

	@Test
	public void test5() {
		Main test = new Main();
		test.setT("=====================");
		// After Java 8
		test.handle(test::aaa);

		// Before Java 8
		test.handle(new Consumer<String>() {
			@Override
			public void accept(String s) {
				test.aaa(s);
			}
		});
	}

	private void aaa(String s) {
		System.out.println(s);
	}

	public static void filter(List<String> names, Predicate<String> condition) {
		for (String name : names) {
			if (condition.test(name)) {
				System.out.println(name + " ");
			}
		}
	}

	public static void handle(List<String> names, Handle<String> hand) {
		for (String name : names) {
			if (hand.test(name)) {
				System.out.println(name + " ");
			}
		}
	}

	private String t;

	public void handle(Consumer<String> str) {
		str.accept(t);
	}

	public void setT(String t) {
		this.t = t;
	}
}
