<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function() {
		if('${dataGrid.gridId}' == null || '${dataGrid.gridId}' == "") {
			alert("grid id is not null.");
			return;
		}

		var gridId = "#" + '${dataGrid.gridId}';
		$(gridId).datagrid(
		{
			title : '${dataGrid.title}',
			iconCls : '${dataGrid.titleIcon}',
			width : '100%',
			height : '100%',
			rownumbers : '${dataGrid.rownumbers}',
			fitColumns : '${dataGrid.fitColumns}',
			showHeader : '${dataGrid.showHeader}',
			<#if '${dataGrid.singleSelect}' == 'true'>
				singleSelect : true,
			</#if>
			idField:'${dataGrid.idField}',
			columns : [${dataGrid.column}],
			border : false,
			autoRowHeight : false,
			<#if '${dataGrid.toolbar}' != "" &&  '${dataGrid.toolbar}' != null>
				toolbar : ${dataGrid.toolbar},
			</#if>
			<#if '${dataGrid.data}' != "" &&  '${dataGrid.data}' != null>
				data : ${dataGrid.data},
			</#if>
			<#if '${dataGrid.url}' != "" &&  '${dataGrid.url}' != null>
				url : '${dataGrid.url}',
			</#if>
			<#if '${dataGrid.pagination}' == 'true'>
				pagination : true,
				pageSize : ${dataGrid.pageSize},
				pageList : [${dataGrid.pageList}],
			</#if>
			<#if '${dataGrid.scrollview}' == 'true'>
				view : scrollview,
			</#if>
			onClickRow : function(rowIndex, rowData) {
				if('${dataGrid.onClickRow}' != null || '${dataGrid.onClickRow}' != "") {
					${dataGrid.onClickRow}(rowIndex, rowData);
				}
			},
			onDblClickRow : function(rowIndex, rowData) {
				if('${dataGrid.onDblClickRow}' != null || '${dataGrid.onDblClickRow}' != "") {
					${dataGrid.onDblClickRow}(rowIndex, rowData);
				}
			},
			onClickCell : function(index, field, value) {
				if('${dataGrid.onClickCell}' != null || '${dataGrid.onClickCell}' != "") {
					${dataGrid.onClickCell}(index, field, value);
				}
			},
			onDblClickCell : function(index, field, value) {
				if('${dataGrid.onDblClickCell}' != null || '${dataGrid.onDblClickCell}' != "") {
					${dataGrid.onDblClickCell}(index, field, value);
				}
			},
			onRowContextMenu : function(e, index, row) {
				if('${dataGrid.onRowContextMenu}' != null || '${dataGrid.onRowContextMenu}' != "") {
					${dataGrid.onRowContextMenu}(e, index, row);
				}
			},
			${dataGrid.extAttribute}
		});
	});
</script>
<div id="${dataGrid.gridId}" />