package org.heyframework.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TreeUtils {

	public static final String CHILDREN = "children";

	public static final String NODE_STATE = "state";

	public static final String NODE_STATE_CLOSE = "closed";

	public static final String NODE_ICON = "iconCls";

	public static List<Map<String, Object>> generateTree(List<Map<String, Object>> listMap) {
		return generateTree(listMap, "ID", "PARENT_ID");
	}

	/**
	 * 生成树
	 * 
	 * @param listMap
	 * @param keyId 主键ID的key
	 * @param keyParentId 主键ID的parentId的key
	 * @return
	 */
	public static List<Map<String, Object>> generateTree(List<Map<String, Object>> listMap, String keyId,
			String keyParentId) {

		// 父级对应的子节点数据集合
		Map<String, List<Map<String, Object>>> parentMap = new HashMap<String, List<Map<String, Object>>>();
		// 主键ID集合
		List<String> idList = new ArrayList<String>();
		for (Map<String, Object> map : listMap) {
			String parentId = String.valueOf(map.get(keyParentId));
			List<Map<String, Object>> list = parentMap.get(parentId);
			if (ListUtils.isEmpty(list)) {
				list = new ArrayList<Map<String, Object>>();
			}
			list.add(map);
			parentMap.put(parentId, list);
			idList.add(String.valueOf(map.get(keyId)));
		}

		// 根节点数据集合
		List<Map<String, Object>> rootList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : listMap) {
			String parentId = String.valueOf(map.get(keyParentId));
			if (!idList.contains(parentId)) {
				rootList.add(map);
			}
		}

		// 树集合
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		if (!ListUtils.isEmpty(rootList)) {
			for (Map<String, Object> map : rootList) {
				String id = String.valueOf(map.get(keyId));
				List<Map<String, Object>> childrenList = generateChildrenTree(parentMap, id, keyId);
				if (!ListUtils.isEmpty(childrenList)) {
					if (!map.containsKey(NODE_STATE)) {
						map.put(NODE_STATE, NODE_STATE_CLOSE);
					}
					map.put(CHILDREN, childrenList);
				}
				treeList.add(map);
			}
		}
		return treeList;
	}

	/**
	 * 生成子节点
	 * 
	 * @param parentMap
	 * @param parentId
	 * @param keyId
	 * @return
	 */
	private static List<Map<String, Object>> generateChildrenTree(Map<String, List<Map<String, Object>>> parentMap,
			String parentId, String keyId) {
		List<Map<String, Object>> treeListMap = new ArrayList<Map<String, Object>>();
		if (!MapUtils.isEmpty(parentMap)) {
			List<Map<String, Object>> listMap = parentMap.get(parentId);
			if (!ListUtils.isEmpty(listMap)) {
				for (Map<String, Object> map : listMap) {
					String id = String.valueOf(map.get(keyId));
					List<Map<String, Object>> childrenList = generateChildrenTree(parentMap, id, keyId);
					if (!ListUtils.isEmpty(childrenList)) {
						if (!map.containsKey(NODE_STATE)) {
							map.put(NODE_STATE, NODE_STATE_CLOSE);
						}
						map.put(CHILDREN, childrenList);
					}
					treeListMap.add(map);
				}
			}
		}
		return treeListMap;
	}
}
