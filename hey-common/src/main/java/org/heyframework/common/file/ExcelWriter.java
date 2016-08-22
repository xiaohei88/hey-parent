package org.heyframework.common.file;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.heyframework.common.util.ListUtils;
import org.heyframework.common.util.MapUtils;

public class ExcelWriter {

	private Workbook workBook;
	private SheetEntity[] sheetEntity;

	public ExcelWriter(SheetEntity... sheetEntity) {
		workBook = new HSSFWorkbook();
		this.sheetEntity = sheetEntity;
	}

	public void wirte() {
		if (workBook != null) {
			for (int i = 0; i < sheetEntity.length; i++) {
				createSheet(i, sheetEntity[i]);
			}
		}
	}

	public void out(HttpServletResponse response, String fileName) throws IOException {
		if (workBook != null) {
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			OutputStream out = response.getOutputStream();
			workBook.write(out);
			out.flush();
			out.close();
		}
	}

	/**
	 * 创建sheet
	 * 
	 * @param index
	 * @param sheetEntity
	 */
	private void createSheet(int index, SheetEntity sheetEntity) {
		// 1、创建sheet
		Sheet sheet = workBook.createSheet();
		workBook.setSheetName(0, sheetEntity.getSheetName());

		// 2、设置列宽度
		setColumnWidth(sheet, sheetEntity);

		// 3、创建抬头
		createTitleRow(sheet, sheetEntity);

		// 4、创建内容
		createContentRow(sheet, sheetEntity);

	}

	/**
	 * 设置列宽
	 * 
	 * @param sheet
	 * @param sheetEntity
	 */
	private void setColumnWidth(Sheet sheet, SheetEntity sheetEntity) {
		Integer[] widths = sheetEntity.getColumnWidth();
		if (widths != null) {
			for (int i = 0; i < widths.length; i++) {
				sheet.setColumnWidth(i, widths[i]);
			}
		}
	}

	/**
	 * 创建表抬头
	 * 
	 * @param sheet
	 * @param sheetEntity
	 */
	private void createTitleRow(Sheet sheet, SheetEntity sheetEntity) {
		Font font = workBook.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

		CellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFont(font);

		Row titleRow = sheet.createRow(0);
		titleRow.setHeight((short) 500);
		String[] names = sheetEntity.getColumnName();
		if (names != null) {
			for (int j = 0; j < names.length; j++) {
				Cell cell = titleRow.createCell(j);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(names[j]);
			}
		}
	}

	/**
	 * 创建内容
	 * 
	 * @param sheet
	 * @param sheetEntity
	 */
	private void createContentRow(Sheet sheet, SheetEntity sheetEntity) {
		List<LinkedHashMap<String, Object>> lists = sheetEntity.getContent();
		if (!ListUtils.isEmpty(lists)) {
			for (int i = 0; i < lists.size(); i++) {
				Row row = sheet.createRow(i + 1);
				Map<String, Object> contentMap = lists.get(i);
				if (!MapUtils.isEmpty(contentMap)) {
					int j = 0;
					for (Map.Entry<String, Object> map : contentMap.entrySet()) {
						Cell cell = row.createCell(j);
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(map.getValue()));
						j++;
					}
				}
			}
		}
	}
	
	public static class SheetEntity {

		private String sheetName;
		private Integer[] columnWidth;
		private String[] columnName;
		private List<LinkedHashMap<String, Object>> content;

		public String getSheetName() {
			return sheetName;
		}

		public void setSheetName(String sheetName) {
			this.sheetName = sheetName;
		}

		public Integer[] getColumnWidth() {
			return columnWidth;
		}

		public void setColumnWidth(Integer[] columnWidth) {
			this.columnWidth = columnWidth;
		}

		public String[] getColumnName() {
			return columnName;
		}

		public void setColumnName(String[] columnName) {
			this.columnName = columnName;
		}

		public List<LinkedHashMap<String, Object>> getContent() {
			return content;
		}

		public void setContent(List<LinkedHashMap<String, Object>> content) {
			this.content = content;
		}
	}
}
