package org.heyframework.common.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompression {

	/**
	 * 压缩文件
	 * 
	 * @param responseOutStream
	 * @param inputFile
	 * @throws IOException
	 */
	public void compression(OutputStream responseOutStream, File... inputFile) throws IOException {
		if (inputFile != null) {
			ZipOutputStream out = new ZipOutputStream(responseOutStream);
			for (File file : inputFile) {
				compression(out, file, file.getName());
			}
			out.close();
		}
	}

	private void compression(ZipOutputStream out, File f, String base) throws IOException {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/"));
			}
			for (int i = 0; i < fl.length; i++) {
				compression(out, fl[i], base + "/" + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				out.write(b);
			}
			bi.close();
			in.close();
		}
	}
}
