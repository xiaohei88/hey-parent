package org.heyframework.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.heyframework.common.util.AssertUtils;
import org.springframework.web.multipart.MultipartFile;

public class ExcelReader {

	private String fileName;
	private InputStream is;

	public ExcelReader(MultipartFile multipartFile) throws IOException {
		AssertUtils.isNull(multipartFile, "请选择需要导入的Excel文件.");

		this.is = multipartFile.getInputStream();
		this.fileName = multipartFile.getOriginalFilename();
	}

	public ExcelReader(File file) throws FileNotFoundException {
		AssertUtils.isNull(file, "请选择需要导入的Excel文件.");

		this.is = new FileInputStream(file);
		this.fileName = file.getName();
	}

	public List<Map<String, Object>> reader(String[] columns) throws IOException {
		return pasreExcel(columns);
	}

	private List<Map<String, Object>> pasreExcel(String[] columns) throws IOException {
		Workbook workbook = null;
		if (fileName.endsWith(".xls")) {
			workbook = new HSSFWorkbook(is);
		} else if (fileName.endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(is);
		}
		return readerExcel(workbook, columns);
	}

	private List<Map<String, Object>> readerExcel(Workbook workbook, String[] columns) {
		List<Map<String, Object>> lists = null;
		Sheet sheet = workbook.getSheetAt(0);
		if (sheet != null) {
			int totalRow = sheet.getLastRowNum();
			if (totalRow != 0) {
				lists = new ArrayList<Map<String, Object>>(totalRow - 1);
				for (int rowNum = 1; rowNum <= totalRow; rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row != null) {
						int totalCell = columns.length;
						Map<String, Object> cellValueMap = new HashMap<String, Object>(totalCell);
						for (int cellNum = 0; cellNum < totalCell; cellNum++) {
							Cell cell = row.getCell(cellNum);
							String cellValue = getCellValue(cell);
							cellValueMap.put(columns[cellNum], cellValue);
						}
						lists.add(cellValueMap);
					}
				}
			}
		}
		return lists;
	}

	@SuppressWarnings("static-access")
	private String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		if (cell.getCellType() == cell.CELL_TYPE_BLANK) {
			return "";
		} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			short format = cell.getCellStyle().getDataFormat();
			if (format == 14 || format == 31 || format == 57 || format == 58) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = cell.getDateCellValue();
				return sdf.format(date);
			} else {
				return String.valueOf(cell.getNumericCellValue());
			}
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}
}
