package org.heyframework.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Author : Mr.He Date : 2013-9-27 Verion : 3.0
 */
public abstract class StringUtils {

	/**
	 * 包含字符数量
	 * 
	 * @param str 字符串
	 * @param c 字符
	 * @return 数量
	 */
	public static int containCharNums(String str, String sign) {
		StringTokenizer st = new StringTokenizer(str, sign);
		return st.countTokens() - 1;
	}

	/**
	 * 是否包含某个字符串
	 * 
	 * @param orgStr 原字符串
	 * @param targetStr 目标字符串
	 * @param delim 分隔符
	 * @return
	 */
	public static boolean contains(String orgStr, String targetStr, String delim) {
		if (orgStr.equals(targetStr))
			return true;

		if (!isEmpty(orgStr)) {
			String[] orgStrs = split(orgStr, delim);
			for (String string : orgStrs) {
				if (string.equals(targetStr)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 占位符格式化
	 * 
	 * @param format
	 * @param args
	 * @return
	 */
	public static String format(String format, Object... args) {
		return String.format(format.replace("{}", "%s"), args);
	}

	/**
	 * 某个符号后最后一个字符
	 * 
	 * @param str 字符串
	 * @param sign 字符
	 * @return
	 */
	public static String lastChar(String str, String sign) {
		if (!str.contains(sign))
			return str;
		return str.substring(str.lastIndexOf(sign) + 1);
	}

	/**
	 * 最后一个符号的前面所有字符串
	 * 
	 * @param str
	 * @param sign
	 * @return
	 */
	public static String preStringOfLastChar(String str, String sign) {
		if (!str.contains(sign))
			return str;
		return str.substring(0, str.lastIndexOf(sign));
	}

	/**
	 * 字符串截取
	 * 
	 * @param str
	 * @param delim
	 * @return
	 */
	public static String[] split(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		List<String> strs = new ArrayList<String>();
		while (st.hasMoreElements()) {
			strs.add((String) st.nextElement());
		}
		return strs.toArray(new String[st.countTokens()]);
	}

	/**
	 * 将null转换成空
	 * 
	 * @param str
	 * @return
	 */
	public String toEmpty(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return str;
	}

	/**
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public String toString(InputStream in) throws IOException {
		return toString(in, "UTF-8");
	}

	/**
	 * 
	 * @param in
	 * @param charsetName
	 * @return
	 * @throws IOException
	 */
	public String toString(InputStream in, String charsetName) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[2048];
		int count = -1;
		while ((count = in.read(data, 0, 2048)) != -1) {
			outStream.write(data, 0, count);
		}
		data = null;
		return new String(outStream.toByteArray(), charsetName);
	}

	/**
	 * 验证字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null && !"null".equals(str) && !"".equals(str) && !"undefined".equals(str))
			return false;
		return true;
	}
}
