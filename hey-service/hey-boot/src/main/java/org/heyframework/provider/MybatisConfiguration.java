package org.heyframework.provider;

import javax.sql.DataSource;

import org.heyframework.common.persistence.mybatis.database.DataBaseFactoryBean;
import org.heyframework.common.persistence.mybatis.database.OracleDataBase;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({ DruidConfiguration.class })
/** 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven /> */
@EnableTransactionManagement
public class MybatisConfiguration {

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource druidDataSource) {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(druidDataSource);
		return bean;
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("org.heyframework.**.mapper");
		mapperScannerConfigurer.setMarkerInterface(org.heyframework.common.persistence.mybatis.base.MyBatisBaseMapper.class);
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return mapperScannerConfigurer;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource druidDataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(druidDataSource);
		return dataSourceTransactionManager;
	}

	@Bean
	public DataBaseFactoryBean dataBaseFactoryBean() {
		DataBaseFactoryBean dataBaseFactoryBean = new DataBaseFactoryBean();
		dataBaseFactoryBean.setDataBase(OracleDataBase.class);
		return dataBaseFactoryBean;
	}

}
