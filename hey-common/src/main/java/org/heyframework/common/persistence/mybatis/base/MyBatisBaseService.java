package org.heyframework.common.persistence.mybatis.base;

import java.util.List;
import java.util.Map;

import org.heyframework.common.exception.ServiceException;

/**
 * 服务的基类
 * 
 * @author little hey
 *
 * @param <T>
 */
public interface MyBatisBaseService<T extends MyBatisBaseBean> {

	/**
	 * 根据主键查询信息
	 * 
	 * @param objectId
	 * @return
	 */
	T get(Long objectId);

	/**
	 * 主视图查询
	 * 
	 * @param condition 存储过滤条件
	 * @return
	 */
	List<Map<String, Object>> queryForList(Map<String, Object> condition) throws ServiceException;

	/**
	 * 返回某行信息
	 * 
	 * @param condition 存储过滤条件
	 * @return
	 */
	Map<String, Object> getForMap(Long objectId) throws ServiceException;

	/**
	 * 对象创建
	 * 
	 * @param object
	 * @return
	 * @throws ServiceException
	 */
	void insert(T object) throws ServiceException;

	/**
	 * 对象修改
	 * 
	 * @param object
	 * @throws ServiceException
	 */
	void update(T object) throws ServiceException;

	/**
	 * 对象删除
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	void delete(Long id) throws ServiceException;
}
