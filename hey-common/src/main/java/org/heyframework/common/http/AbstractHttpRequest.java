package org.heyframework.common.http;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.heyframework.common.util.MapUtils;

public abstract class AbstractHttpRequest {

	/** 客户端 */
	protected CloseableHttpClient httpclient;

	/** 请求路径 */
	protected String url;

	/** 请求参数 */
	protected Map<String, String> paramMap;

	/** 头参数 */
	protected Map<String, String> headerMap = new HashMap<String, String>();

	public AbstractHttpRequest(String url) {
		this.url = url;
		this.httpclient = getHttpClient();
	}

	/**
	 * 获取httpClient方法(http / https)
	 * 
	 * @return
	 */
	public CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	/**
	 * 开始请求
	 * 
	 * @return 返回请求内容
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public abstract String execute() throws ClientProtocolException, IOException;

	/**
	 * 再一次提交
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public abstract String reExecute(String url) throws ClientProtocolException, IOException;

	/**
	 * 增加头参数
	 * 
	 * @param name
	 * @param value
	 */
	public void addHeader(String name, String value) {
		headerMap.put(name, value);
	}

	/**
	 * 得到请求参数
	 * 
	 * @return
	 */
	protected List<NameValuePair> getNameValuePairs() {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (!MapUtils.isEmpty(paramMap)) {
			for (Map.Entry<String, String> param : paramMap.entrySet()) {
				nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
		}
		return nvps;
	}

	protected void close() throws IOException {
		if (httpclient != null) {
			httpclient.close();
		}
	}

	public String getUrl() {
		return url;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	/**
	 * 获取https客户端
	 * 
	 * @author little hey
	 *
	 */
	protected static class HttpsCliects {
		private static SSLConnectionSocketFactory socketFactory;

		/**
		 * 重写验证方法，取消检测SSL
		 */
		private static TrustManager manager = new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		};

		/**
		 * 关闭SSL验证
		 */
		private static void enableSSL() {
			try {
				SSLContext context = SSLContext.getInstance("TLS");
				context.init(null, new TrustManager[] { manager }, null);
				socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
			} catch (NoSuchAlgorithmException e) {
			} catch (KeyManagementException e) {
			}
		}

		protected static CloseableHttpClient createDefault() {
			enableSSL();

			RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
					.setExpectContinueEnabled(true)
					.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
					.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", socketFactory).build();

			PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);
			CloseableHttpClient httpsClient = HttpClients.custom().setConnectionManager(connectionManager)
					.setDefaultRequestConfig(defaultRequestConfig).build();
			return httpsClient;
		}
	}
}
