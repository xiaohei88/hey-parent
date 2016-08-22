package org.heyframework.common.test;

import java.util.Arrays;

public class RefTest {

	String str = new String("000");

	Integer[] in = { 1, 2, 3 };

	public static void main(String[] args) {
		RefTest t = new RefTest();
		change(t.str, t.in);
		System.out.println(t.str + "and");
		System.out.println(Arrays.toString(t.in));
	}

	static void change(String str, Integer[] in) {
		str = "111";
		in[0] = 4;
	}

}
