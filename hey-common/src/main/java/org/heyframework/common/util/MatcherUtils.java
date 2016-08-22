package org.heyframework.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MatcherUtils {

	/**
	 * 正则表达式字符串
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean matcher(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
