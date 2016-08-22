package org.heyframework.common.persistence.mybatis.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.heyframework.common.exception.ServiceException;
import org.heyframework.common.persistence.mybatis.MyBatisSqlTemplate;

/**
 * 所有dao的基类
 * 
 * @author h.l
 *
 * @param <T>
 */
public interface MyBatisBaseMapper<T extends MyBatisBaseBean> {

	@UpdateProvider(type = MyBatisSqlTemplate.class, method = "update")
	void update(T t) throws ServiceException;

	@DeleteProvider(type = MyBatisSqlTemplate.class, method = "delete")
	void delete(T t) throws ServiceException;

	List<T> query(Map<String, Object> condition) throws ServiceException;

	List<Map<String, Object>> queryForList(Map<String, Object> condition) throws ServiceException;

	T get(Long objectId) throws ServiceException;

	Map<String, Object> getForMap(Long objectId) throws ServiceException;
}
