package org.heyframework.common.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class RuntimeTest {
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("java");
			InputStream errorIs = process.getErrorStream();
			System.out.println(InputStreamTOString(errorIs));
			System.out.println("======================");
			InputStream is = process.getInputStream();
			System.out.println(InputStreamTOString(is));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String InputStreamTOString(InputStream in) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int count = -1;
		while ((count = in.read(data, 0, 1024)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), "ISO-8859-1");
	}

}
