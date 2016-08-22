package org.heyframework.common.grid;

import org.heyframework.common.util.ArraysUtils;
import org.heyframework.common.util.StringUtils;

public class Grid {
	private String gridId;
	private String title;
	private String titleIcon;
	private String data;;
	private String url;
	private String idField;
	private boolean rownumbers = true;
	private boolean fitColumns = false;
	private boolean singleSelect = true;
	private boolean showHeader = true;
	private boolean pagination = false;
	private Integer pageSize = 10;
	private String pageList = "10, 20, 50, 100";
	private String column;
	private String toolbar;
	private String onDblClickRow;
	private String onClickRow;
	private String onClickCell;
	private String onDblClickCell;
	private String onRowContextMenu;
	private String extAttribute;

	public String getGridId() {
		return gridId;
	}

	/**
	 * 设置表格ID
	 * 
	 * @param id
	 */
	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * 设置表格标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleIcon() {
		return titleIcon;
	}

	/**
	 * 设置表格标题图标
	 * 
	 * @return
	 */
	public void setTitleIcon(String titleIcon) {
		this.titleIcon = titleIcon;
	}

	public String getData() {
		return data;
	}

	/**
	 * 设置表格数据
	 * 
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	/**
	 * 请求路径
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRownumbers() {
		return rownumbers;
	}

	/**
	 * 是否显示行号
	 * 
	 * @param rownumbers
	 */
	public void setRownumbers(boolean rownumbers) {
		this.rownumbers = rownumbers;
	}

	public boolean isFitColumns() {
		return fitColumns;
	}

	/**
	 * 自适应列宽
	 * 
	 * @param fitColumns
	 */
	public void setFitColumns(boolean fitColumns) {
		this.fitColumns = fitColumns;
	}

	public boolean isSingleSelect() {
		return singleSelect;
	}

	/**
	 * 单选/多选
	 * 
	 * @param singleSelect
	 */
	public void setSingleSelect(boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	/**
	 * 是否显示表头
	 * 
	 * @return
	 */
	public boolean isShowHeader() {
		return showHeader;
	}

	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}

	public boolean isPagination() {
		return pagination;
	}

	/**
	 * 是否分页
	 * 
	 * @param pagination
	 */
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 默认当前页行数
	 * 
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageList() {
		return pageList;
	}

	/**
	 * 当前页显示行数集合
	 * 
	 * @param pageList
	 */
	public void setPageList(String[] pageList) {
		this.pageList = ArraysUtils.toString(false, pageList);
	}

	public String getIdField() {
		return idField;
	}

	/**
	 * 设置表格行主键ID
	 * 
	 * @param idField
	 */
	public void setIdField(String idField) {
		this.idField = idField;
	}

	public String getColumn() {
		return column;
	}

	/**
	 * 设置表格列
	 * 
	 * @param column
	 */
	public void setColumn(StringBuilder column) {
		this.column = column.toString();
	}

	public String getToolbar() {
		return toolbar;
	}

	/**
	 * 工具栏
	 * 
	 * @param toolbar
	 */
	public void setToolbar(String toolbar) {
		if (toolbar.startsWith("#")) {
			this.toolbar = StringUtils.format("'{}'", toolbar);
		} else {
			this.toolbar = toolbar;
		}
	}

	public String getOnDblClickRow() {
		return onDblClickRow;
	}

	/**
	 * 设置双击行事件
	 * 
	 * @param onDblClickRow
	 */
	public void setOnDblClickRow(String onDblClickRow) {
		this.onDblClickRow = onDblClickRow;
	}

	public String getOnClickRow() {
		return onClickRow;
	}

	/**
	 * 设置行单击事件
	 * 
	 * @param onClickRow
	 */
	public void setOnClickRow(String onClickRow) {
		this.onClickRow = onClickRow;
	}

	public String getOnClickCell() {
		return onClickCell;
	}

	/**
	 * 设置单击列事件
	 * 
	 * @return
	 */
	public void setOnClickCell(String onClickCell) {
		this.onClickCell = onClickCell;
	}

	public String getOnDblClickCell() {
		return onDblClickCell;
	}

	/**
	 * 设置双击列事件
	 * 
	 * @return
	 */
	public void setOnDblClickCell(String onDblClickCell) {
		this.onDblClickCell = onDblClickCell;
	}

	/**
	 * 右击行菜单事件
	 * 
	 * @return
	 */
	public String getOnRowContextMenu() {
		return onRowContextMenu;
	}

	public void setOnRowContextMenu(String onRowContextMenu) {
		this.onRowContextMenu = onRowContextMenu;
	}

	public String getExtAttribute() {
		return extAttribute;
	}

	/**
	 * 设置扩展属性.例如:columns : '',toolbar : ''
	 * 
	 * @param extAttribute
	 */
	public void setExtAttribute(String extAttribute) {
		this.extAttribute = extAttribute;
	}

	public static class Page {

		/** 总行数 */
		private int rows;

		/** 当前页 */
		private int pageCurrent;

		/** 页行树 */
		private int pageRows;

		/** 开始索引 */
		private int pageStart;

		/** 结束索引 */
		private int pageEnd;

		/** 总页数 */
		private int pages;

		public Page(int rows, int pageCurrent, int pageRows) {
			if (pageRows <= 0) {
				this.pageRows = 10;
			} else {
				this.pageRows = pageRows;
			}

			if (pageCurrent <= 0) {
				this.pageCurrent = 1;
			} else {
				this.pageCurrent = pageCurrent;
			}

			if (rows <= 0) {
				this.pageCurrent = 1;
				this.pages = 1;
				this.pageStart = 0;
				this.pageEnd = 0;
				this.rows = 0;
				return;
			}

			this.rows = rows;
			int tmp = rows % this.pageRows;

			if (tmp == 0) {
				this.pages = rows / this.pageRows;
			} else {
				this.pages = rows / this.pageRows + 1;
			}
			if (this.pageCurrent > pages) {
				this.pageCurrent = pages;
			}
			this.pageStart = (this.pageCurrent - 1) * pageRows;
			this.pageEnd = this.pageCurrent * pageRows;
		}

		/**
		 * 返回当前页
		 * 
		 * @return the pageCurrent.
		 */
		public int getPageCurrent() {
			return pageCurrent;
		}

		/**
		 * 获取当前
		 * 
		 * @return the pageRows.
		 */
		public int getPageRows() {
			return pageRows;
		}

		/**
		 * 获取页开始
		 * 
		 * @return the pageStart.
		 */
		public int getPageStart() {
			return pageStart;
		}

		/**
		 * 获取页结束
		 * 
		 * @return the pageEnd.
		 */
		public int getPageEnd() {
			return pageEnd;
		}

		/**
		 * 获取总页数
		 * 
		 * @return the pages.
		 */
		public int getPages() {
			return pages;
		}

		/**
		 * 获取收总行数
		 * 
		 * @return the rows.
		 */
		public int getRows() {
			return rows;
		}
	}
}
