package org.heyframework.common.grid;

public class TreeGrid extends Grid {
	private String treeField;

	public String getTreeField() {
		return treeField;
	}

	/**
	 * 设置树的展开/收缩哪列展示
	 * 
	 * @param treeField
	 */
	public void setTreeField(String treeField) {
		this.treeField = treeField;
	}

}
