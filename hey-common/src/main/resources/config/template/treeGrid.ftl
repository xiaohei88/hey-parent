<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function() {
		if('${treeGrid.gridId}' == null || '${treeGrid.gridId}' == "") {
			alert("grid id is not null.");
			return;
		}
		var gridId = "#" + '${treeGrid.gridId}';
		$(gridId).treegrid(
		{
			title : '${treeGrid.title}',
			iconCls : '${treeGrid.titleIcon}',
			width : '100%',
			height : '100%',
			rownumbers : '${treeGrid.rownumbers}',
			animate : true,
			showHeader : '${treeGrid.showHeader}',
			// 这样写的原因是:字符串boolean单选的时候会把行背景点掉
			<#if '${treeGrid.singleSelect}' == 'true'>
				singleSelect : true,
			</#if>
			border : false,
			autoRowHeight : false,
			fitColumns : '${treeGrid.fitColumns}',
			idField : '${treeGrid.idField}',
			treeField : '${treeGrid.treeField}',
			showFooter : true,
			columns : [${treeGrid.column}],
			<#if '${treeGrid.toolbar}' != "" &&  '${treeGrid.toolbar}' != null>
				toolbar : ${treeGrid.toolbar},
			</#if>
			<#if '${treeGrid.data}' != "" &&  '${treeGrid.data}' != null>
				data : ${treeGrid.data},
			</#if>
			<#if '${treeGrid.url}' != "" &&  '${treeGrid.url}' != null>
				url : '${treeGrid.url}',
			</#if>
			<#if '${treeGrid.pagination}' == 'true'>
				pagination : true,
				pageSize : ${treeGrid.pageSize},
				pageList : [${treeGrid.pageList}],
			</#if>
			onClickRow : function(row) {
				if('${treeGrid.onClickRow}' != null || '${treeGrid.onClickRow}' != "") {
					${treeGrid.onClickRow}(row);
				}
			},
			onDblClickRow : function(row){
				if('${treeGrid.onDblClickRow}' != null || '${treeGrid.onDblClickRow}' != "") {
					${treeGrid.onDblClickRow}(row);
				}
			},
			onClickCell : function(field, row) {
				if('${treeGrid.onClickCell}' != null || '${treeGrid.onClickCell}' != "") {
					${treeGrid.onClickCell}(field, row);
				}
			},
			onDblClickCell : function(field, row) {
				if('${treeGrid.onDblClickCell}' != null || '${treeGrid.onDblClickCell}' != "") {
					${treeGrid.onDblClickCell}(field, row);
				}
			},
			onRowContextMenu : function(e, index, row) {
				if('${treeGrid.onRowContextMenu}' != null || '${treeGrid.onRowContextMenu}' != "") {
					${treeGrid.onRowContextMenu}(e, index, row);
				}
			},
			${treeGrid.extAttribute}
		});
	});
</script>
<div id="${treeGrid.gridId}"></div>