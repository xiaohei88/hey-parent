package org.heyframework.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author h.l
 */
public abstract class FileUtils {

	public static final String CLASSPATH_URL_PREFIX = "classpath";
	public static final String WEB_INFO_URL_PREFIX = "/WEB-INF";

	public final static String FILE_FULL_NAME = "FILE_FULL_NAME";
	public final static String FILE_CONTENT = "FILE_CONTENT";

	public static final String UFT8 = "UTF-8";
	private static final String GB2312 = "gb2312";
	private static final String ISO88591 = "ISO8859-1";

	/**
	 * 创建文件
	 * 
	 * @param filePath
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String filePath, String filename) throws IOException {
		// 创建文件夹
		File d = new File(filePath);
		if (!d.exists()) {
			d.mkdirs();
		}
		// 创建文件
		File f = new File(filePath, filename);
		if (!f.exists()) {
			f.createNewFile();
		}
		return f;
	}

	/**
	 * 创建文件
	 * 
	 * @param fileFullPath
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String fileFullPath) throws IOException {
		return FileUtils.createFile(FileUtils.getParentFile(fileFullPath), FileUtils.getFileName(fileFullPath));
	}

	/**
	 * 创建文件
	 * 
	 * @param fileFullPath
	 * @return
	 * @throws IOException
	 */
	public static void createFile(File file) throws IOException {
		FileUtils.createFile(file.getParentFile().toString(), file.getName());
	}

	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param fileContent
	 */
	public static void download(HttpServletResponse response, String fileName, byte[] fileContent) {
		OutputStream responseOutStream = null;
		BufferedOutputStream bos = null;
		try {
			response.setCharacterEncoding(UFT8);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;Filename="
					+ new String(fileName.getBytes(GB2312), ISO88591));
			responseOutStream = response.getOutputStream();
			bos = new BufferedOutputStream(responseOutStream);
			bos.write(fileContent);
			bos.flush();
		} catch (IOException e) {
		} finally {
			try {
				if (responseOutStream != null) {
					responseOutStream.flush();
					responseOutStream.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean exists(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 流转字节
	 * 
	 * @param is
	 * @return
	 */
	public static byte[] getBytes(InputStream is) {
		InputStream fis = null;
		try {
			fis = new BufferedInputStream(is);
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			return bytes;
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在.");
		} catch (IOException e) {
			System.out.println("以二进制的方式读取文件错误.");
		} finally {

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * 获取类路径
	 * 
	 * @param path
	 * @param clazz
	 * @return
	 */
	public static String getClassPath(String path, Class<?> clazz) {
		String file = clazz.getResource(path).getFile();
		String filePath = file.substring(6);
		return filePath;
	}

	/**
	 * 获取文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		File file = new File(filePath);
		return file.getName();
	}

	/**
	 * 获取文件类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName) {
		if (!StringUtils.isEmpty(fileName)) {
			int suffix = fileName.lastIndexOf(".");
			return fileName.substring(suffix);
		}
		return "";
	}

	/**
	 * 获取文件目录
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getParentFile(String filePath) {
		File file = new File(filePath);
		return file.getParentFile().toString();
	}

	/**
	 * 获取目录下所有的文件
	 * 
	 * @param directory
	 * @return
	 */
	public static List<File> listFilesOfDirectory(String directory) {
		List<File> files = new ArrayList<File>();
		File root = new File(directory);
		if (!root.isDirectory()) {
			files.add(root);
		} else {
			File[] subFiles = root.listFiles();
			for (File f : subFiles) {
				files.addAll(listFilesOfDirectory(f.getAbsolutePath()));
			}
		}
		return files;
	}

	/**
	 * 根据apache common fileupload查询上传文件的记录
	 * 
	 * @param request
	 * @return
	 * @throws FileUploadException
	 */
	public static List<FileItem> listUploadFileForFileItem(HttpServletRequest request) throws FileUploadException {
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding(UFT8);
		List<FileItem> fileList = upload.parseRequest(request);
		List<FileItem> returnFileItemList = new ArrayList<FileItem>(fileList.size());
		if (!ListUtils.isEmpty(fileList)) {
			if (!ListUtils.isEmpty(fileList)) {
				for (FileItem fileItem : fileList) {
					if (!fileItem.isFormField() && !StringUtils.isEmpty(fileItem.getName())) {
						returnFileItemList.add(fileItem);
					}
				}
			}
		}
		return returnFileItemList;
	}

	/**
	 * 用户利用multipart请求将本地文件上传到服务器
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, Object>> listUploadFile(HttpServletRequest request) throws IOException {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		MultipartHttpServletRequest mul = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mul.getFileMap();
		String nameString = "";
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			nameString = entity.getKey();
			List<MultipartFile> files = mul.getFiles(nameString);
			for (int i = 0; i < files.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				CommonsMultipartFile mf = (CommonsMultipartFile) files.get(i);
				InputStream is = files.get(i).getInputStream();
				byte[] fileByte = new byte[(int) files.get(i).getSize()];
				is.read(fileByte);
				int index = mf.getOriginalFilename().lastIndexOf(".");
				if (index < 0) {
					break;
				}
				map.put(FILE_FULL_NAME, mf.getOriginalFilename());
				map.put(FILE_CONTENT, fileByte);
				listMap.add(map);
			}
		}
		return listMap;
	}

	/**
	 * 以二进制的方式读取文件
	 * 
	 * @param filePath 文件路径
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] readFileToByte(String filePath) {
		FileInputStream file = null;
		DataInputStream data = null;
		try {
			file = new FileInputStream(filePath);
			data = new DataInputStream(file);
			byte[] bytes = new byte[data.available()];
			data.read(bytes);
			return bytes;
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在.");
		} catch (IOException e) {
			System.out.println("以二进制的方式读取文件错误.");
		} finally {

			try {
				if (data != null) {
					data.close();
				}
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * 文件上传
	 * 
	 * @param targetFile
	 * @param b
	 */
	public static boolean uploadFile(String targetPath, String targetFile, byte[] b) {
		FileOutputStream fileOut = null;
		try {
			File file = createFile(targetPath, targetFile);
			fileOut = new FileOutputStream(file);
			fileOut.write(b);
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (fileOut != null)
					fileOut.close();
			} catch (IOException e) {
			}
		}
		return true;
	}

	/**
	 * 文件写入
	 * 
	 * @param targetFile
	 * @param is
	 * @return
	 */
	public static boolean writeFile(String targetFile, InputStream is) {
		FileOutputStream fos = null;
		try {
			FileUtils.createFile(FileUtils.getParentFile(targetFile), FileUtils.getFileName(targetFile));

			fos = new FileOutputStream(targetFile);
			fos.write(getBytes(is));
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
		return true;
	}
}
