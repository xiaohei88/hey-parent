package org.heyframework.common.sql;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class DataSourceProvider {

	private static Connection connection;
	private static DataSource dataSource;

	public synchronized static Connection getConnection(DataResource resource) {
		try {
			if (connection == null || connection.isClosed()) {
				connection = getDataSource(resource).getConnection();
			}
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized static DataSource getDataSource(DataResource resource) {
		if (dataSource == null) {
			dataSource = new DriverManagerDataSource(resource);
		}
		return dataSource;
	}

	public static class DriverManagerDataSource implements DataSource {
		private DataResource resource;

		public DriverManagerDataSource(DataResource resource) {
			this.resource = resource;
			_loadJdbcDriver();
		}

		@Override
		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection(resource.getUrl(), resource.getUser(), resource.getPassword());
		}

		@Override
		public Connection getConnection(String username, String password) throws SQLException {
			return DriverManager.getConnection(resource.getUrl(), username, password);
		}

		@Override
		public PrintWriter getLogWriter() throws SQLException {
			throw new UnsupportedOperationException("getLogWriter");
		}

		@Override
		public int getLoginTimeout() throws SQLException {
			throw new UnsupportedOperationException("getLoginTimeout");
		}

		@Override
		public void setLogWriter(PrintWriter out) throws SQLException {
			throw new UnsupportedOperationException("setLogWriter");
		}

		@Override
		public void setLoginTimeout(int seconds) throws SQLException {
			throw new UnsupportedOperationException("setLoginTimeout");
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			if (iface == null)
				throw new IllegalArgumentException("Interface argument must not be null");
			if (!DataSource.class.equals(iface)) {
				throw new SQLException("DataSource of type [" + getClass().getName()
						+ "] can only be unwrapped as [javax.sql.DataSource], not as [" + iface.getName());
			}
			return ((T) this);
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return DataSource.class.equals(iface);
		}

		@Override
		public Logger getParentLogger() throws SQLFeatureNotSupportedException {
			throw new UnsupportedOperationException("getParentLogger");
		}

		private void _loadJdbcDriver() {
			try {
				Class.forName(resource.getDriver());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("not found jdbc driver class:[" + resource.getDriver() + "]", e);
			}
		}
	}
}
